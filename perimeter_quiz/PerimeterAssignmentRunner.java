import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int pointNum = 0;
        for (Point p : s.getPoints()) {
            pointNum++;
        }
        return pointNum;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        double totalLen = 0.0;
        int sides = 0;
        Point prevPoint = s.getLastPoint();
        for (Point p : s.getPoints()) {
            totalLen += prevPoint.distance(p);
            sides++;
            prevPoint = p;
        }

        return totalLen / sides;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        double largestSide = 0.0;
        Point prevPoint = s.getLastPoint();
        for (Point p : s.getPoints()) {
            double curSide = prevPoint.distance(p);
            if (curSide > largestSide) {
        largestSide = curSide;
            }   
            prevPoint = p;
        }

    return largestSide;
    }

    public double getLargestX(Shape s) {
        double largestX = Double.MIN_VALUE;
        for (Point p : s.getPoints()) {
            if (p.getX() > largestX) {
            largestX = p.getX();
            }
        }

        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        DirectoryResource dr = new DirectoryResource();
        double largestP = Double.MIN_VALUE;

        for (File f : dr.selectedFiles()) {
            FileResource fileRescource = new FileResource(f);
            Shape s = new Shape(fileRescource);
            double p = this.getPerimeter(s);
            if (p > largestP) {
                largestP = p;
            }
        }
    
        return largestP;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        File temp = null;    // replace this code
        DirectoryResource dr = new DirectoryResource();
        double largestP = Double.MIN_VALUE;

        for (File f : dr.selectedFiles()) {
            FileResource fileRescource = new FileResource(f);
            Shape s = new Shape(fileRescource);
            double p = this.getPerimeter(s);
            if (p > largestP) {
                largestP = p;
                temp = f;
            }
        }
    
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        PerimeterAssignmentRunner runner = new PerimeterAssignmentRunner();
        System.out.println("shape has " + runner.getNumPoints(s) + " points");
        System.out.println("shape's sides average length is " + runner.getAverageLength(s));
        System.out.println("shape's largest side length is " + runner.getLargestSide(s));
        System.out.println("shape's largest X is " + runner.getLargestX(s));
        System.out.println("perimeter = " + length);
    }
    
    public void testPerimeterMultipleFiles() {
        System.out.println("largest primemter is " + this.getLargestPerimeterMultipleFiles());
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        System.out.println("largest primemter file is " + this.getFileWithLargestPerimeter());
    }
    
    public void testGetAverageLength() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            System.out.println(f.getName() + "'s average perimeter is " + this.getAverageLength(s));
        }
    }
    
        public void testGetLargestSide() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            System.out.println(f.getName() + "'s largest side is " + getLargestSide(s));
        }
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}
