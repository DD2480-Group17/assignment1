import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;
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
        ArrayList<Point2D.Double> points1 = new ArrayList<>();
        points1.add(new Point2D.Double(0, 0));
        points1.add(new Point2D.Double(0, 1));
        points1.add(new Point2D.Double(0, 2));

        ArrayList<Point2D.Double> points2 = new ArrayList<>();
        points2.add(new Point2D.Double(0, 0));
        points2.add(new Point2D.Double(0, 2));
        points2.add(new Point2D.Double(0, 1));

        ArrayList<Point2D.Double> points3 = new ArrayList<>();
        points3.add(new Point2D.Double(0, 0));
        points3.add(new Point2D.Double(1, 1));

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
        ArrayList<Point2D.Double> test1 = new ArrayList<>();
        test1.add(new Point2D.Double(1,1));
        test1.add(new Point2D.Double(1,1));
        test1.add(new Point2D.Double(1,1));
        test1.add(new Point2D.Double(1,1));
        test1.add(new Point2D.Double(2,1));

        decide.points = test1;
        assertFalse(decide.lic5());

        //Test 2
        ArrayList<Point2D.Double> test2 = new ArrayList<>();
        test2.add(new Point2D.Double(5,1));
        test2.add(new Point2D.Double(4,1));
        test2.add(new Point2D.Double(3,1));
        test2.add(new Point2D.Double(2,1));
        test2.add(new Point2D.Double(-1,1));

        decide.points = test2;
        assertTrue(decide.lic5());

        //Test 3
        ArrayList<Point2D.Double> test3 = new ArrayList<>();
        test3.add(new Point2D.Double(1,1));
        test3.add(new Point2D.Double(1,1));
        test3.add(new Point2D.Double(1,1));
        test3.add(new Point2D.Double(1,1));
        test3.add(new Point2D.Double(1,1));

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
        Point2D.Double point1 = new Point2D.Double();
        Point2D.Double point2 = new Point2D.Double();

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
    
    /**
     * Tests that LIC3 returns true given that there is at least one set of 3 consecutive
     * points that constitute a triangle with area > area1. And that LIC3 returns false when
     * there is no such set.
     */
    @Test
    void testLIC3() {
    	// test 1: (area of one triangle == 4.0) && (area1 == 3.0). This should lead to
    	// expected value of true.
    	Decide d = new Decide();
    	
    	ArrayList<Point2D.Double> points = new ArrayList<>();
    	points.add(new Point2D.Double(0, 0));
    	points.add(new Point2D.Double(0, 4));
    	points.add(new Point2D.Double(2, 0));
    	
    	Parameters ps = new Parameters();
    	ps.area1 = 3.0;
    	
    	d.points = points;
    	d.parameters = ps;
    	
    	assertTrue(d.lic3());
    	
    	//-------------------------------------
    	
    	// test 1: (area of one triangle == 4.0) && (area1 == 4.0). This should lead to
    	// expected value of false.
    	ps.area1 = 4.0;
    	
    	assertFalse(d.lic3());
    }
    
    /**
     * Tests that lic4 returns true when it exists qpts points that is located in more the quad quadrants.
     *
     * test 1:
     * qpts = 3
     * input (0 0)(-1 0)(0 -1)(0 1)(1 0)
     * (0 0) = quadrant 1, (-1 0) = quadrant 2, (0 -1) = quadrant 3, (0 1) = quadrant 1, (1 0)= quadrant 1
     * test for quads 3, 2, 1 with expected output false true true
     *
     * test 2:
     * input (1 1)(-1 1)(-1 -1)(1 -1)
     * qpts = 4
     * (1 1) = quadrant 1, (-1 1) = quadrant 2, (-1 -1) = quadrant 3, (1 -1) = quadrant 4
     * test for quads 3, 2, 1 with expected output true true true true
     *
     * test 3:
     * input (1 1)(1 1)(1 1)(-1 1)(1 1)(1 1)(-1 -1)(1 -1)
     * (1 1) = quadrant 1, (-1 1) = quadrant 2, (-1 -1) = quadrant 3, (1 -1) = quadrant 4
     * test for quads 2, qpts 2, 3, 4, 5, 6, 7, 8 with expected output false true true true true true true true true
     */
    @Test
    void testLic4() {
        Decide decide = new Decide();
        decide.parameters = new Parameters();

        //Test 1
        ArrayList<Point2D.Double> test1 = new ArrayList<>();
        test1.add(new Point2D.Double(0, 0));
        test1.add(new Point2D.Double(-1, 0));
        test1.add(new Point2D.Double(0, -1));
        test1.add(new Point2D.Double(0, 1));
        test1.add(new Point2D.Double(1, 0));

        decide.parameters.qPts = 3;
        decide.points = test1;

        //checks if it exist 3 consecutive points in more then 3 quadrants
        decide.parameters.quads = 3;
        assertFalse(decide.lic4());

        //checks if it exist 3 consecutive points in more then i quadrants
        for (int i = 2; i >= 1; i--) {
            decide.parameters.quads = i;
            assertTrue(decide.lic4());
        }

        //Test 2
        ArrayList<Point2D.Double> test2 = new ArrayList<>();

        test2.add(new Point2D.Double(1, 1));
        test2.add(new Point2D.Double(-1, 1));
        test2.add(new Point2D.Double(-1, -1));
        test2.add(new Point2D.Double(1, -1));

        decide.points = test2;
        decide.parameters.qPts = 4;
        //checks if it exist 4 consecutive points in more then i quadrants
        for (int i = 3; i >= 1; i--) {
            decide.parameters.quads = i;
            assertTrue(decide.lic4());
        }

        //Test 3
        ArrayList<Point2D.Double> test3 = new ArrayList<>();

        test3.add(new Point2D.Double(1, 1));
        test3.add(new Point2D.Double(1, 1));
        test3.add(new Point2D.Double(1, 1));
        test3.add(new Point2D.Double(-1, 1));
        test3.add(new Point2D.Double(1, 1));
        test3.add(new Point2D.Double(1, 1));
        test3.add(new Point2D.Double(-1, -1));
        test3.add(new Point2D.Double(1, -1));

        decide.points = test3;
        decide.parameters.quads = 2;

        //checks if it exist 2 consecutive points
        decide.parameters.qPts = 2;
        assertFalse(decide.lic4());

        //checks if it exist i consecutive points
        for (int i = 3; i <=8; i++) {
            decide.parameters.qPts = i;
            assertTrue(decide.lic4());
        }

    }

    /**
     * Tests that lic0 returns true if there exists at least one set of two data points separated by exactly kPts
     * consecutive intervening points that are a distance greater than the length,LENGTH1, apart.
     * The condition is not met when numPoints < 3.
     *
     * Test case 1:
     * points = (0, 0), (0, 0), (0, 2), (0, 1)
     * length1 = 1
     * Expected value: false
     *
     * Test case 2:
     * points = (0, 0), (0, 0), (0, 0), (0, 2)
     * length1 = 1
     * Expected value: true
     */
    @Test
    void testLic7() {
        Decide decide = new Decide();
        Parameters parameters = new Parameters();
        decide.parameters = parameters;
        parameters.kPts = 2;
        parameters.length1 = 1.0;

        ArrayList<Point2D.Double> points1 = new ArrayList<>();
        points1.add(new Point2D.Double(0, 0));
        points1.add(new Point2D.Double(0, 0));
        points1.add(new Point2D.Double(0, 2));
        points1.add(new Point2D.Double(0, 1));

        ArrayList<Point2D.Double> points2 = new ArrayList<>();
        points2.add(new Point2D.Double(0, 0));
        points2.add(new Point2D.Double(0, 0));
        points2.add(new Point2D.Double(0, 0));
        points2.add(new Point2D.Double(0, 2));

        decide.points = points1;
        assertFalse(decide.lic7());

        decide.points = points2;
        assertTrue(decide.lic7());
    }
    /**
     * Tests that lic10 returns true if if there exists at least one set of three data points separated by exactly E_PTS and F_PTS
     * consecutive intervening points, respectively, that are the vertices of a triangle with area greater than AREA1
     *
     * Test case 1:
     * intput:
     * points = (-1, 0), (1, 0), (0, 5), (1, 1), (1, 0.5), (2, 0), (0.5, 0), (-0.5, 1)
     * epts = 2, fpts = 1, area1 = 6
     * Expected value: True
     *
     * Test case 2:
     * intput:
     * points = (-1, 0), (1, 0), (0, 5), (1, 1), (1, 0.5), (2, 0), (0.5, 0), (-0.5, 1)
     * epts = 2, fpts = 1, area1 = 6
     * Expected value: False
     *
     * Test case 3:
     * points = (-1, 0), (1, 0), (1, 1), (0, 5), (1, 0.5), (2, 0), (0.5, 0), (-0.5, 1)
     * epts = 2, fpts = 1, area1 = 6
     * Expected value: True
     */
    @Test
    void testLic10(){
        Decide decide = new Decide();
        Parameters parameters = new Parameters();

        //Test 1
        ArrayList<Point2D.Double> test1 = new ArrayList<>();
        test1.add(new Point2D.Double(-1,0));
        test1.add(new Point2D.Double(1,0));
        test1.add(new Point2D.Double(0,5));
        test1.add(new Point2D.Double(1,1));
        test1.add(new Point2D.Double(1,0.5));
        test1.add(new Point2D.Double(2,0));
        test1.add(new Point2D.Double(0.5,0));
        test1.add(new Point2D.Double(-0.5,1));

        parameters.ePts = 2;
        parameters.fPts = 1;
        parameters.area1 = 6;
        decide.numPoints = test1.size();
        decide.points = test1;
        decide.parameters = parameters;
        assertTrue(decide.lic10());

        //Test 2
        parameters.area1 = 8;
        assertFalse(decide.lic10());

        //Test 3
        ArrayList<Point2D.Double> test2 = new ArrayList<>();
        test2.add(new Point2D.Double(-1,0));
        test2.add(new Point2D.Double(1,0));
        test2.add(new Point2D.Double(1,1));
        test2.add(new Point2D.Double(0,5));
        test2.add(new Point2D.Double(1,0.5));
        test2.add(new Point2D.Double(2,0));
        test2.add(new Point2D.Double(0.5,0));
        test2.add(new Point2D.Double(-0.5,1));

        decide.points = test2;
        parameters.area1 = 6;
        assertTrue(decide.lic10());

    }

    /**
     * The test checks if the function returns the correct area for a triangle, by inputing the
     * points in different order.
     */
    @Test
    void testTriangelArea(){
        Decide decide = new Decide();

        Point2D.Double a = new Point2D.Double(-1,0);
        Point2D.Double b = new Point2D.Double(2,0);
        Point2D.Double c = new Point2D.Double(0,4);
        Point2D.Double d = new Point2D.Double(0,-3);

        assertEquals(6.0, decide.triangleArea(a,b,c));
        assertEquals(6.0, decide.triangleArea(b,a,c));
        assertEquals(6.0, decide.triangleArea(c,a,b));

        assertEquals(4.5, decide.triangleArea(a,b,d));
    }
}