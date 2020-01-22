import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DecideTest {
    /**
     * Tests that lic0 returns true if there exists at least one set of two consecutive data points that are a distance
     * greater than the length, length1, apart, else false.
     *
     * Test case 1:
     * points = (0, 0), (0, 1), (0, 2)
     * length1 = 1
     * Expected value: false
     *
     * Test case 2:
     * points = (0, 0), (0, 2), (0, 1)
     * length1 = 1
     * Expected value: true
     *
     * Test case 3:
     * points = (0, 0), (1, 1)
     * length1 = 1
     * Expected value: true
     */
    @Test
    void testLic0() {
        ArrayList<Point> points1 = new ArrayList<>();
        points1.add(new Point(0, 0));
        points1.add(new Point(0, 1));
        points1.add(new Point(0, 2));

        ArrayList<Point> points2 = new ArrayList<>();
        points2.add(new Point(0, 0));
        points2.add(new Point(0, 2));
        points2.add(new Point(0, 1));

        ArrayList<Point> points3 = new ArrayList<>();
        points3.add(new Point(0, 0));
        points3.add(new Point(1, 1));

        Decide decide = new Decide();
        decide.parameters = new Parameters();
        decide.parameters.length1 = 1.0;

        decide.points = points1;
        assertFalse(decide.lic0());

        decide.points = points2;
        assertTrue(decide.lic0());

        decide.points = points3;
        assertTrue(decide.lic0());
    }

    /**
     * Tests that dist returns the euclidian distance between two points.
     *
     * Test case 1:
     * points: (0, 0), (1, 1)
     * Expected value: 1.414213562373095048801688724209698078569671875376948073176679
     *
     * Test case 2:
     * points: (0, 0), (0, 0)
     * Expected value: 0
     *
     * Test case 3:
     * points: (0, 0), (0, 1)
     * Expected value: 1
     */
    @Test
    void testDist() {
        Decide decide = new Decide();
        Point point1 = new Point();
        Point point2 = new Point();

        point1.setLocation(0.0, 0.0);
        point2.setLocation(1.0, 1.0);
        assertEquals(1.414213562373095048801688724209698078569671875376948073176679, decide.dist(point1, point2));

        point1.setLocation(0.0, 0.0);
        point2.setLocation(0.0, 0.0);
        assertEquals(0, decide.dist(point1, point2));

        point1.setLocation(0.0, 0.0);
        point2.setLocation(0.0, 1.0);
        assertEquals(1.0, decide.dist(point1, point2));
    }
}