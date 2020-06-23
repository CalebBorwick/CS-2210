
public class Node {
	
	//declare variables
	private int name;
	private boolean mark;
	
	//sets the node
	public Node(int name) {
		this.name = name;
	}
	
	//sets the mark to true or false
	public void setMark(boolean mark) {
		this.mark = mark;
	}
	
	//returns the mark
	public boolean getMark(){
		return this.mark;
	}
	
	//returns the name
	public int getName() {
		return this.name;
	}
}
