package au.com.rea.command;

import java.util.Optional;

import au.com.rea.domain.Coordinates;
import au.com.rea.domain.Direction;
import au.com.rea.domain.DirectionVO;
import au.com.rea.domain.Robot;
import au.com.rea.factory.DirectionFactory;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


/**
 *
 */
public class MoveCommandTest {
    private DirectionFactory directionFactory;
    private Command moveCommand;
    private Robot robot;

    @Before
    public void setup() {
        directionFactory = new DirectionFactory();
        moveCommand = new MoveCommand();
        Coordinates robotCoordinates = new Coordinates(1, 1);
        DirectionVO directionVO = directionFactory.getDirections().get(Direction.EAST);
        robot = new Robot(robotCoordinates, directionVO);
    }

    @Test
    public void testMoveCommandProcess() throws Exception {
        Robot movedRobot = moveCommand.process(Optional.of(robot));
        assertThat(movedRobot, is(notNullValue()));
        assertThat(movedRobot.getCoordinates().getXCoordinate(), is(2));
        assertThat(movedRobot.getCoordinates().getYCoordinate(), is(1));
    }

    @Test
    public void testMoveBeforePlace() throws Exception {
        Robot robot = moveCommand.process(Optional.empty());
        assertThat(robot, is(nullValue()));
    }

    @Test
    public void testMoveToIncorrectPosition() throws Exception {
        robot.setCoordinates(new Coordinates(5, 0));
        Robot movedRobot = moveCommand.process(Optional.of(robot));
        //This move is ignored as it throws the robot off the table.
        //Robot position should be unchanged.
        assertThat(movedRobot, is(robot));

    }

}