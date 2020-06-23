
//This will create a tree whose root is a leaf node
public class BinarySearchTree implements BinarySearchTreeADT {
	
	private BinaryNode root;
	
	//constructor will create a tree whose root is a leaf node
	public BinarySearchTree() {
		this.root = new BinaryNode();
	}
	
	//Returns the Pixel storing the given key, if the key is stored in the tree
	public Pixel get(BinaryNode r, Location key){
		BinaryNode node = findNode(r,key);
		if (node ==null) {
			return null;
		}
		else {
			return node.getData();
		}
    }
	
	
	//helper method that returns the binary node storing the pixel 
	private BinaryNode findNode(BinaryNode r, Location p){
		if (r.isLeaf()) {
			return null;
		}
		else {
			int eval = p.compareTo(r.getData().getLocation());
			if(eval==0) {
				return r;
			}
			else if (eval == -1) {
				return findNode(r.getLeft(),p);
			}
			else if (eval == 1) {
				return findNode(r.getRight(),p);
			}
		}
		return null;
    }

	//Inserts the given data in the tree if no data item with the same key is already there
		public void put(BinaryNode r, Pixel data) throws DuplicatedKeyException{
			if(r.isLeaf()){
				r.setData(data);
				r.setLeft(new BinaryNode(null,null,null,r));
				r.setRight(new BinaryNode(null,null,null,r));
			}
			else {
				int val = data.getLocation().compareTo(r.getData().getLocation());
				if (val == -1) {
					put(r.getLeft(),data);
				}
				else if(val ==1) {
					put(r.getRight(),data);
				}
				else if (val ==0){
					throw new DuplicatedKeyException();
				}
			}
		}

	
	//Removes the data item with the given key, if the key is stored in the tree
	public void remove(BinaryNode r, Location key) throws InexistentKeyException{
		BinaryNode p = findNode(r,key);
		if (p==null) {
			throw new InexistentKeyException();
		}
		else if(!(p.isLeaf())) {
			if(p.getLeft().isLeaf()||p.getRight().isLeaf()) {
				BinaryNode parent = p.getParent();
				BinaryNode child;
				if (p.getLeft().isLeaf()) {
					 child = p.getRight();
				}
				else {
					 child = p.getLeft();
				}
				child.setParent(parent);
				if (p==root) {
					root.setData(child.getData());
				}
				else {
					if(parent.getLeft()==p) {
						parent.setLeft(child);
					}
					else {
						parent.setRight(child);
					}
				}
			}
			else {
				BinaryNode small = recSmall(p.getRight());
				p.setData(small.getData());
				remove(small,small.getData().getLocation());
			}
		}
	}
	
	//Returns the Pixel with the smallest key larger than the given one	
	public Pixel successor(BinaryNode r, Location key) {
		BinaryNode p = findNode(r,key);
		if (p == null) {
			return r.getData();
		}
		else{
			if ((!(p.getRight().isLeaf()))) {
					return recSmall(p.getRight()).getData();
			}
			else {
				BinaryNode parent = p.getParent();
				while ((p!=this.root)&&(parent.getRight()==p)) {
					p=parent;
					parent=p.getParent();

				}
				//returns root
				if (p==this.root) {
					return r.getData();
				}
				else {
					return parent.getData();
				}
			}
		}
	}
	
	//Returns the Pixel with the largest key smaller than the given one
	public Pixel predecessor(BinaryNode r, Location key) {
		BinaryNode p = findNode(r,key);
		if (p == null) {
			return r.getData();
		}
		else{
			if ((!(p.getLeft().isLeaf()))) {
					return recLarge(p.getLeft()).getData();
			}
			else {
				BinaryNode parent = p.getParent();
				while ((p!=this.root)&&(parent.getLeft()==p)) {
					p=parent;
					parent=p.getParent();

				}
				//returns root
				if (p==this.root) {
					return r.getData();
				}
				else {
					return parent.getData();
				}
			}
		}
	}
	
	//Returns the Pixel with the smallest key.
	public Pixel smallest(BinaryNode r) throws EmptyTreeException{	
		BinaryNode p = recSmall(r);
		if (p==null) {
			throw new EmptyTreeException();
		}
		return p.getData();
	}
	private BinaryNode recSmall(BinaryNode p) {
		if (p.isLeaf()) {
			return p.getParent();
		}
		return recSmall(p.getLeft());
	}
		
	//Returns the Pixel with the largest key
	public Pixel largest(BinaryNode r) throws EmptyTreeException{
		BinaryNode p = recLarge(r);
		if (p==null) {
			throw new EmptyTreeException();
		}
		return p.getData();
	}
	
	private BinaryNode recLarge(BinaryNode p) {
		if (p.isLeaf()) {
			return p.getParent();
		}
		return recSmall(p.getRight());
	}
	
	//Returns the root of the binary search tree.
	public BinaryNode getRoot() {
		return this.root;
	}
}
