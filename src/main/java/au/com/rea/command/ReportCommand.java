package au.com.rea.command;

import java.util.Optional;

import au.com.rea.domain.Robot;
import au.com.rea.exception.RobotControllerException;

/**
 *
 */
public class ReportCommand implements Command {

    @Override
    public Robot process(Optional<Robot> robot, String... arguments) throws RobotControllerException {
        if (!robot.isPresent()) {
            throw new RobotControllerException("Robot needs to be placed on the table first.");
        }
        System.out.println(robot.get());
        return robot.get();
    }
}
