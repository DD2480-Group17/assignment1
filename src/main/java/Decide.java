import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Decide {
    enum BOOLEAN_OPERATOR {
        ANDD,
        ORR,
        NOTUSED
    }

    ArrayList<Point2D.Double> points;
    Parameters parameters;
    BOOLEAN_OPERATOR[][] lcm;
    boolean[] puv;


    /**
     * Empty constructor
     */
    public Decide() {

    }

    /**
     * Constructor
     * @param points 2D planar point
     * @param parameters 19 input parameters
     * @param lcm logical connector matrix
     * @param puv preliminary unlocking vector
     */
    public Decide(ArrayList<Point2D.Double> points, Parameters parameters, BOOLEAN_OPERATOR[][] lcm, boolean[] puv) {
        this.points = points;
        this.parameters = parameters;
        this.lcm = lcm;
        this.puv = puv;
    }

    /**
     * Launch Interceptor Condition 0
     *
     * @return true if there exists at least one set of two consecutive data points that are a distance greater than
     * the length, length1, apart.
     */
    boolean lic0() {
        for (int i = 1; i < points.size(); i++) {
            if (dist(points.get(i - 1), points.get(i)) > parameters.length1) {
                return true;
            }
        }
        return false;
    }
    /**
     * Returns true if there is at least one set of three consecutive data points
     * in points Arraylist that are the vertices of a triangle with area > AREA1 (which is parameters.area1).
     * Otherwise, return false.
     * 
     * @return true if there is at least one set of three consecutive data points
     * in points Arraylist that are the vertices of a triangle with area > AREA1 (which is parameters.area1).
     * Otherwise, false.
     */
    boolean lic3() {
    	// The formula used to calculate the area of the triangle comes from
    	// https://www.mathopenref.com/coordtrianglearea.html.
    	
    	double area1 = parameters.area1;
    	// begin from index 2 (3rd position in points arraylist)
    	// and check two steps backwards.
    	for (int i = 2; i < points.size(); i++) {
    		
    		Point2D point0 = points.get(i-2);
    		Point2D point1 = points.get(i-1);
    		Point2D point2 = points.get(i);
    		
    		double triangleArea = Math.abs(	(
    										point0.getX() * (point1.getY() - point2.getY()) + 
    										point1.getX() * (point2.getY() - point0.getY()) +
    										point2.getX() * (point0.getY() - point1.getY())
    										) / 2.0 );
    		
    		if (triangleArea > area1)
    			return true;
    	}
    	
    	return false;
    }
    /**
     * lic4 checks if it exists QPTS consecutive data points in more then QUADS unique quadrants, where , the data point (0,0)
     * is in quadrant I, the point (-l,0) is in quadrant II, the point (0,-l) is in quadrant III, the point
     * (0,1) is in quadrant I and the point (1,0) is in quadrant I     *
     *
     * @return the function returns true if it exists QPTS consecutive data points in more then QUADS unique quadrants
     */
    boolean lic4() {
        //checks if qPts or quads is in a undefined area for this function

        if (parameters.qPts < 2 || parameters.qPts > points.size()
                || parameters.quads < 1 || parameters.quads > 3) {
            return false;
        }
        //It is not possible for a point to be in more then one quadrant
        else if (parameters.qPts < parameters.quads) {
            return false;
        }

        //check if there exists a qpts consecutive set of data points
        for(int i = 0; i + parameters.qPts-1 < points.size(); i++) {

            //check if the data set contains more then quad quads
            boolean[] foundQuads = new boolean[4];
            for(int j = 0; j < parameters.qPts; j++){

                Point2D.Double point = points.get(i+j);

                //Is the point in quadrant 1
                if (point.x >= 0 && point.y >= 0) {
                    foundQuads[0] = true;
                }
                //Is the point in quadrant 2
                else if (point.y >= 0) {
                    foundQuads[1] = true;
                }
                //Is the point in quadrant 3
                else if (point.x <= 0) {
                    foundQuads[2] = true;
                }
                //The point is in quadrant 4
                else {
                    foundQuads[3] = true;
                }
            }
            int numberOfquadrants = 0;
            //count number of unique quadrants for this data set
            for (int z = 0; z < foundQuads.length; z++) {
                if (foundQuads[z]) {
                    numberOfquadrants++;
                }
            }
            //if we have point in more the quads quadrants return true
            if(numberOfquadrants > parameters.quads){
                return true;
            }
        }
        return false;
    }

    /**
     * Launch Interceptor Condition 5
     * @return true if there exists one set of two consecutive data points such that X[j]-X[j-1] < 0
     */
    boolean lic5(){
        for(int j = 1; j < points.size(); j++){
            if((points.get(j).x - points.get(j-1).x) < 0){
                return true;
            }
        }
        return false;
    }

    /**
     * Calculates the distance between two points in euclidian space.
     *
     * @param point1 the first point
     * @param point2 the second point
     * @return the euclidian distance between two points
     */
    double dist(Point2D.Double point1, Point2D point2) {
        return Math.sqrt(Math.pow(point1.getX() - point2.getX(), 2) + Math.pow(point1.getY() - point2.getY(), 2));

    }

    /**
     * Launch Interceptor Condition 7
     * @return true if there exists at least one set of two data points separated by exactly kPts consecutive
     * intervening points that are a distance greater than the length,LENGTH1, apart.
     * The condition is not met when numPoints < 3.
     */
    boolean lic7() {
        for (int i = parameters.kPts + 1; i < points.size(); i++) {
            if (points.size() < 3) {
                return false;
            }
            if (dist(points.get(i - parameters.kPts - 1), points.get(i)) > parameters.length1) {
                return true;
            }
        }
        return false;
    }

    /**
     * Launch Interceptor Condition 10
     * @return true if There exists at least one set of three data points separated by exactly E_PTS and F_PTS
     * consecutive intervening points, respectively, that are the vertices of a triangle with area greater than AREA1
     */
    boolean lic10(){
        if(points.size() < 5)
            return false;
        for(int i = 0; i + parameters.ePts + parameters.fPts + 2 < points.size(); i++){
            if(triangleArea(points.get(i),points.get(i+parameters.ePts+1),points.get(i+parameters.ePts+parameters.fPts+2))> parameters.area1 ||
                    triangleArea(points.get(i),points.get(i+parameters.fPts+1),points.get(i+parameters.fPts+parameters.ePts+2))> parameters.area1){
                return true;
            }
        }
        return false;
    }

    /**
     * trianlgeArea calculates the area formed by 3 data points
     * @param point0
     * @param point1
     * @param point2
     * @return the area of the tringle formed by point0, point1 and point2
     */
    double triangleArea(Point2D.Double point0, Point2D.Double point1, Point2D.Double point2){
        return Math.abs((
                        point0.getX() * (point1.getY() - point2.getY()) +
                        point1.getX() * (point2.getY() - point0.getY()) +
                        point2.getX() * (point0.getY() - point1.getY()) ) / 2.0 );
    }
}
