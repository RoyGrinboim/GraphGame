package dataStructure;

import utils.Point3D;

public class Node implements node_data{
	
	private int key;
	private double weight;
	private Point3D point;
	private int tag;
	private String info = "";
	
	public Node(int key, double wei) {
		this.key = key;
		this.weight = wei;
	}

	public Node(int k, int w, int x, int y, int z) {
		this.key = k;
		this.weight = (double)w;
		Point3D p = new Point3D((double)x, (double)y, (double)z);
		this.point = p;
	}
	
	public Node(int k, double w, double x, double y) {
		this.key = k;
		this.weight = w;
		Point3D p = new Point3D(x, y, (double)0);
		this.point = p;
	}

	@Override
	public int getKey() {
		return this.key;
	}

	@Override
	public Point3D getLocation() {
		if(this.point == null)
		{
			return null;
		}
		return this.point;
	}

	@Override
	public void setLocation(Point3D p) {
		this.point = p;
		
	}

	@Override
	public double getWeight() {
		return this.weight;
	}

	@Override
	public void setWeight(double w) {
		this.weight = w;
		
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
