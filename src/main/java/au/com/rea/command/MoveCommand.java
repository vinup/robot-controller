package au.com.rea.command;

import java.util.Optional;

import au.com.rea.domain.Coordinates;
import au.com.rea.domain.Robot;
import au.com.rea.exception.RobotControllerException;
import au.com.rea.validator.CoordinatesValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class MoveCommand implements Command {
    private Logger log = LoggerFactory.getLogger(MoveCommand.class);

    @Override
    public Robot process(Optional<Robot> robot, String... arguments) throws RobotControllerException {
        if (!robot.isPresent()) {
            throw new RobotControllerException("Robot needs to be placed on the table first.");
        }
        Robot movedRobot = robot.get();
        Coordinates newCoordinates = moveCoordinates(movedRobot.getCoordinates(), movedRobot.getDirectionVO().getCoordinates());
        if (CoordinatesValidator.validate(newCoordinates)) {
            movedRobot.setCoordinates(newCoordinates);
            return movedRobot;
        } else {
            log.error("Invalid position for the Robot");
            throw new RobotControllerException("Invalid position for the Robot");
        }

    }

    private Coordinates moveCoordinates(Coordinates robotCoordinates, Coordinates directionCoordinates) {
        int newXCoordinate = robotCoordinates.getXCoordinate() + directionCoordinates.getXCoordinate();
        int newYCoordinate = robotCoordinates.getYCoordinate() + directionCoordinates.getYCoordinate();
        return new Coordinates(newXCoordinate, newYCoordinate);
    }
}
