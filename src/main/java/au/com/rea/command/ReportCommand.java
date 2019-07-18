package au.com.rea.command;

import java.util.Optional;

import au.com.rea.domain.Robot;
import au.com.rea.exception.RobotControllerException;

/**
 * Command Implementation for Report.
 */
public class ReportCommand implements Command {

    @Override
    public Robot process(Optional<Robot> robot, String... arguments) throws RobotControllerException {
        if (robot.isPresent()) {
            System.out.println(robot.get());
            return robot.get();
        } else {
            System.out.println("Robot hasn't been placed on the table yet.");
        }
        return null;
    }
}
