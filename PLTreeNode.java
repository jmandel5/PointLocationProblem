
public class PLTreeNode {
	public Line segment;
	public PLTreeNode leftChild;
	public PLTreeNode rightChild;
	public PLTreeNode() {
		segment = null;
		leftChild = null;
		rightChild = null;
	}
	public void insert( Line newline) {
		//If segment is null
		if ( segment == null) {
			segment = newline;
		}
		//If segment is counterclockwise of current segment 
		else if ( segment.ccw(newline.p1) == Line.COUNTERCLOCKWISE || segment.ccw(newline.p2) == Line.COUNTERCLOCKWISE   ) {
			if ( rightChild != null) {
				rightChild.insert(newline);
			}
			else {
				rightChild = new PLTreeNode();
				rightChild.segment = newline;
			}		
		}
		//If newline is clockwise of current segment
		else if ( segment.ccw(newline.p1) == Line.CLOCKWISE || segment.ccw(newline.p2) == Line.CLOCKWISE ) {
			if ( leftChild != null) {
				leftChild.insert(newline);
			}
			else {
				leftChild = new PLTreeNode();
				leftChild.segment = newline;
			}
		}	
		else {
			return;
		}
	}
	public String getPath (Point p) {
		if ( segment.ccw(p) == Line.COUNTERCLOCKWISE) {
			if ( rightChild != null) {	
				return "R"+  rightChild.getPath(p); 
			}
			else {
				return "R";
			}
		}
		else if (segment.ccw(p) == Line.CLOCKWISE)   {
			if ( leftChild != null ) {
				return "L" + leftChild.getPath(p) ;
			}
			else {
				return "L";
			}
		}
		else {
			return "C";
		}
	}
	public int countExternalNodes() {
		if ( leftChild != null && rightChild != null) {
			return leftChild.countExternalNodes() + rightChild.countExternalNodes();
		}
		if ( rightChild != null && leftChild == null) {
			return 1 + rightChild.countExternalNodes() ;
		}
		else if ( leftChild != null && rightChild == null) {
			return 1 + leftChild.countExternalNodes() ;
		}
		else {
			return 2;
		}
	}
	public int countTotalPathLength() {
		if ( leftChild != null && rightChild != null) {
			return leftChild.countExternalNodes() + rightChild.countExternalNodes() + leftChild.countTotalPathLength() + rightChild.countTotalPathLength();
		}
		if ( rightChild != null && leftChild == null) {
			return 1 + rightChild.countExternalNodes() + rightChild.countTotalPathLength() ;
		}
		else if ( leftChild != null && rightChild == null) {
			return 1 + leftChild.countExternalNodes() + leftChild.countTotalPathLength() ;
		}
		else {
			return 2;
		}
	}
	public Line findFirstSeparation(Point a, Point b  ) {
		if ( leftChild == null && rightChild == null) {
			return segment;
		}
		else if ( segment.ccw(a) == Line.CLOCKWISE && segment.ccw(b) == Line.COUNTERCLOCKWISE ) {
			return segment;
		}
		else if ( segment.ccw(a) == Line.COUNTERCLOCKWISE && segment.ccw(b) == Line.CLOCKWISE ) {
			return segment;
		}
		else if ( segment.ccw(a) == Line.CLOCKWISE && segment.ccw(b) == Line.CLOCKWISE) {
			return leftChild.findFirstSeparation(a,b);
		}
		else {
			return rightChild.findFirstSeparation(a,b);
		}
	}
	public void printInOrder() {
		
		if (rightChild != null) {
			rightChild.printInOrder();
		}	
		System.out.println(this.segment + " ");
		if ( leftChild != null) {
			leftChild.printInOrder();
		}
	}
}
