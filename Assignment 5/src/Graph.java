import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class Graph implements GraphADT{
	
	//declare variables
	private int n, name;
	private Node[] vertex;
	private Edge[][] matrix; 
	
	//constructor that creates a graph n nodes and no edges 
	public Graph(int n) {
		this.n = n;
		vertex = new Node[n];
		matrix = new Edge[n][n];
		for (int i=0; i<n; i++) {
			vertex[i]= new Node(i);
		}
	}
	
	//returns the node with the specified name
	public Node getNode(int name) throws GraphException{
		if (name<0 || name > n-1) {
			throw new GraphException();
		}
		else {
			return vertex[name];

		}
	}

	//Adds an edge to the given type connecting u and v
	public void insertEdge(Node u, Node v, int edgeType) throws GraphException {
		if(u.getName()>=0 && u.getName()<=n-1) {
			if(v.getName()>=0 && v.getName()<=n-1) {
					matrix[u.getName()][v.getName()] = new Edge(u,v,edgeType);
					matrix[v.getName()][u.getName()] = new Edge(u,v,edgeType);
				}
				else {
					throw new GraphException();
				}
			}
			else {
				throw new GraphException();
			}
		
	}

	//returns a java iterator storing all the edges incident on the node u 
	//or null if u does not have any edges incidents on it
	public Iterator incidentEdges(Node u) throws GraphException {
		if (u.getName()<0 || u.getName() <= n-1) {
			List<Edge> valid = new ArrayList<Edge>();
			for (int i = 0; i<n; i++) {
				if(matrix[u.getName()][i]!=null) {
					valid.add(matrix[u.getName()][i]);
				}
			}
			if(valid.size() == 0) {
				return null;
			}
			else {
				return valid.iterator();
			}
		}
		else {
			throw new GraphException();
		}
	}

	//returns the edge connecting nodes u and v
	public Edge getEdge(Node u, Node v) throws GraphException {
		if(u.getName()>=0 &&u.getName()<=n-1) {
			if(v.getName()>=0&&v.getName()<=n-1) {
				if(matrix[u.getName()][v.getName()]!=null && matrix[v.getName()][u.getName()]!=null) {
					return matrix[u.getName()][v.getName()];
				}
				else {
					throw new GraphException();
				}
			}
			else {
				throw new GraphException();
			}
		}
		else {
			throw new GraphException();
		}
	}

	//returns true if nodes u and v are adjacent false otherwise
	public boolean areAdjacent(Node u, Node v) throws GraphException {
		if (v.getName()<0 || v.getName()>n-1 || u.getName()<0 || u.getName()>n-1) {
			throw new GraphException();
		}
		else if(matrix[u.getName()][v.getName()]== null || matrix[v.getName()][u.getName()]==null) {
			return false;
		}
		else {
			return true;
		}
	}


}
