import java.awt.*;
import java.util.ArrayList;

public class Decide {
    int numPoints;
    ArrayList<Point> points;
    Parameters parameters;
    LCM lcm;
    PUV puv;
    boolean[] CMV = new boolean[15];

    /**
     * Launch Interceptor Condition 0
     * @return true if there exists at least one set of two consecutive data points that are a distance greater than
     * the length, LENGTH1, apart.
     */
    private boolean lic0() {
        for (int i = 1; i < points.size(); i++) {
            if (dist(points.get(i - 1), points.get(i)) > parameters.length1) {
                return true;
            }
        }
        return false;
    }

    /**
     * Calculates the distance between two points in euclidian space.
     * @param point1 the first point
     * @param point2 the second point
     * @return the euclidian distance between two points
     */
    private double dist(Point point1, Point point2) {
        return Math.sqrt(Math.pow(point1.x - point2.x, 2) + Math.pow(point1.y - point2.y, 2));
    }
}
