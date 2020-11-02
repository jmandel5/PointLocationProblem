import java.util.ArrayList;

public class Line  {
	public Point p1;
	public Point p2;
	public double slope;
	public boolean infiniteSlope;
	public ArrayList<Point> intersections;
	public static final int CLOCKWISE = 1;
	public static final int COUNTERCLOCKWISE = -1;
	public static final int COLINEAR = 0;
	public Line (Point a, Point b) {
		if ( b.y > a.y ) {
			 p1 = a;
			 p2 = b;
		}
		else if ( b.y == a.y) {
			if ( a.x <= b.x) {
				p1 = a;
				p2 = b;
			}
			else {
				p1 = b;
				p2 = a;
			}
		}
		else {
			p1 = b;
			p2 = a;
		}
		if ( p1.x == p2.x) {
			infiniteSlope = true;
		}
		else {
			infiniteSlope = false;
			slope = ( p2.y - p1.y) / ( p2.x - p1.x );
		}
		intersections = new ArrayList<Point>();
		intersections.add(p1);
		intersections.add(p2);
	}
	public String toString() {
		return p1.toString() + "  " + p2.toString(); 
	}
	public void addIntersectionPoint( Line newline) {
		Point intpoint = new Point();
		if ( this.infiniteSlope == false && newline.infiniteSlope == false ) {
			if (this.slope == newline.slope) {
				return;
			}  
			else {
				intpoint.x = ( p1.y - newline.p1.y + newline.slope * newline.p1.x - this.slope * p1.x )/ ( newline.slope - this.slope);
				intpoint.y = p1.y + slope * ( intpoint.x - p1.x );
			}
		}
		else if (this.infiniteSlope == true && newline.infiniteSlope == false ) {
			intpoint.x = this.p1.x;
			intpoint.y = newline.p1.y + newline.slope * (intpoint.x - newline.p1.x);
		}
		else if (this.infiniteSlope == false && newline.infiniteSlope == true) {
			intpoint.x = newline.p1.x;
			intpoint.y = this.p1.y + this.slope * (intpoint.x - this.p1.x);
		}
		else  {
			return;
		}
		int count = 1;
		if ( 0 <= intpoint.x && intpoint.x <= 1 && 0 <= intpoint.y && intpoint.y <= 1) {
			while ( count <= intersections.size() - 1 && intpoint.y > intersections.get(count).y ) {
				count++;
			}
			if ( intpoint.y != intersections.get(count).y) {
				intersections.add(count, intpoint);
			}
		}
	}
	public int ccw(Point p3) {
		double dx1 = p2.x - p1.x;
		double dy1 = p2.y - p1.y;
		double dx2 = p3.x - p1.x;
		double dy2 = p3.y - p1.y;
		if (dx1*dy2 > dy1*dx2) return -1;
		else if (dx1*dy2 < dy1*dx2) return 1;
		else if ((dx1*dx2 < 0) || (dy1*dy2 < 0)) return 1;
		else if ((dx1*dx1+dy1*dy1) < (dx2*dx2+dy2*dy2)) return -1;
		else return 0;
	}	
	public boolean equals(Line l) {
		if ( l.p1.equals(this.p1) && l.p2.equals(this.p2)) {
			return true;
		}
		return false;
	}
}
