package au.com.rea.command;

import java.util.Optional;

import au.com.rea.domain.Direction;
import au.com.rea.domain.Robot;
import au.com.rea.factory.DirectionFactory;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


/**
 *
 */
public class PlaceCommandTest {

    @Test
    public void testPlaceCommand() throws Exception {
        DirectionFactory directionFactory = new DirectionFactory();
        Command placeCommand = new PlaceCommand(directionFactory);
        String[] args =  {"1", "0", "EAST"};
        Robot robot = placeCommand.process(Optional.empty(),args);
        assertThat(robot, is(notNullValue()));
        assertThat(robot.getCoordinates().getXCoordinate(), is(1));
        assertThat(robot.getCoordinates().getYCoordinate(), is(0));
        assertThat(robot.getDirectionVO().getDirection(), is(Direction.EAST));
    }

    @Test
    public void testPlaceWithInvalidPosition() throws Exception {
        DirectionFactory directionFactory = new DirectionFactory();
        Command placeCommand = new PlaceCommand(directionFactory);
        String[] args = {"1", "6", "EAST"};
        Robot robot = placeCommand.process(Optional.empty(), args);
        //This position is beyond table dimension.
        //Robot will not be placed (initialized)
        assertThat(robot, is(nullValue()));
    }
}