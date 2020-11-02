
public class Point {
	public double x;
	public double y;
	public Point ( double a, double b) {
		x = a;
		y = b;
	}
	public Point () {
		x = 0;
		y = 0;
	}
	public String toString () {
		return (int) (x * 1000.0 ) / 1000.0 + " " + (int) (y * 1000.0 ) / 1000.0;
	}
	public boolean equals (Point p) {
		if ( this.x == p.x && this.y == p.y) {
			return true;
		}
		return false;
	}
}
