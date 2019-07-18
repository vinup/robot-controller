package au.com.rea.command;

import java.util.Optional;

import au.com.rea.domain.Direction;
import au.com.rea.domain.DirectionVO;
import au.com.rea.domain.Robot;
import au.com.rea.domain.Rotation;
import au.com.rea.factory.DirectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Command implementation for LEFT and RIGHT.
 */
public class RotateCommand implements Command {
    private DirectionFactory directionFactory;
    private Rotation rotation;
    private Logger log = LoggerFactory.getLogger(RotateCommand.class);

    public RotateCommand(DirectionFactory directionFactory, Rotation rotation) {
        this.directionFactory = directionFactory;
        this.rotation = rotation;
    }

    @Override
    public Robot process(Optional<Robot> robot, String... arguments) {
        if (robot.isPresent()) {
            Robot newRobot = robot.get();
            DirectionVO currentDirectionVO = newRobot.getDirectionVO();
            Direction newDirection = Rotation.RIGHT.equals(rotation)
                ? currentDirectionVO.getClockwiseNext()
                : currentDirectionVO.getAntiClockwiseNext();
            DirectionVO newDirectionVO = directionFactory.getDirections()
                .get(newDirection);
            newRobot.setDirectionVO(newDirectionVO);
            return newRobot;
        } else {
            log.warn("Ignoring the command as the robot hasn't been place on the table yet.");
        }
        return null;
    }
}
