package dataStructure;

public class Edge implements edge_data{

	private node_data source;
	private node_data destination;
	private double weight;
	private int tag;
	private String info = "";
	
	public Edge(node_data src, node_data dest, double w) {
		this.source = src;
		this.destination = dest;
		this.weight = w;
	}

	@Override
	public int getSrc() {
		return this.source.getKey();
	}

	@Override
	public int getDest() {
		return this.destination.getKey();
	}

	@Override
	public double getWeight() {
		return this.weight;
	}

	@Override
	public String getInfo() {
		return this.info;
	}

	@Override
	public void setInfo(String s) {
		this.info = s;
		
	}

	@Override
	public int getTag() {
		return this.tag;
	}

	@Override
	public void setTag(int t) {
		this.tag = t;
		
	}

}
