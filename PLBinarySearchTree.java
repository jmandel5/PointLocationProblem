import java.util.ArrayList;

public class PLBinarySearchTree {
	private PLTreeNode root;
	private ArrayList<Line> allLines;
	public PLBinarySearchTree ( Line r) {
		root = new PLTreeNode();
		root.segment = r;
		allLines = new ArrayList<Line>();
	}
	public PLBinarySearchTree () {
		root = new PLTreeNode();
		allLines = new ArrayList<Line>();
	}
	public void insert( Line newline) {
		allLines.add(newline);
		for (int i = 0 ; i < allLines.size() - 1 ; i++) {
			newline.addIntersectionPoint(allLines.get(i));
		}
		for ( int i = 0 ; i < newline.intersections.size() -1 ; i++ ) {
			root.insert(new Line(newline.intersections.get(i), newline.intersections.get(i+1) ));	
		}
	}
	public String SameRegion (Point a , Point b) {
		if ( this.getPath(a).indexOf("C") >=0 || this.getPath(b).indexOf("C") >=0  ) {
			return "Can not compare, one of these points lies on a line!";
		}
		else if ( this.getPath(a).equals(this.getPath(b))) {
			return "In the same region";
		}
		else {
			return "Not in the same region. Here is a line segment that separates them: " + root.findFirstSeparation(a,b);
			
		}
	}
	public String getPath(Point p ) {	
		String g = root.getPath(p);
		//System.out.println(g);
		return g;
	}
	public int countExternalNodes() {
		return root.countExternalNodes();
	}
	public int countTotalPathLength() {
		return root.countTotalPathLength();
	}
	public double averagePathLength() {
			return (double)  root.countTotalPathLength()/root.countExternalNodes();
	}
	public void printInOrder() {
		root.printInOrder();
	}
	
}
