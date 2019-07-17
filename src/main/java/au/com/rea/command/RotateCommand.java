package au.com.rea.command;

import java.util.Optional;

import au.com.rea.domain.Direction;
import au.com.rea.domain.DirectionVO;
import au.com.rea.domain.Robot;
import au.com.rea.domain.Rotation;
import au.com.rea.exception.RobotControllerException;
import au.com.rea.factory.DirectionFactory;

/**
 *
 */
public class RotateCommand implements Command {
    private DirectionFactory directionFactory;
    private Rotation rotation;

    public RotateCommand(DirectionFactory directionFactory, Rotation rotation) {
        this.directionFactory = directionFactory;
        this.rotation = rotation;
    }

    @Override
    public Robot process(Optional<Robot> robot, String... arguments) throws RobotControllerException {
        if (!robot.isPresent()) {
            throw new RobotControllerException("Robot needs to be placed on the table first.");
        }
        Robot newRobot = robot.get();
        DirectionVO currentDirectionVO= newRobot.getDirectionVO();
        Direction newDirection = Rotation.RIGHT.equals(rotation)
            ? currentDirectionVO.getClockwiseNext()
            : currentDirectionVO.getAntiClockwiseNext();
        DirectionVO newDirectionVO = directionFactory.getDirections()
            .get(newDirection);
        newRobot.setDirectionVO(newDirectionVO);
        return newRobot;
    }
}
