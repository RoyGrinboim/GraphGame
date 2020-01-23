package gameClient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import Server.Fruit;
import Server.Game_Server;
import Server.game_service;
import algorithms.Graph_Algo;
import dataStructure.edge_data;
import dataStructure.node_data;
import oop_dataStructure.OOP_DGraph;
import oop_dataStructure.oop_edge_data;
import oop_dataStructure.oop_graph;
import oop_dataStructure.oop_node_data;
import utils.StdDraw;


public class MyGameGui {
	static double score = 0.0;
	public static void main(String[] a) {
		StdDraw.setCanvasSize(1500, 500);
		StdDraw.setXscale(35.187, 35.215);
		StdDraw.setYscale(32.099, 32.11);
		start();
		}
	public static void start() {
		for (int i = 0; i < 24; i++) {
		int scenario_num = i;
		game_service game = Game_Server.getServer(scenario_num); 
		String g = game.getGraph();
		OOP_DGraph gg = new OOP_DGraph();
		gg.init(g);
		String info = game.toString();
		JSONObject line;
		try {
			line = new JSONObject(info);
			JSONObject ttt = line.getJSONObject("GameServer");
			int rs = ttt.getInt("robots");
			System.out.println(info);
			System.out.println(g);
			// the list of fruits should be considered in your solution
			List<String> alf = game.getFruits();
			Iterator<String> f_iter = game.getFruits().iterator();
			while(f_iter.hasNext()) {System.out.println(f_iter.next());}	
			int src_node = 0;  // arbitrary node, you should start at one of the fruits
			for(int a = 0;a<rs;a++) {
				game.addRobot(src_node+a);
			}
		}
		catch (JSONException e) {e.printStackTrace();}
		game.startGame();
		while(game.isRunning()) {
			moveRobots(game, gg);
		}
		String results = game.toString();
		System.out.println("Game Over: "+results);
		StdDraw.clear();
		}
	}
	private static void drawGraph(oop_graph g) {
		printPoints(g);
		printEdges(g);
		
	}
	
	private static void printPoints(oop_graph g)
	{
		for(oop_node_data n : g.getV())
		{
			   StdDraw.setPenRadius(0.03);
	           StdDraw.setPenColor(StdDraw.BLUE);
	           StdDraw.point(n.getLocation().x(), n.getLocation().y());
	           StdDraw.setPenColor(StdDraw.YELLOW);
	           String text = ""+n.getWeight();
	           StdDraw.text(n.getLocation().x(), n.getLocation().y(), text);
		}
	}
	
	private static void printEdges(oop_graph g)
	{
		for(oop_node_data n : g.getV())
		{
			for(oop_edge_data e : g.getE(n.getKey()))
			{
				   StdDraw.setPenRadius(0.01);
		           StdDraw.setPenColor(StdDraw.GRAY);
		           StdDraw.line(g.getNode(e.getSrc()).getLocation().x(), g.getNode(e.getSrc()).getLocation().y(), g.getNode(e.getDest()).getLocation().x(), g.getNode(e.getDest()).getLocation().y());
		           StdDraw.setPenColor(StdDraw.YELLOW);
		           String text = ""+e.getWeight();
		           double x = (g.getNode(e.getSrc()).getLocation().x()+g.getNode(e.getDest()).getLocation().x())/2;
		           double y = (g.getNode(e.getSrc()).getLocation().y()+g.getNode(e.getDest()).getLocation().y())/2;
		           StdDraw.text(x, y, text);
			}
		}
	}
	private static void moveRobots(game_service game, oop_graph gg) {
		Iterator<String> f_iter = game.getFruits().iterator();
		ArrayList<gameClient.Fruit> flist = new ArrayList<gameClient.Fruit>();
		String s="";
		while(f_iter.hasNext()) {
			s = f_iter.next();
			gameClient.Fruit f = new gameClient.Fruit(s, gg);
			flist.add(f);
			}
		int src = 0;
		for(oop_node_data n : gg.getV())
		{
			for(oop_edge_data e : gg.getE(src))
			{
				if(e.getSrc() == src)
				{
					moveBot(gg, src, e.getDest(), flist);
					break;
				}
			}	
			src = n.getKey();
		}
		
	}
	
	@SuppressWarnings("deprecation")
	private static void moveBot(oop_graph g, int src, int dest, ArrayList<gameClient.Fruit> alf) {
		double x = g.getNode(src).getLocation().x();
		double y = g.getNode(src).getLocation().y();
		double x1 = g.getNode(dest).getLocation().x();
		double y1 = g.getNode(dest).getLocation().y();
		double m = (y1-y)/(x1-x);
		String bot1 = "file:///C:/Users/roeeg/eclipse-workspace/Graphs/src/gameClient/bot1.png";
		double width = 0.0005;
		double height = 0.0005;
		drawGraph(g);
		StdDraw.picture(x, y, bot1, width, height);
		StdDraw.show(10);
		while(x <= x1 && y <= y1)
		{
			drawGraph(g);
			x += 0.00001;
			y = m*(x-x1)+y1;
			StdDraw.picture(x, y, bot1, width, height);
			drawFruits(alf);
			for(gameClient.Fruit f : alf)
			{
				if(x == f.getX() && y == f.getY())
				{
					f.setEaten();
					score += f.getValue();
				}
			}
			StdDraw.show(10);
			StdDraw.clear();
		}
	}
	private static void drawFruits(ArrayList<gameClient.Fruit> alf) {
		String fruit1 = "file:///C:/Users/roeeg/eclipse-workspace/Graphs/src/gameClient/apple.png";
		double width = 0.0005;
		double height = 0.0005;
		for(gameClient.Fruit f : alf)
		{
			if(f.getEaten() == false)
			{
				StdDraw.picture(f.getX(), f.getY(), fruit1, width, height);
			}
		}
		
	}
	
	private ArrayList<oop_node_data> shortestPath(int src, int dest, oop_graph graph) {
		HashMap<Integer, Double> hmw = new HashMap<Integer, Double>();
		HashMap<Integer, ArrayList<oop_node_data>> hml = new HashMap<Integer, ArrayList<oop_node_data>>();
		for(oop_node_data n : graph.getV())
		{
			hmw.put(n.getKey(), Double.MAX_VALUE);
		}
		hmw.put(src, 0.0);
		hml.get(src).add(graph.getNode(src));
		for(int i : hmw.keySet())
		{
			for(oop_edge_data e : graph.getE(i))
			{
				if(e.getSrc() == i)
				{
					double w = hmw.get(i)+e.getWeight();
					if(w < hmw.get(e.getDest()))
					{
						hmw.put(e.getDest(), w);
						hml.get(e.getDest()).addAll(hml.get(i));
						hml.get(e.getDest()).add(graph.getNode(e.getDest()));
					}
				}
			}
		}
		return hml.get(dest);
	}
	
}


