import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.*;

class UtilityTest {
    /**
     * Tests that dist returns the euclidian distance between two points.
     * <p>
     * Test case 1:
     * points: (0, 0), (1, 1)
     * Expected value: 1.414213562373095048801688724209698078569671875376948073176679
     * <p>
     * Test case 2:
     * points: (0, 0), (0, 0)
     * Expected value: 0
     * <p>
     * Test case 3:
     * points: (0, 0), (0, 1)
     * Expected value: 1
     */
    @Test
    void testDist() {

        Point2D.Double point1 = new Point2D.Double();
        Point2D.Double point2 = new Point2D.Double();

        point1.setLocation(0.0, 0.0);
        point2.setLocation(1.0, 1.0);
        assertEquals(1.414213562373095048801688724209698078569671875376948073176679, Utility.dist(point1, point2));

        point1.setLocation(0.0, 0.0);
        point2.setLocation(0.0, 0.0);
        assertEquals(0, Utility.dist(point1, point2));

        point1.setLocation(0.0, 0.0);
        point2.setLocation(0.0, 1.0);
        assertEquals(1.0, Utility.dist(point1, point2));

    }

    /**
     * Tests that canContainPoints correctly returns true if the three points can not be contained
     * in a circle with radius1
     * <p>
     * Test case: radius1 = sqrt(2)
     * Expected value: false
     * <p>
     * Test case: radius1 = 1.4
     * Expected value: true
     */
    @Test
    void testCanContainPoints() {
        Point2D point1 = new Point2D.Double(1, 1);
        Point2D point2 = new Point2D.Double(-1, 1);
        Point2D point3 = new Point2D.Double(-1, -1);

        Decide decide = new Decide();
        decide.parameters = new Parameters();

        decide.parameters.radius1 = Math.sqrt(2);
        assertFalse(Utility.canNotContainPoints(point1, point2, point3, decide.parameters.radius1));

        decide.parameters.radius1 = 1.4;
        assertTrue(Utility.canNotContainPoints(point1, point2, point3, decide.parameters.radius1));
    }

    /**
     * The test checks if the function returns the correct area for a triangle, by inputing the
     * points in different order.
     */
    @Test
    void testTriangelArea() {

        Point2D.Double a = new Point2D.Double(-1, 0);
        Point2D.Double b = new Point2D.Double(2, 0);
        Point2D.Double c = new Point2D.Double(0, 4);
        Point2D.Double d = new Point2D.Double(0, -3);

        assertEquals(6.0, Utility.triangleArea(a, b, c));
        assertEquals(6.0, Utility.triangleArea(b, a, c));
        assertEquals(6.0, Utility.triangleArea(c, a, b));

        assertEquals(4.5, Utility.triangleArea(a, b, d));
    }


    /**
     * Tests that distPointLine returns the distance between a point and a line.
     * Test case 1:
     * points = (2, -1), (-2, 2), (-2, -1)
     * Expected value: 3.0
     * <p>
     * Test case 2:
     * points = (-2, 0), (0, 4), (2, 0)
     * Expected value: 4.0
     */
    @Test
    void testDistPointLine() {
        Point2D.Double point1 = new Point2D.Double(2, -1);
        Point2D.Double point2 = new Point2D.Double(-2, 2);
        Point2D.Double point3 = new Point2D.Double(-2, -1);

        assertEquals(3.0, Utility.distPointLine(point1, point2, point3));

        Point2D.Double point4 = new Point2D.Double(-2, 0);
        Point2D.Double point5 = new Point2D.Double(0, 4);
        Point2D.Double point6 = new Point2D.Double(2, 0);

        assertEquals(4.0, Utility.distPointLine(point4, point5, point6));
    }
}