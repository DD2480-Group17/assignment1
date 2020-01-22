import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class DecideTest {

    @Test
    void lcm4() {
        Decide decide = new Decide();
        decide.parameters = new Parameters();

        ArrayList<Point> threeQuad = new ArrayList();
        threeQuad.add(new Point(0,0));
        threeQuad.add(new Point(-1,0));
        threeQuad.add(new Point(0,-1));
        threeQuad.add(new Point(0,1));
        threeQuad.add(new Point(1,0));

        decide.parameters.qPts = 3;
        decide.numPoints = threeQuad.size();
        decide.points = threeQuad;

        //checks if it exist points in more then i quadrants
        for(int i = 2; i>= 1; i--){
            decide.parameters.quads = i;
            assertTrue(decide.lcm4());
        }

        //checks if it exist points in more then 3 quadrants (not possible with threeQuad)
        decide.parameters.quads = 3;
        assertFalse(decide.lcm4());

        ArrayList<Point> fourQuad = new ArrayList();

        fourQuad.add(new Point(1,1));
        fourQuad.add(new Point(-1,1));
        fourQuad.add(new Point(-1,-1));
        fourQuad.add(new Point(1,-1));

        decide.numPoints = fourQuad.size();
        decide.points = fourQuad;
        //checks if it exist points in more then i quadrants for the set fourQuad
        for(int i = 3; i>= 1; i--){
            decide.parameters.quads = i;
            assertTrue(decide.lcm4());
        }

    }
}