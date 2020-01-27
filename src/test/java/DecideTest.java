import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class DecideTest {

    /**
     * Tests that lic0 returns true if there exists at least one set of two consecutive data points that are a distance
     * greater than the length, length1, apart, else false.
     * <p>
     * Test case 1:
     * points = (0, 0), (0, 1), (0, 2)
     * length1 = 1
     * Expected value: false
     * <p>
     * Test case 2:
     * points = (0, 0), (0, 2), (0, 1)
     * length1 = 1
     * Expected value: true
     * <p>
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
     * Tests that lic2 return true if there exists at least one set of three consecutive data points which form an angle such that:
     * angle < (PI − EPSILON)
     * or
     * angle > (PI + EPSILON)
     *
     * Test case:
     * Points: (1, 0), (0, 0), (cos(pi/4), sin(pi/4))
     * Epsilon: pi/2
     * Expected value: true
     */
    @Test
    void testLic2() {
        ArrayList<Point2D.Double> points = new ArrayList<>();
        points.add(new Point2D.Double(1, 0));
        points.add(new Point2D.Double(0, 0));
        points.add(new Point2D.Double(Math.cos(Math.PI / 4.0), Math.sin(Math.PI / 4.0)));

        Decide decide = new Decide();
        decide.points = points;
        decide.parameters = new Parameters();
        decide.parameters.epsilon = Math.PI / 2.0;

        assertTrue(decide.lic2());
    }

    /**
     * testLic5 checks if decide returns true if there exists two consecutive points such that X[j]-X[j-1] < 0, otherwise the function
     * should return false
     * <p>
     * test 1
     * input: (1 1)(1 1)(1 1)(1 1)(2 1)
     * expected output: false
     * <p>
     * test 2
     * input: (5 1)(4 1)(3 1)(2 1)(-1 1)
     * expected output: true
     * <p>
     * test 3
     * input: (1 1)(1 1)(1 1)(1 1)(1 1)
     * expected output: false
     */
    @Test
    void testLic5() {
        Decide decide = new Decide();

        //Test 1
        ArrayList<Point2D.Double> test1 = new ArrayList<>();
        test1.add(new Point2D.Double(1, 1));
        test1.add(new Point2D.Double(1, 1));
        test1.add(new Point2D.Double(1, 1));
        test1.add(new Point2D.Double(1, 1));
        test1.add(new Point2D.Double(2, 1));

        decide.points = test1;
        assertFalse(decide.lic5());

        //Test 2
        ArrayList<Point2D.Double> test2 = new ArrayList<>();
        test2.add(new Point2D.Double(5, 1));
        test2.add(new Point2D.Double(4, 1));
        test2.add(new Point2D.Double(3, 1));
        test2.add(new Point2D.Double(2, 1));
        test2.add(new Point2D.Double(-1, 1));

        decide.points = test2;
        assertTrue(decide.lic5());

        //Test 3
        ArrayList<Point2D.Double> test3 = new ArrayList<>();
        test3.add(new Point2D.Double(1, 1));
        test3.add(new Point2D.Double(1, 1));
        test3.add(new Point2D.Double(1, 1));
        test3.add(new Point2D.Double(1, 1));
        test3.add(new Point2D.Double(1, 1));

        decide.points = test3;
        assertFalse(decide.lic5());
    }

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
    void testLic3() {
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
     * <p>
     * test 1:
     * qpts = 3
     * input (0 0)(-1 0)(0 -1)(0 1)(1 0)
     * (0 0) = quadrant 1, (-1 0) = quadrant 2, (0 -1) = quadrant 3, (0 1) = quadrant 1, (1 0)= quadrant 1
     * test for quads 3, 2, 1 with expected output false true true
     * <p>
     * test 2:
     * input (1 1)(-1 1)(-1 -1)(1 -1)
     * qpts = 4
     * (1 1) = quadrant 1, (-1 1) = quadrant 2, (-1 -1) = quadrant 3, (1 -1) = quadrant 4
     * test for quads 3, 2, 1 with expected output true true true true
     * <p>
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
        for (int i = 3; i <= 8; i++) {
            decide.parameters.qPts = i;
            assertTrue(decide.lic4());
        }

    }

    /**
     * Tests that lic0 returns true if there exists at least one set of two data points separated by exactly kPts
     * consecutive intervening points that are a distance greater than the length,LENGTH1, apart.
     * The condition is not met when numPoints < 3.
     * <p>
     * Test case 1:
     * points = (0, 0), (0, 0), (0, 2), (0, 1)
     * length1 = 1
     * Expected value: false
     * <p>
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
     * Tests that lic8 returns true if there exists at least one set of three data points separated by exactly A PTS and B PTS
     * consecutive intervening points, respectively, that cannot be contained within or on a circle of
     * radius RADIUS1. The condition is not met when NUMPOINTS < 5.
     */
    @Test
    void testLic8() {
        Decide decide = new Decide();
        decide.parameters = new Parameters();

        ArrayList<Point2D.Double> points = new ArrayList<>();
        points.add(new Point2D.Double(1, 1));
        points.add(new Point2D.Double(0, 0));
        points.add(new Point2D.Double(-1, 1));
        points.add(new Point2D.Double(0, 0));
        points.add(new Point2D.Double(-1, -1));

        decide.points = points;
        decide.parameters.aPts = 1;
        decide.parameters.bPts = 1;

        decide.parameters.radius1 = 1.4;
        assertTrue(decide.lic8());

        decide.parameters.radius1 = Math.sqrt(2);
        assertFalse(decide.lic8());
    }

    /**
     * Tests that canContainPoints correctly returns true if the three points can not be contained
     * in a circle with radius1
     *
     * Test case: radius1 = sqrt(2)
     * Expected value: false
     *
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
        assertFalse(decide.canContainPoints(point1, point2, point3, decide.parameters.radius1));

        decide.parameters.radius1 = 1.4;
        assertTrue(decide.canContainPoints(point1, point2, point3, decide.parameters.radius1));
    }

	/**
	 * Tests that Lic9 returns true if there exists at least one set of three data
	 * points separated by exactly C_PTS and D_PTS consecutive intervening points,
	 * respectively, that form an angle such that: angle < (PI-EPSILON) or angle >
	 * (PI+EPSILON). The second point of the set of three points is always the vertex
	 * of the angle.
	 *
	 * It also tests that Lic9 returns false if either the first point or the last
	 * point (or both) coincide with the vertex.
	 *
	 * It also tests that Lic9 returns false if NUMPOINTS < 5.
	 *
	 * It is assumed that 1 <= C_PTS, 1 <= D_PTS, C_PTS+D_PTS <= NUMPOINTS-3.
	 */
	@Test
	void testLic9() {

		// test: Numpoints < 5
		// && C_PTS == 1 && D_PTS == 2
		// => lic9 returns false
		Decide d = new Decide();
		Parameters ps = new Parameters();

		ps.cPts = 1;
		ps.dPts = 2;
		ps.epsilon = Math.PI / 2.0;

		ArrayList<Point2D.Double> points = new ArrayList<>();
		points.add(new Point2D.Double(1, 0)); // first point
		points.add(new Point2D.Double(0, 0)); // second point
		points.add(new Point2D.Double(Math.cos(Math.PI / 4.0), Math.sin(Math.PI / 4.0))); // third point

		d.points = points;
		d.parameters = ps;

		assertFalse(d.lic9());

		// --------------------------------------------
		// test: Numpoints >= 5 && two non-vertex points coincide
		// && C_PTS == 1 && D_PTS == 2
		// => lic9 returns false
		d = new Decide();
		ps = new Parameters();

		ps.cPts = 1;
		ps.dPts = 2;
		ps.epsilon = Math.PI / 2.0;

		points = new ArrayList<>();
		points.add(new Point2D.Double(0, 0));
		points.add(new Point2D.Double(0, 0)); // first point
		points.add(new Point2D.Double(0, 0));
		points.add(new Point2D.Double(0, 0)); // second point
		points.add(new Point2D.Double(0, 0));
		points.add(new Point2D.Double(0, 0));
		points.add(new Point2D.Double(Math.cos(Math.PI / 4.0), Math.sin(Math.PI / 4.0))); // third point
		points.add(new Point2D.Double(0, 0));

		d.points = points;
		d.parameters = ps;

		assertFalse(d.lic9());

		// --------------------------------------------
		// test: Numpoints >= 5 && no two non-vertex points coincide && angle == pi/4 &&
		// epsilon == pi/2
		// && C_PTS == 1 && D_PTS == 2
		// => angle < pi - epsilon => lic9 returns true
		d = new Decide();
		ps = new Parameters();

		ps.cPts = 1;
		ps.dPts = 2;
		ps.epsilon = Math.PI / 2.0;

		points = new ArrayList<>();
		points.add(new Point2D.Double(0, 0));
		points.add(new Point2D.Double(1, 0)); // first point
		points.add(new Point2D.Double(0, 0));
		points.add(new Point2D.Double(0, 0)); // second point
		points.add(new Point2D.Double(0, 0));
		points.add(new Point2D.Double(0, 0));
		points.add(new Point2D.Double(Math.cos(Math.PI / 4.0), Math.sin(Math.PI / 4.0))); // third point
		points.add(new Point2D.Double(0, 0));

		d.points = points;
		d.parameters = ps;

		assertTrue(d.lic9());

		// ---------------------------------------
		// test: Numpoints >= 5 && no two points non-vertex coincide && angle == 7pi/4
		// && epsilon == pi/2 (which means that angle + epsilon == 3pi/2)
		// && C_PTS == 1 && D_PTS == 2
		// => angle > pi + epsilon => lic9 returns true

		d = new Decide();
		ps = new Parameters();

		ps.cPts = 1;
		ps.dPts = 2;
		ps.epsilon = Math.PI / 2.0;

		points = new ArrayList<>();
		points.add(new Point2D.Double(0, 0));
		points.add(new Point2D.Double(1, 0)); // first point
		points.add(new Point2D.Double(0, 0));
		points.add(new Point2D.Double(0, 0)); // second point
		points.add(new Point2D.Double(0, 0));
		points.add(new Point2D.Double(0, 0));
		points.add(new Point2D.Double(Math.cos(Math.PI / 4.0), -Math.sin(Math.PI / 4.0))); // third point
		points.add(new Point2D.Double(0, 0));

		d.points = points;
		d.parameters = ps;

		assertTrue(d.lic9());
	}

    /**
     * Preliminary Unlocking Matrix calculation test
     *
     * Tests that pum[0][0] and pum[0][1] are calculated correctly, given that
     * cmv[0] is true, lcm[0][0] is ANDD and lcm[0][1] is ORR
     */
	@Test
    void calcPumTest() {
        IOHandler ioHandler = new IOHandler();
        Decide decide = ioHandler.parseDecideInput(TestCases.test2);

        decide.calcPum();
        assertFalse(decide.pum[0][0]);

        decide.cmv[0] = true;
        decide.calcPum();
        assertTrue(decide.pum[0][0]);
        assertTrue(decide.pum[0][1]);
    }

    /**
     * Tests Launch Interceptor Condition 11
     * <p>
     * Test case:
     * Points: (2, 0), (0, 0), (0, 0), (1, 0)
     * gPts: 2
     * Expected value: true
     */
    @Test
    void testLic11() {
        ArrayList<Point2D.Double> points = new ArrayList<>();
        points.add(new Point2D.Double(2, 0));
        points.add(new Point2D.Double(0, 0));
        points.add(new Point2D.Double(0, 0));
        points.add(new Point2D.Double(1, 0));

        Decide decide = new Decide();
        decide.parameters = new Parameters();
        decide.parameters.gPts = 2;
        decide.points = points;

        assertTrue(decide.lic11());
    }

    /**
     * Tests that lic10 returns true if if there exists at least one set of three data points separated by exactly E_PTS and F_PTS
     * consecutive intervening points, respectively, that are the vertices of a triangle with area greater than AREA1
     * <p>
     * Test case 1:
     * intput:
     * points = (-1, 0), (1, 0), (0, 5), (1, 1), (1, 0.5), (2, 0), (0.5, 0), (-0.5, 1)
     * epts = 2, fpts = 1, area1 = 10
     * Expected value: False
     * <p>
     * Test case 2:
     * intput:
     * points = (-1, 0), (1, 0), (0, 5), (1, 1), (1, 0.5), (2, 0), (0.5, 0), (-0.5, 1)
     * epts = 2, fpts = 1, area1 = 6
     * Expected value: False
     * <p>
     * Test case 3:
     * points = (-1, 0), (1, 0), (1, 1), (0, 5), (1, 0.5), (2, 0), (0.5, 0), (-0.5, 1)
     * epts = 2, fpts = 1, area1 = 6
     * Expected value: True
     */
    @Test
    void testLic10() {
        Decide decide = new Decide();
        Parameters parameters = new Parameters();

        //Test 1
        ArrayList<Point2D.Double> test1 = new ArrayList<>();
        test1.add(new Point2D.Double(-1, 0));
        test1.add(new Point2D.Double(1, 0));
        test1.add(new Point2D.Double(0, 5));
        test1.add(new Point2D.Double(1, 1));
        test1.add(new Point2D.Double(1, 0.5));
        test1.add(new Point2D.Double(2, 0));
        test1.add(new Point2D.Double(0.5, 0));
        test1.add(new Point2D.Double(-0.5, 1));

        parameters.ePts = 2;
        parameters.fPts = 1;
        parameters.area1 = 10;
        decide.points = test1;
        decide.parameters = parameters;
        assertFalse(decide.lic10());

        //Test 2
        parameters.area1 = 8;
        assertFalse(decide.lic10());

        //Test 3
        ArrayList<Point2D.Double> test2 = new ArrayList<>();
        test2.add(new Point2D.Double(-1, 0));
        test2.add(new Point2D.Double(1, 0));
        test2.add(new Point2D.Double(1, 1));
        test2.add(new Point2D.Double(0, 5));
        test2.add(new Point2D.Double(1, 0.5));
        test2.add(new Point2D.Double(2, 0));
        test2.add(new Point2D.Double(0.5, 0));
        test2.add(new Point2D.Double(-0.5, 1));

        decide.points = test2;
        parameters.area1 = 6;
        assertTrue(decide.lic10());

    }

    /**
     * The test checks if the function returns the correct area for a triangle, by inputing the
     * points in different order.
     */
    @Test
    void testTriangelArea() {
        Decide decide = new Decide();

        Point2D.Double a = new Point2D.Double(-1, 0);
        Point2D.Double b = new Point2D.Double(2, 0);
        Point2D.Double c = new Point2D.Double(0, 4);
        Point2D.Double d = new Point2D.Double(0, -3);

        assertEquals(6.0, decide.triangleArea(a, b, c));
        assertEquals(6.0, decide.triangleArea(b, a, c));
        assertEquals(6.0, decide.triangleArea(c, a, b));

        assertEquals(4.5, decide.triangleArea(a, b, d));
    }
    
    /**
     * Tests if lic14 returns true if there exists at least one set of three data points, 
     * separated by exactly E_PTS and F_PTS consecutive
     * intervening points, respectively, that are the vertices of a triangle with area greater
     * than AREA1. In addition, there exist three data points (which can be the same or different
     * from the three data points just mentioned) separated by exactly E PTS and F PTS consecutive
     * intervening points, respectively, that are the vertices of a triangle with area less than
     * AREA2. Both parts must be true for the LIC to be true. Otherwise, return false.
     * Also, return false when NUMPOINTS < 5.
     */
    void testLic14() {
        // test: Numpoints < 5
        // && E_PTS == 1 && F_PTS == 2
        // => lic14 returns false
        Decide d = new Decide();
        Parameters ps = new Parameters();
        
        ps.area1 = 2;
        ps.area2 = 1;
        ps.ePts = 1;
        ps.fPts = 2;
        
        ArrayList<Point2D.Double> points = new ArrayList<>();
        points.add(new Point2D.Double(1, 0)); // first point
        points.add(new Point2D.Double(0, 0)); // second point
        points.add(new Point2D.Double(Math.cos(Math.PI / 4.0), Math.sin(Math.PI / 4.0))); // third point

        d.points = points;
        d.parameters = ps;

        assertFalse(d.lic14());

        // --------------------------------------------
        // test: Numpoints >= 5 && area1 == 2 && area2 == 0
        // && E_PTS == 1 && F_PTS == 2 && area of one triangle > 2 && no triangle area < 0 
        // => lic14 returns false
        d = new Decide();
        ps = new Parameters();

        ps.area1 = 2;
        ps.area2 = 0;
        ps.ePts = 1;
        ps.fPts = 2;
        
        points = new ArrayList<>();
        
        points.add(new Point2D.Double(0, 0));
        points.add(new Point2D.Double(6, 0)); // first point
        points.add(new Point2D.Double(0, 0));
        points.add(new Point2D.Double(0, 0)); // second point
        points.add(new Point2D.Double(0, 0));
        points.add(new Point2D.Double(0, 0));
        points.add(new Point2D.Double(0, 1)); // third point
        points.add(new Point2D.Double(0, 0));

        d.points = points;
        d.parameters = ps;

        assertFalse(d.lic14());

        // --------------------------------------------
        // test: Numpoints >= 5 && area1 == 2 && area2 == 1
        // && E_PTS == 1 && F_PTS == 2 && area of one triangle > 2 && another triangle area < 1 
        // => lic14 returns true
        d = new Decide();
        ps = new Parameters();

        ps.area1 = 2;
        ps.area2 = 1;
        ps.ePts = 1;
        ps.fPts = 2;
        
        points = new ArrayList<>();
        
        points.add(new Point2D.Double(1, 0)); // first point - less than (triangle), area == 0.5
        points.add(new Point2D.Double(6, 0)); // first point - large than (triangle), area == 3.0
        points.add(new Point2D.Double(0, 0)); // second point - less than (triangle), area == 0.5
        points.add(new Point2D.Double(0, 0)); // second point - large than (triangle), area == 3.0
        points.add(new Point2D.Double(0, 0));
        points.add(new Point2D.Double(0, 1)); // third point - less than (triangle), area == 0.5
        points.add(new Point2D.Double(0, 1)); // third point - large than (triangle), area == 3.0
        points.add(new Point2D.Double(0, 0));

        d.points = points;
        d.parameters = ps;

        assertTrue(d.lic14());
    }

    /**
     * Tests that lic6 returns true if there exists at least one set of two data points,
     * separated by exactly kPts consecutive intervening points, which are a distance greater than the length1,
     * apart AND there exists at least one set of two data points, separated by exactly kPts consecutive
     * intervening points, that are a distance less than the length2, apart.
     * The condition is not met when numPoints < 3.
     *
     * Test case 1:
     * points = (0, 1), (1, 0), (0, 2), (0, 0), (-1, 0), (1, 2), (2, 2)
     * length1 = 1
     * lenght2 = 1
     * kPts = 1
     * Expected value: false
     *
     * Test case 2:
     * points = (0, 1), (1, 0), (0, 2), (0, 0), (-1, 0), (1, 2), (2, 2)
     * length1 = 1
     * lenght2 = 5
     * kPts = 1
     * Expected value: true
     */
    @Test
    void testLic12() {
        ArrayList<Point2D.Double> points = new ArrayList<>();
        Parameters parameters = new Parameters();
        Decide decide1 = new Decide();
        parameters.kPts = 1;
        parameters.length1 = 1;
        parameters.length2 = 1;

        Point2D.Double p1 = new Point2D.Double(0, 1);
        Point2D.Double p2 = new Point2D.Double(1, 0);
        Point2D.Double p3 = new Point2D.Double(0, 2);
        Point2D.Double p4 = new Point2D.Double(0, 0);
        Point2D.Double p5 = new Point2D.Double(-1, 0);
        Point2D.Double p6 = new Point2D.Double(1, 2);
        Point2D.Double p7 = new Point2D.Double(2, 2);
        points.add(p1);
        points.add(p2);
        points.add(p3);
        points.add(p4);
        points.add(p5);
        points.add(p6);
        points.add(p7);

        decide1.parameters = parameters;
        decide1.points = points;

        assertFalse(decide1.lic12());

        Decide decide2 = new Decide();
        parameters.kPts = 1;
        parameters.length1 = 1;
        parameters.length2 = 5;

        decide2.parameters = parameters;
        decide2.points = points;

        assertTrue(decide2.lic12());
    }
    
    /**
     * Tests that calcFUV works correctly. i.e. for all i, FUV[i] is set to true if PUV[i] is false 
     * or if for all elements i,j, i!=j, in PUM row i are true.
     */
    @Test
    void testFUV() {
    	Decide d = new Decide();
    	boolean[] puv = new boolean[15];
    	boolean[][] pum = d.pum;
    	
    	d.puv = puv;
    	
    	// test case: puv[1] == false && for all i, pum[1][i] == false => fuv[1] = true; 
    	puv[1] = false;
    	d.calcFUV();
    	assertTrue(d.fuv[1]);
    	
    	// test case: puv[1] == true && for all i, pum[1][i] == false => fuv[1] = false; 
    	puv[1] = true;
    	d.calcFUV();
    	assertFalse(d.fuv[1]);

    	// test case: puv[1] == false && for all i, pum[1][i] == true => fuv[1] = true; 
    	puv[1] = false;
    	pum[1][0] = true;
    	pum[1][1] = true;
    	pum[1][2] = true;
    	pum[1][3] = true;
    	pum[1][4] = true;
    	pum[1][5] = true;
    	pum[1][6] = true;
    	pum[1][7] = true;
    	pum[1][8] = true;
    	pum[1][9] = true;
    	pum[1][10] = true;
    	pum[1][11] = true;
    	pum[1][12] = true;
    	pum[1][13] = true;
    	pum[1][14] = true;
    	
    	d.calcFUV();
    	assertTrue(d.fuv[1]);
    }
    
	/**
	 * Tests if true if There exists at least one set of three data points,
	 * separated by exactly A_PTS and B_PTS consecutive intervening points,
	 * respectively, that cannot be contained within or on a circle of radius
	 * RADIUS1. In addition, there exists at least one set of three data points
	 * (which can be the same or different from the three data points just
	 * mentioned) separated by exactly A_PTS and B_PTS consecutive intervening
	 * points, respectively, that can be contained in or on a circle of radius
	 * RADIUS2. Both parts must be true for the LIC to be true. Otherwise, return
	 * false. Also, return false when NUMPOINTS < 5.
	 */
    void testLic13() {
        // test: Numpoints < 5
        // && A_PTS == 1 && B_PTS == 2
        // => lic13 returns false
        Decide d = new Decide();
        Parameters ps = new Parameters();
        
        ps.radius1 = 2;
        ps.radius2 = 1;
        ps.aPts = 1;
        ps.bPts = 2;
        
        ArrayList<Point2D.Double> points = new ArrayList<>();
        points.add(new Point2D.Double(1, 0)); // first point
        points.add(new Point2D.Double(0, 0)); // second point
        points.add(new Point2D.Double(Math.cos(Math.PI / 4.0), Math.sin(Math.PI / 4.0))); // third point

        d.points = points;
        d.parameters = ps;

        assertFalse(d.lic13());

        // --------------------------------------------
        // test: Numpoints >= 5 && radius1 == 3 && radius2 == 0
        // && A_PTS == 1 && B_PTS == 2 && circle1 radius > 2 && no circle radius < 0 
        // => lic13 returns false
        d = new Decide();
        ps = new Parameters();

        ps.radius1 = 2;
        ps.radius2 = 0;
        ps.aPts = 1;
        ps.bPts = 2;
        
        points = new ArrayList<>();
        
        points.add(new Point2D.Double(0, 0));
        points.add(new Point2D.Double(3, 0)); // first point
        points.add(new Point2D.Double(0, 0));
        points.add(new Point2D.Double(0, 0)); // second point
        points.add(new Point2D.Double(0, 0));
        points.add(new Point2D.Double(0, 0));
        points.add(new Point2D.Double(0, 3)); // third point
        points.add(new Point2D.Double(0, 0));

        d.points = points;
        d.parameters = ps;

        assertFalse(d.lic13());

        // --------------------------------------------
        // test: Numpoints >= 5 && radius1 == 2 && radius2 == 1
        // && A_PTS == 1 && B_PTS == 2 && circle1 radius  > 2 && circle2 radius < 1 
        // => lic13 returns true
        d = new Decide();
        ps = new Parameters();

        ps.radius1 = 2;
        ps.radius2 = 1;
        ps.aPts = 1;
        ps.bPts = 2;
        
        points = new ArrayList<>();
        
        points.add(new Point2D.Double(1, 0)); // first point - less than (circle), radius == 1.0
        points.add(new Point2D.Double(3, 0)); // first point - large than (circle), radius == 3.0
        points.add(new Point2D.Double(0, 0)); // second point - less than (circle), radius == 1.0
        points.add(new Point2D.Double(0, 0)); // second point - large than (circle), radius == 3.0
        points.add(new Point2D.Double(0, 0));
        points.add(new Point2D.Double(0, 1)); // third point - less than (circle), radius == 1.0
        points.add(new Point2D.Double(0, 3)); // third point - large than (circle), radius == 3.0
        points.add(new Point2D.Double(0, 0));

        d.points = points;
        d.parameters = ps;

        assertTrue(d.lic13());
    }

    /**
     * Tests that lic6 returns true if there exists at least one set of nPts consecutive data points such that
     * least one of the points lies a distance greater than DIST from the line between the first of these nPts points and the last.
     * If the first and last points of these nPts are identical, then we compare the distance from that point to all
     * the other nPts points.
     * The condition is not met when numPoints < 3.
     *
     * Test case 1:
     * points = (-2, 2), (0, 4), (2, 0)
     * nPts = 3
     * dist = 1.0
     * Expected value: true
     *
     * Test case 2:
     * points = (-2, 2), (-2, 2), (2, 0)
     * nPts = 3
     * dist = 5.0
     * Expected value: false
     *
     * Test case 3 - Identical points:
     * points = (-2, 0), (5, 3), (0, 0), (-2, 0)
     * nPts = 4
     * dist = 1.0
     * Expected value: false
     **/
    @Test
    void testLic6() {
        ArrayList<Point2D.Double> points = new ArrayList<>();
        Point2D.Double point1 = new Point2D.Double(-2, 0);
        Point2D.Double point2 = new Point2D.Double(0, 4);
        Point2D.Double point3 = new Point2D.Double(2,0);
        Decide decide1 = new Decide();
        Parameters parameters1 = new Parameters();
        parameters1.nPts = 3;
        parameters1.dist = 1.0;
        decide1.parameters = parameters1;
        decide1.points = points;

        points.add(point1);
        points.add(point2);
        points.add(point3);

        assertTrue(decide1.lic6());

        ArrayList<Point2D.Double> points2 = new ArrayList<>();
        Point2D.Double point4 = new Point2D.Double(-2,2);
        Point2D.Double point5 = new Point2D.Double(-2,2);
        Point2D.Double point6 = new Point2D.Double(2,0);
        points2.add(point4);
        points2.add(point5);
        points2.add(point6);
        Decide decide2 = new Decide();
        Parameters parameters2 = new Parameters();
        parameters2.nPts = 3;
        parameters2.dist = 5.0;
        decide2.parameters = parameters2;
        decide2.points = points2;

        assertFalse(decide2.lic6());

        ArrayList<Point2D.Double> points3 = new ArrayList<>();
        Point2D.Double point7 = new Point2D.Double(-2, 0);
        Point2D.Double point8 = new Point2D.Double(-2, 2);
        Point2D.Double point9 = new Point2D.Double(0,0);
        Point2D.Double point10 = new Point2D.Double(-2,0);
        points3.add(point7);
        points3.add(point8);
        points3.add(point9);
        points3.add(point10);

        Decide decide3 = new Decide();
        Parameters parameters3 = new Parameters();
        parameters3.dist = 3.0;
        parameters3.nPts = 4;
        decide3.parameters = parameters3;
        decide3.points = points3;

        assertFalse(decide3.lic6());
    }

    /**
     * Tests that distPointLine returns the distance between a point and a line.
     * Test case 1:
     * points = (2, -1), (-2, 2), (-2, -1)
     * Expected value: 3.0
     *
     * Test case 2:
     * points = (-2, 0), (0, 4), (2, 0)
     * Expected value: 4.0
     */
    @Test
    void testDistPointLine() {
        Decide decide = new Decide();
        Point2D.Double point1 = new Point2D.Double(2, -1);
        Point2D.Double point2 = new Point2D.Double(-2, 2);
        Point2D.Double point3 = new Point2D.Double(-2,-1);

        assertEquals(3.0, decide.distPointLine(point1, point2, point3));

        Point2D.Double point4 = new Point2D.Double(-2, 0);
        Point2D.Double point5 = new Point2D.Double(0, 4);
        Point2D.Double point6 = new Point2D.Double(2,0);

        assertEquals(4.0, decide.distPointLine(point4, point5, point6));
    }
    
    /**
     * Tests that lic1 returns true if there exists three consecutive
     * data points that cannot be contained in a circle with radius1.
     * cannot be contained witih
     *
     * Test case 1:
     * points = (0, 2), (0, 0), (-1, -1)
     * Expected value: true
     *
     * Test case 2:
     * points = (0, 2), (0, 0), (-1, -1)
     * Expected value: false
     */
    @Test
    void testLic1() {
        Decide decide = new Decide();
        decide.parameters = new Parameters();

        ArrayList<Point2D.Double> points = new ArrayList<>();
        points.add(new Point2D.Double(0, 2));
        points.add(new Point2D.Double(2, 0));
        points.add(new Point2D.Double(0, 0));
        points.add(new Point2D.Double(-1, -1));

        decide.points = points;

        decide.parameters.radius1 = 1;
        assertTrue(decide.lic1());

        decide.parameters.radius1 = 5;
        assertFalse(decide.lic1());
    }
    
	/**
	 * Tests that cmv calculates lic0 to lic14 in a correct way.
	 */
	void calcCmv() {
		// test 1: (area of one triangle == 4.0) && (area1 == 3.0). This should lead to
		// expected value of true of lic3.
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
		d.calcCmv();

		assertFalse(d.cmv[0]);
		assertFalse(d.cmv[1]);
		assertFalse(d.cmv[2]);

		assertTrue(d.cmv[3]);

		assertFalse(d.cmv[4]);
		assertFalse(d.cmv[5]);
		assertFalse(d.cmv[6]);
		assertFalse(d.cmv[7]);
		assertFalse(d.cmv[8]);
		assertFalse(d.cmv[9]);
		assertFalse(d.cmv[10]);
		assertFalse(d.cmv[11]);
		assertFalse(d.cmv[12]);
		assertFalse(d.cmv[13]);
		assertFalse(d.cmv[14]);

		// -------------------------------------

		// test 1: (area of one triangle == 4.0) && (area1 == 4.0). This should lead to
		// expected value of false of lic3.
		ps.area1 = 4.0;

		assertFalse(d.lic3());

		assertFalse(d.cmv[0]);
		assertFalse(d.cmv[1]);
		assertFalse(d.cmv[2]);

		assertFalse(d.cmv[3]);

		assertFalse(d.cmv[4]);
		assertFalse(d.cmv[5]);
		assertFalse(d.cmv[6]);
		assertFalse(d.cmv[7]);
		assertFalse(d.cmv[8]);
		assertFalse(d.cmv[9]);
		assertFalse(d.cmv[10]);
		assertFalse(d.cmv[11]);
		assertFalse(d.cmv[12]);
		assertFalse(d.cmv[13]);
		assertFalse(d.cmv[14]);

	}
}