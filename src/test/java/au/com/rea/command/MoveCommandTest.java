package au.com.rea.command;

import java.util.Optional;

import au.com.rea.DirectionFactory;
import au.com.rea.domain.Coordinates;
import au.com.rea.domain.Direction;
import au.com.rea.domain.DirectionVO;
import au.com.rea.domain.Robot;
import au.com.rea.exception.RobotControllerException;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;


/**
 *
 */
public class MoveCommandTest {
    private DirectionFactory directionFactory;
    private Command moveCommand;

    @Before
    public void setup() {
        directionFactory = new DirectionFactory();
        moveCommand = new MoveCommand();
    }

    @Test
    public void testProcess() throws Exception {
        Coordinates robotCoordinates = new Coordinates(1, 1);
        DirectionVO directionVO = directionFactory.getDirections().get(Direction.EAST);
        Robot robot = new Robot(robotCoordinates, directionVO);

        Robot movedRobot = moveCommand.process(Optional.of(robot));
        assertThat(movedRobot, is(notNullValue()));
        assertThat(movedRobot.getCoordinates().getXCoordinate(), is(2));
        assertThat(movedRobot.getCoordinates().getYCoordinate(), is(1));
    }
    @Test(expected =  RobotControllerException.class)
    public void testMoveBeforePlace() throws Exception{
        Robot robot = moveCommand.process(Optional.empty());
    }

}