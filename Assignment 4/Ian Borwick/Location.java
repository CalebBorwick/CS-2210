//This class represents the position (x, y) of a pixel
public class Location {
	
	//declare variables
	private int x,y;
	
	//constructor that initializes this Location object with the specified coordinates
	public Location(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	//Returns the x coordinate of this Location.
	public int xCoord() {
		return this.x;
	}
	
	//Returns the y coordinate of this Location.
	public int yCoord() {
		return this.y;
	}
	
	//Compares this Location with p using column order
	public int compareTo (Location p) {
		if (this.xCoord()<p.xCoord()||(this.xCoord()==p.xCoord()&&this.yCoord()<p.yCoord())) {
			return -1;
		}
		else if (this.xCoord()==p.xCoord()&&this.yCoord()==p.yCoord()) {
			return 0;
		}
		else {
			return 1;
		}
	}
}
