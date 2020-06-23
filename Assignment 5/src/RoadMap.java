import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;


public class RoadMap {
	
	//declare variables
	private Graph map;
	private String inputFile;
	private int scale;
	private int start;
	private int end;
	private int width;
	private int length;
	private int initialBudget;
	private int toll;
	private int gain;
	private Stack stack;
	private LinkedList<Integer> list[];

	
	//Constructor for building a graph from the input file
	public RoadMap(String inputFile) throws MapException,NumberFormatException, IOException {
		int count = 0;
		if(inputFile.isEmpty()) {
			throw new MapException();
		}
		else {
			FileInputStream stream = new FileInputStream(inputFile);
			InputStreamReader read = new InputStreamReader(stream);
			BufferedReader file = new BufferedReader(read);
			
			this.scale = Integer.valueOf(file.readLine());
			this.start = Integer.valueOf(file.readLine());
			this.end = Integer.valueOf(file.readLine());
			this.width = Integer.valueOf(file.readLine());
			this.length = Integer.valueOf(file.readLine());
			this.initialBudget = Integer.valueOf(file.readLine());
			this.toll = Integer.valueOf(file.readLine());
			this.gain = Integer.valueOf(file.readLine());
			this.map = new Graph(this.width*this.length);
			
			for (int i=0; i<this.length*2 -1; i++) {
				String column = file.readLine();
				for (int j=0;j<width*2-1;j++) {
					char curr = column.charAt(j);
					if (curr=='+') {
						count++;
					}
					else if(curr=='T') {
						if (i%2==0) {
							map.insertEdge(map.getNode(count-1),map.getNode(count),1);

						}
						else {
							map.insertEdge(map.getNode(count-width+(j/2)),map.getNode(count+(j/2)),1);
						}
						
					}
					else if(curr=='F') {
						if (i%2==0) {
							map.insertEdge(map.getNode(count-1),map.getNode(count),0);

						}
						else {
							map.insertEdge(map.getNode(count-width+(j/2)),map.getNode(count+(j/2)),0);
						}
						
					}
					else if(curr=='C') {
						if (i%2==0) {
							map.insertEdge(map.getNode(count-1),map.getNode(count),-1);

						}
						else {
							map.insertEdge(map.getNode(count-width+(j/2)),map.getNode(count+(j/2)),-1);
						}
					}
				}	
			}
			file.close();
		}
	}
	
	//returns graph representing the road map
	public Graph getGraph() throws MapException{
		if (map!= null) {
			return map;
		}
		else {
			throw new MapException();
		}
	}
	
	// returns the starting node 
	public int getStartingNode() {
		return this.start;	
		}
	
	//returns the destination Node
	public int getDestinationNode() {
		return this.end;
	}
	
	//returns the initial amount of money to pay tolls
	public int getInitialMoney() {
		return this.initialBudget;
	}
	
	//private helper that sets initial budget
	private void setInitialMoney(int i) {
		this.initialBudget=i;
	}
	

	
        
	
	//returns a java iterator containing the nodes of a path from the start node to the destination node
	public Iterator findPath(int start, int destination, int initialMoney) {
		Stack<Node> path = new Stack<Node>(); 
	     
        if (solvePath(start,destination, getInitialMoney(), path)==true){
            return path.iterator();
        }
 
        return null;
         
    }
	
	//private recursive boolean depth first search
	private boolean solvePath(int start, int destination, int initialMoney, Stack<Node> path) throws GraphException{
		Node curr = map.getNode(start);
		curr.setMark(true);
		path.push(curr);
		//end condition
		if(start == destination) {
			return true;
		}
		
		Iterator edges = map.incidentEdges(curr);
		//iterates through edges to find path
		while(edges.hasNext()) {
			Edge next = (Edge)edges.next();
			Node endPoint;
			//determine the point being moved too
			if (next.firstEndpoint()==curr) {
				endPoint = next.secondEndpoint();
			}
			else {
				endPoint = next.firstEndpoint();
			}
			//if edge is toll
			if(endPoint.getMark()==false) {
				if(next.getType()==1) {
					if (getInitialMoney()>= this.toll) {
						setInitialMoney(getInitialMoney()-this.toll);
							if(solvePath(endPoint.getName(),destination, getInitialMoney(),path)==true) {
								return true;
						}
					}
				}
				//if edge is free
				else if(next.getType()==0) {
					if(solvePath(endPoint.getName(),destination, getInitialMoney(),path)==true) {
						return true;
					}
				}
				//if edge is gain
				else if(next.getType()==-1) {
					setInitialMoney(getInitialMoney()+this.gain);
						if(solvePath(endPoint.getName(),destination, getInitialMoney(),path)==true) {
							return true;
					}
				}
			}
		}
		//if the path has to go backwards checks type to fix money loss/gain going backwards
		Node removed = path.pop();
		if(path.isEmpty()) {
			return false;
		}
		removed.setMark(false);
		Edge conEdge = map.getEdge(removed, path.peek());
		
		if(conEdge.getType()==1) {
			setInitialMoney(getInitialMoney()+this.toll);
		}
		else if (conEdge.getType()==-1){
			setInitialMoney(getInitialMoney()-this.gain);
		}
		return false;
		}


}
