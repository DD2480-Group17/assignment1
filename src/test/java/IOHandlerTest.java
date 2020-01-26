import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IOHandlerTest {

    @Test
    void parseDecideInputTest() {
        IOHandler ioHandler = new IOHandler();
        Decide decide = ioHandler.parseDecideInput(TestCases.test1);
        assertEquals(decide.points.size(), 4);
        assertEquals(decide.points.get(3).getX(), 2.2);
        assertEquals(decide.parameters.aPts, 1);
        assertEquals(decide.lcm[0][0], Decide.BOOLEAN_OPERATOR.ANDD);
        assertTrue(decide.puv[0]);
    }
}