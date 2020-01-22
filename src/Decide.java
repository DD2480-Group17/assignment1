import java.awt.*;
import java.util.ArrayList;

public class Decide {
    int numPoints;
    ArrayList<Point> points;
    Parameters parameters;
    LCM lcm;
    PUV puv;

    /**
     * lcm4 checks if it exists data points in more then QUADS unique quadrants, where , the data point (0,0)
     * is in quadrant I, the point (-l,0) is in quadrant II, the point (0,-l) is in quadrant III, the point
     * (0,1) is in quadrant I and the point (1,0) is in quadrant I     *
     *
     * @return the function returns true if it exists data points in more then QUADS unique quadrants
     */
    public boolean lcm4(){
        //checks if qPts or quads is in a undefined area for this function

        if(parameters.qPts < 2 || parameters.qPts > numPoints
                || parameters.quads < 1 || parameters.quads > 3){
            return false;
        }
        //It is not possible for a point to be in more then one quadrant
        else if(parameters.qPts < parameters.quads){
            return false;
        }

        boolean[] foundQuads = new boolean[4];
        //Loop through the data points and translate coordinates to a quadrant and set that quadrant to true.
        for(Point point : points){
            //Is the point in quadrant 1
            if(point.x >= 0 && point.y >= 0){
                foundQuads[0] = true;
            }
            //Is the point in quadrant 2
            else if(point.y >= 0){
                foundQuads[1] = true;
            }
            //Is the point in quadrant 3
            else if(point.x <= 0){
                foundQuads[2] = true;
            }
            //The point is in quadrant 4
            else{
                foundQuads[3] = true;
            }
        }

        int numberOfquadrants = 0;
        //count number of unique quadrants
        for(int i = 0 ; i < foundQuads.length; i++){
            if(foundQuads[i]) {
                numberOfquadrants++;
            }
        }

        //if we have point in more the quads quadrants return true
        return numberOfquadrants > parameters.quads;

    }
}
