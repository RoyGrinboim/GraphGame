package gameClient;

import Server.game_service;
import oop_dataStructure.oop_edge_data;
import oop_dataStructure.oop_graph;
import oop_dataStructure.oop_node_data;
import oop_elements.OOP_Edge;
import oop_elements.OOP_NodeData;

public class Fruit {

	double value;
	double x;
	double y;
	boolean eaten = false;
	
	public Fruit(String s, oop_graph g)
	{
		int i = s.indexOf("35");
		String temp = "";
		while(s.charAt(i) != ',')
		{
			temp = temp + s.charAt(i);
			i++;
		}
		this.x = Double.parseDouble(temp);
		i = s.indexOf("32");
		temp = "";
		while(s.charAt(i) != ',')
		{
			temp = temp + s.charAt(i);
			i++;
		}
		this.y = Double.parseDouble(temp);
		
		i = 18;
		temp = "";
		while(s.charAt(i) != ',')
		{
			temp = temp + s.charAt(i);
			i++;
		}
		this.value = Double.parseDouble(temp);
	}
	
	public void setEaten()
	{
		this.eaten=true;
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

	public boolean getEaten() {
		return this.eaten;
	}

	public double getValue() {
		return this.value;
	}

}
