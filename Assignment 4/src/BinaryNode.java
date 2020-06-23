//class represents the nodes of the binary search tree
public class BinaryNode {
	
	Pixel value;
	BinaryNode left;
	BinaryNode right;
	BinaryNode parent;

	// stores the Pixel in the node and sets left child, right child, and parent to the specified value
	public BinaryNode (Pixel value, BinaryNode left, BinaryNode right, BinaryNode parent) {
		this.value=value;
		this.left=left;
		this.right=right;
		this.parent=parent;
	}
	
	//constructor for the class that initializes a leaf node. The data, children and parent are set to null
	public BinaryNode () {
		this.value=null;
		this.left=null;
		this.right=null;
		this.parent=null;
	}
	
	//Returns the parent of this node
	public BinaryNode getParent(){
		return this.parent;
	}
	
	//Returns the Pixel object stored in this node.
	public Pixel getData () {
		return this.value;
	}
	
	//Returns the left child of this node
	public BinaryNode getLeft() {
		return this.left;
	}
	
	//Returns the right child of this node
	public BinaryNode getRight() {
		return this.right;
	}
	
	//sets the parent of this node to the specified value.
	public void setParent(BinaryNode parent) {
		this.parent=parent;
	}
		
	//Sets the left child of this node to the specified value
	public void setLeft (BinaryNode p) {
		this.left=p;
	}
	
	//Sets the right child of this node to the specified value
	public void setRight (BinaryNode p) {
		this.right=p;
	}
	
	//Stores the given Pixel in this node
	public void setData (Pixel value) {
		this.value=value;
	}
	
	//Returns true if this node is a leaf; returns false otherwise
	public boolean isLeaf() {
		if (this.value==null) {
			return true;
		}
		else {
			return false;
		}
	}

	
}
