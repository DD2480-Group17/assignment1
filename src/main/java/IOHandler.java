import java.awt.*;
import java.awt.geom.Point2D;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class IOHandler {
    HashMap<String, Decide.BOOLEAN_OPERATOR> stringToEnum;

    /**
     * Constructor
     * Initializes stringToEnum.
     */
    public IOHandler() {
        stringToEnum = new HashMap<>();
        stringToEnum.put("ANDD", Decide.BOOLEAN_OPERATOR.ANDD);
        stringToEnum.put("ORR", Decide.BOOLEAN_OPERATOR.ORR);
        stringToEnum.put("NOTUSED", Decide.BOOLEAN_OPERATOR.NOTUSED);
    }

    /**
     * Parses a String and returns a Decide object
     * @param input Decide input in String representation
     * @return a Decide object with set input parameters
     */
    public Decide parseDecideInput(String input) {
        String[] lines = input.split("\n");
        int l = 0;

        ArrayList<Point2D.Double> points = new ArrayList<>();
        Parameters parameters = new Parameters();
        Decide.BOOLEAN_OPERATOR[][] LCM = new Decide.BOOLEAN_OPERATOR[15][15];
        boolean[] PUV = new boolean[15];

        //points
        int numPoints = Integer.parseInt(lines[l++]);

        for (int i = 0; i < numPoints; i++) {
            String[] coords = lines[l++].split(" ");
            double x = Double.parseDouble(coords[0]);
            double y = Double.parseDouble(coords[1]);
            Point2D.Double point = new Point2D.Double();
            point.setLocation(x, y);
            points.add(point);
        }

        //parameters
        parameters.length1 = Double.parseDouble(lines[l++]);
        parameters.radius1 = Double.parseDouble(lines[l++]);
        parameters.epsilon = Double.parseDouble(lines[l++]);
        parameters.area1 = Double.parseDouble(lines[l++]);
        parameters.qPts = Integer.parseInt(lines[l++]);
        parameters.quads = Integer.parseInt(lines[l++]);
        parameters.dist = Double.parseDouble(lines[l++]);
        parameters.nPts = Integer.parseInt(lines[l++]);
        parameters.kPts = Integer.parseInt(lines[l++]);
        parameters.aPts = Integer.parseInt(lines[l++]);
        parameters.bPts = Integer.parseInt(lines[l++]);
        parameters.cPts = Integer.parseInt(lines[l++]);
        parameters.dPts = Integer.parseInt(lines[l++]);
        parameters.ePts = Integer.parseInt(lines[l++]);
        parameters.fPts = Integer.parseInt(lines[l++]);
        parameters.gPts = Integer.parseInt(lines[l++]);
        parameters.length2 = Double.parseDouble(lines[l++]);
        parameters.radius2 = Double.parseDouble(lines[l++]);
        parameters.area2 = Double.parseDouble(lines[l++]);

        //LCM
        for (int i = 0; i < 15; i++) {
            String[] line = lines[l++].split(" ");
            for (int j = 0; j < 15; j++) {
                LCM[i][j] = stringToEnum.get(line[j]);
            }
        }

        //PUV
        for (int i = 0; i < 15; i++) {
            PUV[i] = Boolean.parseBoolean(lines[l++]);
        }

        return new Decide(points, parameters, LCM, PUV);
    }

    /**
     * Reads input from a file, stores it in a Decide object and returns it.
     * @param fileName the file name with input data
     * @return a Decide object filled with data from the file
     * @throws IOException if file does not exist
     */
    public Decide readFile(String fileName) throws IOException {
        File file = new File(fileName);
        BufferedReader reader = new BufferedReader(new FileReader(file));

        ArrayList<Point2D.Double> points = new ArrayList<>();
        Parameters parameters = new Parameters();
        Decide.BOOLEAN_OPERATOR[][] LCM = new Decide.BOOLEAN_OPERATOR[15][15];
        boolean[] PUV = new boolean[15];

        //points
        int numPoints = Integer.parseInt(reader.readLine());

        for (int i = 0; i < numPoints; i++) {
            String[] coords = reader.readLine().split(" ");
            double x = Double.parseDouble(coords[0]);
            double y = Double.parseDouble(coords[1]);
            Point2D.Double point = new Point2D.Double();
            point.setLocation(x, y);
            points.add(point);
        }

        //parameters
        parameters.length1 = Double.parseDouble(reader.readLine());
        parameters.radius1 = Double.parseDouble(reader.readLine());
        parameters.epsilon = Double.parseDouble(reader.readLine());
        parameters.area1 = Double.parseDouble(reader.readLine());
        parameters.qPts = Integer.parseInt(reader.readLine());
        parameters.quads = Integer.parseInt(reader.readLine());
        parameters.dist = Double.parseDouble(reader.readLine());
        parameters.nPts = Integer.parseInt(reader.readLine());
        parameters.kPts = Integer.parseInt(reader.readLine());
        parameters.aPts = Integer.parseInt(reader.readLine());
        parameters.bPts = Integer.parseInt(reader.readLine());
        parameters.cPts = Integer.parseInt(reader.readLine());
        parameters.dPts = Integer.parseInt(reader.readLine());
        parameters.ePts = Integer.parseInt(reader.readLine());
        parameters.fPts = Integer.parseInt(reader.readLine());
        parameters.gPts = Integer.parseInt(reader.readLine());
        parameters.length2 = Double.parseDouble(reader.readLine());
        parameters.radius2 = Double.parseDouble(reader.readLine());
        parameters.area2 = Double.parseDouble(reader.readLine());

        //LCM
        for (int i = 0; i < 15; i++) {
            String[] line = reader.readLine().split(" ");
            for (int j = 0; j < 15; j++) {
                LCM[i][j] = stringToEnum.get(line[j]);
            }
        }

        //PUV
        for (int i = 0; i < 15; i++) {
            PUV[i] = Boolean.parseBoolean(reader.readLine());
        }

        reader.close();
        return new Decide(points, parameters, LCM, PUV);
    }
}
