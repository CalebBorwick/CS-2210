
public class Edge {
	
	//declare variables 
	private Node firstEndpoint;
	private Node secondEndpoint;
	private int type;
	
	//creates a graph with n nodes and no edges 
	public Edge(Node u, Node v, int type) {
		this.firstEndpoint = u;
		this.secondEndpoint = v;
		this.type = type;
	}
	
	//return the first end point
	public Node firstEndpoint() {
		return this.firstEndpoint;
	}
	
	//return the second end point
	public Node secondEndpoint() {
		return this.secondEndpoint;
	}
	
	//return the type of the edge
	public int getType() {
		return this.type;
	}
}
