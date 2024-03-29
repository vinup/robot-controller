package au.com.rea.command;

import java.util.Optional;

import au.com.rea.domain.Coordinates;
import au.com.rea.domain.Direction;
import au.com.rea.domain.DirectionVO;
import au.com.rea.domain.Robot;
import au.com.rea.factory.DirectionFactory;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 *
 */
public class ReportCommandTest {

    private DirectionFactory directionFactory;
    private Command reportCommand;
    private Robot robot;

    @Before
    public void setup() {
        directionFactory = new DirectionFactory();
        reportCommand = new ReportCommand();
        Coordinates robotCoordinates = new Coordinates(1, 1);
        DirectionVO directionVO = directionFactory.getDirections().get(Direction.EAST);
        robot = new Robot(robotCoordinates, directionVO);
    }


    @Test
    public void testReportBeforePlace() throws Exception {
        robot = reportCommand.process(Optional.empty());
        //As the Robot hasn't been placed yet, it will still be uninitialised.
        assertThat(robot, is(nullValue()));
    }
}