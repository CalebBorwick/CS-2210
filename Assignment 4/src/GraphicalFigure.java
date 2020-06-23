
public class GraphicalFigure {
	private int id;
	private int width;
	private int height;
	private String type;
	private Location pos;
	private static BinarySearchTree tree;

	
	//constructor that creates a Graphical Figure 
	public GraphicalFigure (int id, int width, int height, String type, Location pos) {
		this.id=id;
		this.width=width;
		this.height = height;
		this.type=type;
		this.pos=pos;
		this.tree = new BinarySearchTree();

	}
	
	//Sets the type of this figure to the specified value.
	public void setType (String type) {
		this.type=type;
	}
	
	//Returns the width of the enclosing rectangle for this figure.
	public int getWidth () {
		return width;
	}
	
	//Returns the height of the enclosing rectangle for this figure.
	public int getHeight() {
		return height;
	}
	
	//Returns the type of this figure.
	public String getType () {
		return type;
	}
	
	// Returns the id of this figure.
	public int getId() {
		return id;
	}
	
	//Returns the offset of this figure.
	public Location getOffset() {
		return pos;
	}
	
	//Changes the offset of this figure to the specified value
	public void setOffset(Location value) {
		this.pos=value;
	}
	
	// Inserts pix into the binary search tree associated with this figure
	public void addPixel(Pixel pix) throws DuplicatedKeyException{
		this.tree.put(this.tree.getRoot(), pix);
	}
	


	//Returns true if this figure intersect the one specified in the parameter. It returns false otherwise.
	public boolean intersects (GraphicalFigure gobj) {
		if(rectangle(gobj)) {
			BinaryNode r =this.tree.getRoot();
			Pixel small = this.tree.smallest(this.tree.getRoot());
			Pixel large = this.tree.largest(this.tree.getRoot());
			Pixel cur = small;
			while (cur !=large) {
				if(findOffsetPix(cur, gobj.getOffset())) {
					return true;
				}
				cur= this.tree.successor(r, cur.getLocation());
			}
			return true;
		}
		return false;
	}
	
	//finds the pixel location of f' 
	private boolean findOffsetPix(Pixel pix, Location offset) {
		int y = pix.getLocation().yCoord() + this.getOffset().yCoord() - offset.yCoord();
		int x = 	pix.getLocation().xCoord() + this.getOffset().xCoord() - offset.xCoord();
		return findPixel(new Location(x,y));
	}
	
	//helper method that checks to see if the rectangles intersect
	private boolean rectangle(GraphicalFigure gobj) {
		int thisX = this.getOffset().xCoord();
		int thisY = this.getOffset().yCoord();
		return thisX < gobj.getOffset().xCoord() + gobj.getWidth() && 
				thisX + this.getWidth() > gobj.getOffset().xCoord() && 
					thisY < gobj.getOffset().yCoord() + gobj.getHeight() && 
						thisY + this.getHeight() > gobj.getOffset().yCoord();
	}
	
	//helper method that returns true if there is a pixel at the location and false otherwise
	private boolean findPixel(Location p) {
		Pixel pix = tree.get(tree.getRoot(),p);
		if(pix!=null) {
			return true;
		}
		else {
			return false;
		}
		
	}

}
