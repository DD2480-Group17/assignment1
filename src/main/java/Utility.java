import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Utility {

    /**
     * @param point1 point 1 used in smallest enclosing circle.
     * @param point2 point 2 used in smallest enclosing circle.
     * @param point3 point 3 used in smallest enclosing circle.
     * @param r wanted radius of the smallest enclosing circle.
     * @return true, if the three points can not be contained in a circle of radius r.
     */
    public static boolean canNotContainPoints(Point2D point1, Point2D point2, Point2D point3, double r) {
        ArrayList<Point> tempPoints = new ArrayList<>();
        tempPoints.add(new Point(point1));
        tempPoints.add(new Point(point2));
        tempPoints.add(new Point(point3));
        double radius = SmallestEnclosingCircle.makeCircle(tempPoints).r;
        return radius > r;
    }

    /**
     * Returns angle (in radians) that is formed between 3 points. 0 <= angle < 2pi.
     * Angle is calculated counter-clockwise from vector point1 -> point0 towards
     * vector point1 -> point2.
     *
     * @param point0 one point.
     * @param point1 vertex.
     * @param point2 another point.
     * @return angle (in radians) formed by point0, point1 and point2. 0 <= angle <
     * 2pi. Angle is calculated counter-clockwise from vector point1 ->
     * point0 towards vector point1 -> point2.
     */
    public static double calcAngle(Point2D point0, Point2D point1, Point2D point2) {
        // the code used here is inspired by
        // https://medium.com/@manivannan_data/find-the-angle-between-three-points-from-2d-using-python-348c513e2cd

        double point1To0_x = point0.getX() - point1.getX();
        double point1To0_y = point0.getY() - point1.getY();
        double point1To2_x = point2.getX() - point1.getX();
        double point1To2_y = point2.getY() - point1.getY();

        double angle = Math.atan2(point1To2_y, point1To2_x) - Math.atan2(point1To0_y, point1To0_x);
        // if angle is negative, add 2pi to angle.
        if (angle < 0)
            angle = angle + 2 * Math.PI;

        return angle;
    }

    /**
     * trianlgeArea calculates the area formed by 3 data points
     *
     * @param point0 one vertex of a triangle
     * @param point1 second vertex of a triangle
     * @param point2 third vertex of a triangle
     * @return the area of the tringle formed by point0, point1 and point2
     */
    public static double triangleArea(Point2D.Double point0, Point2D.Double point1, Point2D.Double point2) {
        return Math.abs((
                point0.getX() * (point1.getY() - point2.getY()) +
                        point1.getX() * (point2.getY() - point0.getY()) +
                        point2.getX() * (point0.getY() - point1.getY())) / 2.0);
    }

    /**
     * Calculates the distance between a single point and a line.
     *
     * @param startPoint the start point of the line
     * @param point the point
     * @param endPoint the end point of the line
     * @return the distance between the single point and the line
     */
    public static double distPointLine(Point2D.Double startPoint, Point2D.Double point, Point2D.Double endPoint) {
        double lineDist = dist(startPoint, endPoint);
        double pToStart = dist(point, startPoint);
        double pToEnd = dist(point, endPoint);

        //calcAngle() is calculating the angle clockwise -> the angle between startPoint and Point will therefore be 2*Pi - angle
        if (((2 * Math.PI) - (calcAngle(startPoint, endPoint, point))) >= (Math.PI / 2) || (calcAngle(endPoint, startPoint, point) >= (Math.PI / 2))) {

            if (pToStart > pToEnd) {
                return pToEnd;
            } else {
                return pToStart;
            }
        } else {
            //Heron's formula to find the area of the triangle that the point,
            //startPoint and endPoint form. The height of the triangle is equal to the distance
            //between the point and the line.
            double s = (0.5) * (lineDist + pToStart + pToEnd);
            double area = Math.sqrt(s * ((s - lineDist) * (s - pToStart) * (s - pToEnd)));
            double distPointLine = (2 * area) / lineDist;

            return distPointLine;
        }
    }

    /**
     * Calculates the distance between two points in euclidian space.
     *
     * @param point1 the first point
     * @param point2 the second point
     * @return the euclidian distance between two points
     */
    public static double dist(Point2D.Double point1, Point2D point2) {
        return Math.sqrt(Math.pow(point1.getX() - point2.getX(), 2) + Math.pow(point1.getY() - point2.getY(), 2));

    }
}
