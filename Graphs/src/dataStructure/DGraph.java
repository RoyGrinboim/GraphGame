package dataStructure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class DGraph implements graph{

	private HashMap<Integer, node_data> nodes = new HashMap<>();
	private HashMap<pair, edge_data> edges = new HashMap<>();
	private int node_counter = 0;
	private int edge_counter = 0;
	
	@Override
	public node_data getNode(int key) {
		return this.nodes.get(key);
	}

	@Override
	public edge_data getEdge(int src, int dest) {
		pair p = new pair(getNode(src), getNode(dest));
		return this.edges.get(p);
	}

	@Override
	public void addNode(node_data n) {
		this.nodes.put(n.getKey(), n);
		this.node_counter++;
	}

	@Override
	public void connect(int src, int dest, double w) {
		node_data s = getNode(src);
		node_data d = getNode(dest);
		edge_data e = new Edge(s, d, w);
		pair p = new pair(s, d);
		this.edges.put(p, e);
		this.edge_counter++;
	}

	@Override
	public Collection<node_data> getV() {
		ArrayList<node_data> al = new ArrayList<node_data>(this.nodes.values());
		return al;
	}

	@Override
	public Collection<edge_data> getE(int node_id) {
		ArrayList<edge_data> al = new ArrayList<edge_data>();
		node_data n = getNode(node_id);
		for(pair p : this.edges.keySet())
		{
			if(n == p.getSource() || n == p.getDestination())
			{
				al.add(this.edges.get(p));
			}
		}
		return al;
	}

	@Override
	public node_data removeNode(int key) {
		node_data n = getNode(key);
		for(pair p:this.edges.keySet())
		{
			if(n == p.getSource() || n == p.getDestination())
			{
				this.edges.remove(p);
				this.edge_counter--;
			}
		}
		node_counter--;
		return n;
	}

	@Override
	public edge_data removeEdge(int src, int dest) {
		node_data s = getNode(src);
		node_data d = getNode(dest);
		pair p = new pair(s, d);
		edge_data e = getEdge(src, dest);
		this.edges.remove(p);
		this.edge_counter--;
		return e;
	}

	@Override
	public int nodeSize() {
		return node_counter;
	}

	@Override
	public int edgeSize() {
		return edge_counter;
	}

	@Override
	public int getMC() {
		// TODO Auto-generated method stub
		return 0;
	}

}
