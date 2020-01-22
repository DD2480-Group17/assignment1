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
     * Calculates the distance between two points in euclidian space.
     * @param point1 the first point
     * @param point2 the second point
     * @return the euclidian distance between two points
     */
    double dist(Point point1, Point point2) {
        return Math.sqrt(Math.pow(point1.getX() - point2.getX(), 2) + Math.pow(point1.getY() - point2.getY(), 2));
    }
}
