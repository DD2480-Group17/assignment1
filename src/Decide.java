import java.awt.*;
import java.util.ArrayList;

public class Decide {
    int numPoints;
    ArrayList<Point> points;
    Parameters parameters;
    LCM lcm;
    PUV puv;

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
     * lcm4 checks if it exists data points in more then QUADS unique quadrants, where , the data point (0,0)
     * is in quadrant I, the point (-l,0) is in quadrant II, the point (0,-l) is in quadrant III, the point
     * (0,1) is in quadrant I and the point (1,0) is in quadrant I     *
     *
     * @return the function returns true if it exists data points in more then QUADS unique quadrants
     */
    public boolean lic4() {
        //checks if qPts or quads is in a undefined area for this function

        if (parameters.qPts < 2 || parameters.qPts > numPoints
                || parameters.quads < 1 || parameters.quads > 3) {
            return false;
        }
        //It is not possible for a point to be in more then one quadrant
        else if (parameters.qPts < parameters.quads) {
            return false;
        }

        boolean[] foundQuads = new boolean[4];
        //Loop through the data points and translate coordinates to a quadrant and set that quadrant to true.
        for (Point point : points) {
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
        //count number of unique quadrants
        for (int i = 0; i < foundQuads.length; i++) {
            if (foundQuads[i]) {
                numberOfquadrants++;
            }
        }
        //if we have point in more the quads quadrants return true
        return numberOfquadrants > parameters.quads;
    }
    /**
     * Calculates the distance between two points in euclidian space.
     *
     * @param point1 the first point
     * @param point2 the second point
     * @return the euclidian distance between two points
     */
    double dist(Point point1, Point point2) {
        return Math.sqrt(Math.pow(point1.getX() - point2.getX(), 2) + Math.pow(point1.getY() - point2.getY(), 2));

    }
}
