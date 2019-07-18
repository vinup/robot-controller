package au.com.rea.command;

import java.util.Optional;

import au.com.rea.domain.Coordinates;
import au.com.rea.domain.Direction;
import au.com.rea.domain.DirectionVO;
import au.com.rea.domain.Robot;
import au.com.rea.exception.RobotControllerException;
import au.com.rea.factory.DirectionFactory;
import au.com.rea.validator.CoordinatesValidator;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Command implementation for Place.
 */
public class PlaceCommand implements Command {
    private Logger log = LoggerFactory.getLogger(PlaceCommand.class);
    private DirectionFactory directionFactory;

    public PlaceCommand(DirectionFactory directionFactory) {
        this.directionFactory = directionFactory;
    }

    @Override
    public Robot process(Optional<Robot> robot, String... args) throws RobotControllerException {
        if (args != null && args.length != 3) {
            throw new RobotControllerException("Invalid number of arguments");
        }
        try {
            Coordinates coordinates = new Coordinates(Integer.parseInt(args[0]),
                Integer.parseInt(args[1]));
            if (CoordinatesValidator.validate(coordinates)) {
                Direction direction = Direction.valueOf(StringUtils.upperCase(args[2]));
                DirectionVO directionVO = directionFactory.getDirections().get(direction);
                return new Robot(coordinates, directionVO);
            } else {
                log.warn("Ignoring the move as new position is beyond table dimensions.");
            }
        } catch (Exception e) {
            log.error("Error occurred while parsing the parameters for Place Command.");
            throw new RobotControllerException("Invalid Parameters for Place command ");
        }
        return null;
    }

}
