package au.com.rea.command;

import java.util.Optional;

import au.com.rea.domain.Coordinates;
import au.com.rea.domain.Robot;
import au.com.rea.exception.RobotControllerException;

/**
 *
 */
public class MoveCommand implements Command {

    @Override
    public Robot process(Optional<Robot> robot, String... arguments) throws RobotControllerException {
        if (!robot.isPresent()) {
            throw new RobotControllerException("Robot needs to be placed on the table first.");
        }
        Robot movedRobot = robot.get();
        Coordinates newCoordinates = moveCoordinates(movedRobot.getCoordinates(), movedRobot.getDirectionVO().getCoordinates());
        movedRobot.setCoordinates(newCoordinates);
        return movedRobot;
    }

    private Coordinates moveCoordinates(Coordinates robotCoordinates, Coordinates directionCoordinates) {
        int newXCoordinate = robotCoordinates.getXCoordinate() + directionCoordinates.getXCoordinate();
        int newYCoordinate = robotCoordinates.getYCoordinate() + directionCoordinates.getYCoordinate();
        return new Coordinates(newXCoordinate, newYCoordinate);
    }
}
