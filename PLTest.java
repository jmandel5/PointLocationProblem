import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PLTest {
	public static void main ( String[] args ) throws IOException 
	{
		File file = new File(args[0]);
		Scanner in = new Scanner(file);
		
		//finds number of lines to insert
		double numLines = in.nextDouble();
		
		//Collects points for each line, and makes an array of lines
		ArrayList<Line> lines = new ArrayList<Line>();
		double x1,y1,x2,y2;
		Line l;
		for ( int i = 0 ; i < numLines ; i++) {
			x1 = in.nextDouble();
			y1 = in.nextDouble();
			x2 = in.nextDouble();
			y2 = in.nextDouble();
			if ( x1 == x2 && y1 == y2) {
				System.out.println("Invalid point combination for a line, points must be different! " + x1 + " " + y1 + " " + x2 + " " + y2);
			}
			else {
				l = new Line( new Point(x1,y1) , new Point(x2,y2));
				boolean colinear = false;
				for ( int j = 0 ; j < lines.size(); j++) {
					if (lines.get(j).equals(l)) {
						colinear = true;
						System.out.println("The line " + l + " trying to be inserted is colinear with line " + lines.get(j) );
					}
				}
				if ( colinear == false) {
					lines.add(l);
				}
			}
		}
		
		//Collects all point pairs to compare and puts them into an ArrayList
		ArrayList<Point> points = new ArrayList<Point>();
		while ( in.hasNextDouble()) {
			x1 = in.nextDouble();
			y1 = in.nextDouble();
			points.add(new Point(x1,y1));
		}	
		
		//Adds all lines to the PLBinarySearchTree
		PLBinarySearchTree p = new PLBinarySearchTree();
		for ( int i = 0 ; i < lines.size() ; i++) {
			p.insert(lines.get(i));
		}
		//Compares all pairs of points to see if they lie in the same region and displays result
		for ( int i = 0 ; i < points.size() - 1; i+=2) {
			System.out.println("Point pair " + (i/2 + 1) + ": " + p.SameRegion(points.get(i), points.get(i+1)));
			
		}	
		//Counts the number of external nodes
		System.out.println("Number of external nodes: " + p.countExternalNodes());
		//Counts the total path length
		System.out.println("Total path length over all paths: " + p.countTotalPathLength());
		//Calculates the average path length
		System.out.println("Average path length: " + (int) (p.averagePathLength() * 100.0 ) / 100.0);
	} 
}
