package au.com.rea.command;

import java.util.Optional;

import au.com.rea.domain.*;
import au.com.rea.exception.RobotControllerException;
import au.com.rea.factory.DirectionFactory;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 *
 */
public class RotateCommandTest {
    private DirectionFactory directionFactory;
    private Command rotateCommand;
    private Robot robot;

    @Before
    public void setup() {
        directionFactory = new DirectionFactory();
        Coordinates robotCoordinates = new Coordinates(1, 1);
        DirectionVO directionVO = directionFactory.getDirections().get(Direction.EAST);
        robot = new Robot(robotCoordinates, directionVO);
    }

    @Test
    public void testRotateLeft() throws RobotControllerException {
        rotateCommand = new RotateCommand(directionFactory, Rotation.LEFT);
        robot = rotateCommand.process(Optional.of(robot));
        assertThat(robot.getDirectionVO().getDirection(), is(Direction.NORTH));
    }

    @Test
    public void testRotateRight() throws RobotControllerException {
        rotateCommand = new RotateCommand(directionFactory, Rotation.RIGHT);
        robot = rotateCommand.process(Optional.of(robot));
        assertThat(robot.getDirectionVO().getDirection(), is(Direction.SOUTH));
    }
    @Test(expected =  RobotControllerException.class)
    public void testRotateBeforePlace() throws Exception{
        rotateCommand = new RotateCommand(directionFactory, Rotation.LEFT);
        Robot robot = rotateCommand.process(Optional.empty());
    }
}