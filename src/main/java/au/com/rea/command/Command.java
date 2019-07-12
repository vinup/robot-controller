package au.com.rea.command;

import java.util.Optional;

import au.com.rea.domain.Robot;
import au.com.rea.exception.RobotControllerException;

/**
 *
 */
public interface Command {
    Robot process(Optional<Robot> robot, String... arguments) throws RobotControllerException;
}
