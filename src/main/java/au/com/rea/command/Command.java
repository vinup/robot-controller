package au.com.rea.command;

import java.util.Optional;

import au.com.rea.domain.Robot;
import au.com.rea.exception.RobotControllerException;

/**
 * Interface to define command operations.
 */
public interface Command {
    /**
     * Processes the command based on the implementation.
     * Robot is optional for Place command and arguments are optional for all the other commands.
     * Until the Robot is placed in a valid position, all the other commands will be ignored.
     *
     * @param robot
     * @param arguments
     * @return
     * @throws RobotControllerException
     */
    Robot process(Optional<Robot> robot, String... arguments) throws RobotControllerException;
}
