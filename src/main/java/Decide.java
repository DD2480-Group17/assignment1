import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Decide {
    enum BOOLEAN_OPERATOR {
        ANDD,
        ORR,
        NOTUSED
    }

    //Input parameters
    ArrayList<Point2D.Double> points;
    Parameters parameters;
    BOOLEAN_OPERATOR[][] lcm;
    boolean[] puv;

    //Intermediate results
    boolean[] cmv = new boolean[15];
    boolean[][] pum = new boolean[15][15];
    boolean[] fuv = new boolean[15];

    /**
     * Empty constructor
     */
    public Decide() {

    }

    /**
     * Constructor
     *
     * @param points     2D planar point
     * @param parameters 19 input parameters
     * @param lcm        logical connector matrix
     * @param puv        preliminary unlocking vector
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
            if (Utility.dist(points.get(i - 1), points.get(i)) > parameters.length1) {
                return true;
            }
        }
        return false;
    }

    /**
     * Launch Interceptor Condition 2
     *
     * @return true if there exists at least one set of three consecutive data points which form an angle such that:
     * angle < (PI − EPSILON)
     * or
     * angle > (PI + EPSILON)
     * The second of the three consecutive points is always the vertex of the angle. If either the first
     * point or the last point (or both) coincides with the vertex, the angle is undefined and false is returned
     */
    boolean lic2() {
        for (int i = 2; i < points.size(); i++) {
            Point2D point0 = points.get(i - 2);
            Point2D point1 = points.get(i - 1);
            Point2D point2 = points.get(i);

            if (!(point0.equals(point1) || point2.equals(point1))) {
                double angle = Utility.calcAngle(point0, point1, point2);
                if (angle < Math.PI - parameters.epsilon || angle > Math.PI + parameters.epsilon)
                    return true;
            }
        }
        return false;
    }

    /**
     * Returns true if there is at least one set of three consecutive data points in
     * points Arraylist that are the vertices of a triangle with area > AREA1 (which
     * is parameters.area1). Otherwise, return false.
     *
     * @return true if there is at least one set of three consecutive data points in
     * points Arraylist that are the vertices of a triangle with area >
     * AREA1 (which is parameters.area1). Otherwise, false.
     */
    public boolean lic3() {
        // The formula used to calculate the area of the triangle comes from
        // https://www.mathopenref.com/coordtrianglearea.html.

        double area1 = parameters.area1;
        // begin from index 2 (3rd position in points arraylist)
        // and check two steps backwards.
        for (int i = 2; i < points.size(); i++) {

            Point2D.Double point0 = points.get(i - 2);
            Point2D.Double point1 = points.get(i - 1);
            Point2D.Double point2 = points.get(i);

            double triangleArea = Utility.triangleArea(point0, point1, point2);

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
        for (int i = 0; i + parameters.qPts - 1 < points.size(); i++) {

            //check if the data set contains more then quad quads
            boolean[] foundQuads = new boolean[4];
            for (int j = 0; j < parameters.qPts; j++) {

                Point2D.Double point = points.get(i + j);

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
            if (numberOfquadrants > parameters.quads) {
                return true;
            }
        }
        return false;
    }

    /**
     * Launch Interceptor Condition 5
     *
     * @return true if there exists one set of two consecutive data points such that X[j]-X[j-1] < 0
     */
    boolean lic5() {
        for (int j = 1; j < points.size(); j++) {
            if ((points.get(j).x - points.get(j - 1).x) < 0) {
                return true;
            }
        }
        return false;
    }


    /**
     * Launch Interceptor Condition 7
     *
     * @return true if there exists at least one set of two data points separated by exactly kPts consecutive
     * intervening points that are a distance greater than the length,LENGTH1, apart.
     * The condition is not met when numPoints < 3.
     */
    boolean lic7() {
        for (int i = parameters.kPts + 1; i < points.size(); i++) {
            if (points.size() < 3) {
                return false;
            }
            if (Utility.dist(points.get(i - parameters.kPts - 1), points.get(i)) > parameters.length1) {
                return true;
            }
        }
        return false;
    }

    /**
     * Launch Interceptor Condition 8
     *
     * @return if there exists at least one set of three data points separated by exactly A PTS and B PTS
     * consecutive intervening points, respectively, that cannot be contained within or on a circle of
     * radius RADIUS1. The condition is not met when NUMPOINTS < 5.
     */
    boolean lic8() {
        if (points.size() < 5) {
            return false;
        }

        for (int i = parameters.aPts + parameters.bPts + 2; i < points.size(); i++) {
            Point2D point1 = points.get(i - parameters.aPts - parameters.bPts - 2);
            Point2D point2 = points.get(i - parameters.bPts - 1);
            Point2D point3 = points.get(i);
            if (Utility.canNotContainPoints(point1, point2, point3, parameters.radius1)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Returns true if there exists at least one set of three data points separated
     * by exactly C_PTS and D_PTS consecutive intervening points, respectively, that
     * form an angle such that: angle < (PI-EPSILON) or angle > (PI+EPSILON) The
     * second point of the set of three points is always the vertex of the angle.
     * <p>
     * Otherwise, returns false if either the first point or the last point (or
     * both) coincide with the vertex.
     * <p>
     * Also, Lic9 returns false if NUMPOINTS < 5.
     * <p>
     * It is assumed that 1 <= C_PTS, 1 <= D_PTS, C_PTS+D_PTS <= NUMPOINTS-3.
     *
     * @return true if there exists at least one set of three data points separated
     * by exactly C_PTS and D_PTS consecutive intervening points,
     * respectively, that form an angle such that: angle < (PI-EPSILON) or
     * angle > (PI+EPSILON). The second point of the set of three points is
     * always the vertex of the angle. Otherwise, false if either the first
     * point or the last point (or both) coincide with the vertex, or if
     * NUMPOINTS < 5.
     */
    boolean lic9() {

        if (points.size() < 5)
            return false;

        // ------------------------------------------

        // INVARIANT: for some j,
        // ..., points[j], ..., points[j+C_PTS+1] == vertex, ...,
        // points[j+C_PTS+1+D_PTS+1] == points[j+C_PTS+D_PTS+2], ...

        for (int i = parameters.cPts + parameters.dPts + 2; i < points.size(); i++) {
            Point2D point0 = points.get(i - parameters.cPts - parameters.dPts - 2);
            Point2D point1 = points.get(i - parameters.dPts - 1); // vertex
            Point2D point2 = points.get(i);

            if (!(point0.equals(point1) || point2.equals(point1))) {
                double angle = Utility.calcAngle(point0, point1, point2);
                if (angle < Math.PI - parameters.epsilon || angle > Math.PI + parameters.epsilon)
                    return true;
            }

        }

        return false;
    }


    /**
     * Calculates the Preliminary Unlocking Matrix (PUM)
     * LCM[i,j] represents the boolean operator to be applied to CMV[i] and CMV[j].
     * PUM[i,j] is set according to the result of this operation. If LCM[i,j] is NOTUSED, then PUM[i,j]
     * should be set to true. If LCM[i,j] is ANDD, PUM[i,j] should be set to true only if (CMV[i] AND
     * CMV[j]) is true. If LCM[i,j] is ORR, PUM[i,j] should be set to true if (CMV[i] OR CMV[j]) is
     * true.
     */
    void calcPum() {
        for (int i = 0; i < lcm.length; i++) {
            for (int j = 0; j < lcm[0].length; j++) {
                switch (lcm[i][j]) {
                    case ORR:
                        pum[i][j] = cmv[i] || cmv[j];
                        break;
                    case ANDD:
                        pum[i][j] = cmv[i] && cmv[j];
                        break;
                    case NOTUSED:
                        pum[i][j] = true;
                        break;
                }
            }
        }
    }

    /**
     * Launch Interceptor Condition 10
     *
     * @return true if There exists at least one set of three data points separated by exactly E_PTS and F_PTS
     * consecutive intervening points, respectively, that are the vertices of a triangle with area greater than AREA1
     */
    boolean lic10() {
        if (points.size() < 5)
            return false;
        for (int i = 0; i + parameters.ePts + parameters.fPts + 2 < points.size(); i++) {
            if (Utility.triangleArea(points.get(i), points.get(i + parameters.ePts + 1), points.get(i + parameters.ePts + parameters.fPts + 2)) > parameters.area1) {
                return true;
            }
        }
        return false;
    }

    /**
     * Launch Interceptor Condition 11
     *
     * @return ture if there exists at least one set of two data points, (X[i],Y[i]) and (X[j],Y[j]), separated by
     * exactly G PTS consecutive intervening points, such that X[j] - X[i] < 0. (where i < j )
     * The condition is not met when NUMPOINTS < 3.
     */
    boolean lic11() {
        if (points.size() < 3) {
            return false;
        }

        for (int j = parameters.gPts + 1; j < points.size(); j++) {
            int i = j - parameters.gPts - 1;
            if (points.get(j).getX() - points.get(i).getX() < 0) {
                return true;
            }
        }
        return false;
    }


    /**
     * Launch Interceptor Condition 12
     *
     * @return true if there exists at least one set of two data points, separated by exactly kPts consecutive
     * intervening points, which are a distance greater than the length1, apart AND there exists
     * at least one set of two data points, separated by exactly kPts consecutive intervening points,
     * that are a distance less than the length2, apart.
     * The condition is not met when numPoints < 3.
     */
    boolean lic12() {

        if (points.size() < 3) {
            return false;
        }
        int count = 0;
        for (int i = 0; i < points.size() - parameters.kPts - 1; i++) {
            if (Utility.dist(points.get(i), points.get(i + parameters.kPts + 1)) > parameters.length1) {
                count++;
                break;
            }
        }

        for (int j = 0; j < points.size() - parameters.kPts - 1; j++) {
            if (Utility.dist(points.get(j), points.get(j + parameters.kPts + 1)) < parameters.length2) {
                count++;
                break;
            }
        }

        if (count == 2) {
            return true;
        }

        return false;
    }

    /**
     * Returns true if there exists at least one set of three data points,
     * separated by exactly E_PTS and F_PTS consecutive
     * intervening points, respectively, that are the vertices of a triangle with area greater
     * than AREA1. In addition, there exist three data points (which can be the same or different
     * from the three data points just mentioned) separated by exactly E PTS and F PTS consecutive
     * intervening points, respectively, that are the vertices of a triangle with area less than
     * AREA2. Both parts must be true for the LIC to be true. Otherwise, return false.
     * Also, return false when NUMPOINTS < 5.
     * <p>
     * Assumed: 0 <= AREA2 and 0 <= AREA1
     *
     * @return true if if there exists at least one set of three data points,
     * separated by exactly E_PTS and F_PTS consecutive
     * intervening points, respectively, that are the vertices of a triangle with area greater
     * than AREA1. In addition, there exist three data points (which can be the same or different
     * from the three data points just mentioned) separated by exactly E PTS and F PTS consecutive
     * intervening points, respectively, that are the vertices of a triangle with area less than
     * AREA2. Both parts must be true for the LIC to be true. Otherwise, return false.
     * Also, return false when NUMPOINTS < 5.
     */
    boolean lic14() {

        if (points.size() < 5)
            return false;

        // ------------------------------------------

        // INVARIANT: for some j,
        // ..., points[j], ..., points[j+E_PTS+1], ...,
        // points[j+E_PTS+1+F_PTS+1] == points[j+E_PTS+F_PTS+2], ...

        boolean result = false;

        for (int i = parameters.ePts + parameters.fPts + 2; i < points.size(); i++) {
            Point2D.Double point0 = points.get(i - parameters.ePts - parameters.fPts - 2);
            Point2D.Double point1 = points.get(i - parameters.fPts - 1);
            Point2D.Double point2 = points.get(i);

            double triangleArea = Utility.triangleArea(point0, point1, point2);

            if (triangleArea > parameters.area1) {
                result = true;
                break;
            }
        }

        if (!result) {
            // INVARIANT: first part of the condition is not satisfied.
            return false;
        }

        // INVARIANT: First part is satisfied.

        for (int i = parameters.ePts + parameters.fPts + 2; i < points.size(); i++) {
            Point2D.Double point0 = points.get(i - parameters.ePts - parameters.fPts - 2);
            Point2D.Double point1 = points.get(i - parameters.fPts - 1);
            Point2D.Double point2 = points.get(i);

            double triangleArea = Utility.triangleArea(point0, point1, point2);

            if (triangleArea < parameters.area2) {
                // INVARIANT: both parts of the condition are satisfied.
                return true;
            }
        }

        // INVARIANT: the second part of the condition is not satisfied.
        return false;
    }

    /**
     * Calculates entries in FUV vector.for all i, FUV[i] is set to true if PUV[i] is false
     * (indicating that the associated LIC should not hold back launch)
     * or if for all elements i,j, i!=j, in PUM row i are true.
     */
    void calcFUV() {

        for (int i = 0; i < fuv.length; i++) {
            fuv[i] = false;
        }

        for (int i = 0; i < fuv.length; i++) {
            if (!puv[i])
                fuv[i] = true;
            else {
                for (int j = 0; j < pum[i].length; j++) {
                    if (i != j && pum[i][j]) {
                        fuv[i] = true;
                        break;
                    }
                }
            }
        }
    }

    /**
     * Launch condition 13.
     * <p>
     * Assumed: 0 <= Radius1 and 0 <= Radius2.
     *
     * @return true if There exists at least one set of three data points, separated
     * by exactly A_PTS and B_PTS consecutive intervening points,
     * respectively, that cannot be contained within or on a circle of
     * radius RADIUS1. In addition, there exists at least one set of three
     * data points (which can be the same or different from the three data
     * points just mentioned) separated by exactly A_PTS and B_PTS
     * consecutive intervening points, respectively, that can be contained
     * in or on a circle of radius RADIUS2. Both parts must be true for the
     * LIC to be true. Otherwise, return false. Also, return false when
     * NUMPOINTS < 5.
     */
    boolean lic13() {
        if (points.size() < 5)
            return false;

        // ------------------------------------------

        // INVARIANT: for some j,
        // ..., points[j], ..., points[j+A_PTS+1], ...,
        // points[j+A_PTS+1+B_PTS+1] == points[j+A_PTS+B_PTS+2], ...

        boolean result = false;

        for (int i = parameters.aPts + parameters.bPts + 2; i < points.size(); i++) {
            Point2D.Double point0 = points.get(i - parameters.aPts - parameters.bPts - 2);
            Point2D.Double point1 = points.get(i - parameters.bPts - 1);
            Point2D.Double point2 = points.get(i);

            boolean notContainedInCircle = Utility.canNotContainPoints(point0, point1, point2, parameters.radius1);

            if (notContainedInCircle) {
                result = true;
                break;
            }
        }

        if (!result) {
            // INVARIANT: first part of the condition is not satisfied.
            return false;
        }

        // INVARIANT: First part is satisfied.

        for (int i = parameters.aPts + parameters.bPts + 2; i < points.size(); i++) {
            Point2D.Double point0 = points.get(i - parameters.aPts - parameters.bPts - 2);
            Point2D.Double point1 = points.get(i - parameters.bPts - 1);
            Point2D.Double point2 = points.get(i);

            boolean notContainedInCircle = Utility.canNotContainPoints(point0, point1, point2, parameters.radius2);

            if (!notContainedInCircle) {
                // INVARIANT: both parts of the condition are satisfied.
                return true;
            }
        }

        // INVARIANT: the second part of the condition is not satisfied.
        return false;
    }

    /**
     * Launch Interceptor Condition 6 returns
     *
     * @return true if there exists at least one set of nPts consecutive data points such that
     * least one of the points lies a distance greater than DIST from the line between the first of these nPts points and the last.
     * If the first and last points of these nPts are identical, then we compare the distance from that point to all
     * The condition is not met when numPoints < 3.
     */
    boolean lic6() {

        if (points.size() < parameters.nPts || points.size() < 3) {
            return false;
        }

        for (int i = 0; i <= (points.size() - parameters.nPts); i++) {
            //if the first and last points are identical, check the distance from the point to the rest of
            //the points in the set of nPts consecutive data points
            if ((points.get(i).getX() == points.get(parameters.nPts - 1).getX()) && (points.get(i).getY() == points.get(parameters.nPts - 1).getY()) && i != parameters.nPts - 1) {

                for (int j = 0; j < parameters.nPts - 2; j++) {
                    if (Utility.dist(points.get(0), points.get(j + 1)) > parameters.dist) {
                        return true;
                    }
                }
                return false;

            } else {
                for (int j = 1; j < parameters.nPts - 1; j++) {

                    if (Utility.distPointLine(points.get(i), points.get(i + j), points.get(i + parameters.nPts - 1)) > parameters.dist) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    /**
     * Launch Interceptor Condition 1
     *
     * @return true if there exists at least one set of three consecutive data points that cannot all be contained
     * within or on a circle of radius1
     */
    boolean lic1() {
        for (int i = 0; i < (points.size() - 2); i++) {
            if (Utility.canNotContainPoints(points.get(i), points.get(i + 1), points.get(i + 2), parameters.radius1)) {
                return true;
            }
        }
        return false;
    }
    
	/**
	 * Calculate entries in cmv vector. This method calls all lic#, where 0 <= # <=
	 * 14 methods and saves their results in cmv vector.
	 */
	void calcCmv() {
		cmv[0] = lic0();
		cmv[1] = lic1();
		cmv[2] = lic2();
		cmv[3] = lic3();
		cmv[4] = lic4();
		cmv[5] = lic5();
		cmv[6] = lic6();
		cmv[7] = lic7();
		cmv[8] = lic8();
		cmv[9] = lic9();
		cmv[10] = lic10();
		cmv[11] = lic11();
		cmv[12] = lic12();
		cmv[13] = lic13();
		cmv[14] = lic14();
	}
	
	/**
	 * Returns true if all entries in fuv array are true. Otherwise, return false.
	 * 
	 * @return true if all entries in fuv array are true. Otherwise, false.
	 */
	boolean launch() {
		for (int i = 0; i < fuv.length; i++) {
			if (!fuv[i])
				return false;
		}
		return true;
	}

}
