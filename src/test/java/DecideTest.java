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
     * testLic5 checks if decide returns true if there exists two consecutive points such that X[j]-X[j-1] < 0, otherwise the function
     * should return false
     *
     * test 1
     * input: (1 1)(1 1)(1 1)(1 1)(2 1)
     * expected output: false
     *
     * test 2
     * input: (5 1)(4 1)(3 1)(2 1)(-1 1)
     * expected output: true
     *
     * test 3
     * input: (1 1)(1 1)(1 1)(1 1)(1 1)
     * expected output: false
     */
    @Test
    void testLic5(){
        Decide decide = new Decide();

        //Test 1
        ArrayList<Point> test1 = new ArrayList<>();
        test1.add(new Point(1,1));
        test1.add(new Point(1,1));
        test1.add(new Point(1,1));
        test1.add(new Point(1,1));
        test1.add(new Point(2,1));

        decide.points = test1;
        assertFalse(decide.lic5());

        //Test 2
        ArrayList<Point> test2 = new ArrayList<>();
        test2.add(new Point(5,1));
        test2.add(new Point(4,1));
        test2.add(new Point(3,1));
        test2.add(new Point(2,1));
        test2.add(new Point(-1,1));

        decide.points = test2;
        assertTrue(decide.lic5());

        //Test 3
        ArrayList<Point> test3 = new ArrayList<>();
        test3.add(new Point(1,1));
        test3.add(new Point(1,1));
        test3.add(new Point(1,1));
        test3.add(new Point(1,1));
        test3.add(new Point(1,1));

        decide.points = test3;
        assertFalse(decide.lic5());
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