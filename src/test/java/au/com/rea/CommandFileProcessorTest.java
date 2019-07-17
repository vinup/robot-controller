package au.com.rea;

import au.com.rea.domain.Direction;
import au.com.rea.domain.Robot;
import au.com.rea.exception.RobotControllerException;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 *
 */
public class CommandFileProcessorTest {
    private CommandFileProcessor commandFileProcessor;

    @Before
    public void setUp() {
        commandFileProcessor = new CommandFileProcessor();
    }

    @Test
    public void testProcessCommandLine() throws RobotControllerException {
        commandFileProcessor.processCommandLine("PLACE 1,1,SOUTH");
        Robot robot = commandFileProcessor.getRobot();
        assertThat(robot.getDirectionVO().getDirection(), is(Direction.SOUTH));
    }

    @Test
    public void testProcessInputFile() throws Exception {
        commandFileProcessor.processCommandFile("src/test/resources/testInputCommands1.txt");
        Robot robot = commandFileProcessor.getRobot();
        assertThat(robot.getDirectionVO().getDirection(), is(Direction.WEST));
        assertThat(robot.getCoordinates().getXCoordinate(), is(0));
        assertThat(robot.getCoordinates().getYCoordinate(), is(1));
    }

    @Test(expected = RobotControllerException.class)
    public void testInputFileWithNoPlace() throws Exception {
        commandFileProcessor.processCommandFile("src/test/resources/testInputCommands2.txt");
    }
}