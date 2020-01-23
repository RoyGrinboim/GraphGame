package dataStructure;

public class pair {

	private node_data source;
	private node_data destination;
	
	
	public pair(node_data source, node_data destination) {
		this.source = source;
		this.destination = destination;
	}
	
	public node_data getSource() {
		return source;
	}
	public void setSource(Node source) {
		this.source = source;
	}
	public node_data getDestination() {
		return destination;
	}
	public void setDestination(Node destination) {
		this.destination = destination;
	}
	
	
}
