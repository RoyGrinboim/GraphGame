package algorithms;

import dataStructure.DGraph;
import dataStructure.edge_data;
import dataStructure.node_data;
import utils.StdDraw;

public class Gui {
	
	public static void main(String[] args)
	{
		StdDraw.setCanvasSize(800, 800);
		StdDraw.setXscale(0, 1000);
		StdDraw.setYscale(0, 1000);
		DGraph g = new DGraph();
		printPoints(g);
		printEdges(g);
	}
	
	public static void printPoints(DGraph g)
	{
		for(node_data n : g.getV())
		{
			   StdDraw.setPenRadius(0.03);
	           StdDraw.setPenColor(StdDraw.BLUE);
	           StdDraw.point(n.getLocation().x(), n.getLocation().y());
	           StdDraw.setPenColor(StdDraw.BLACK);
	           String text = ""+n.getWeight();
	           StdDraw.text(n.getLocation().x(), n.getLocation().y(), text);
		}
	}
	
	public static void printEdges(DGraph g)
	{
		for(node_data n : g.getV())
		{
			for(edge_data e : g.getE(n.getKey()))
			{
				StdDraw.setPenRadius(0.01);
		           StdDraw.setPenColor(StdDraw.RED);
		           StdDraw.line(g.getNode(e.getSrc()).getLocation().x(), g.getNode(e.getSrc()).getLocation().y(), g.getNode(e.getDest()).getLocation().x(), g.getNode(e.getDest()).getLocation().y());
		           StdDraw.setPenColor(StdDraw.BLACK);
		           String text = ""+e.getWeight();
		           double x = (g.getNode(e.getSrc()).getLocation().x()+g.getNode(e.getDest()).getLocation().x())/2;
		           double y = (g.getNode(e.getSrc()).getLocation().y()+g.getNode(e.getDest()).getLocation().y())/2;
		           StdDraw.text(x, y, text);
			}
		}
	}

}
