package au.com.rea;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Optional;

import au.com.rea.command.Command;
import au.com.rea.domain.Robot;
import au.com.rea.exception.RobotControllerException;
import au.com.rea.factory.CommandFactory;
import au.com.rea.factory.DirectionFactory;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is responsible for processing the input file and performing all the commands.
 */
public class CommandFileProcessor {
    private Logger logger = LoggerFactory.getLogger(CommandFileProcessor.class);
    private DirectionFactory directionFactory;
    private CommandFactory commandFactory;
    private Robot robot;


    public CommandFileProcessor() {
        directionFactory = new DirectionFactory();
        commandFactory = new CommandFactory(directionFactory);
    }


    /**
     * This method is responsible for processing the input file.
     * It throws RobotControllerException in the below scenarios.
     * 1. Input File cannot be found or not a valid file.
     * 2. First Command is not 'PLACE'.
     * 3. If PLACE has invalid arguments.
     * 4. If PLACE or MOVE take ROBOT beyond the table limits.
     *
     * @param inputFilePath
     * @throws RobotControllerException
     */
    public void processCommandFile(String inputFilePath) throws RobotControllerException {
        try {
            Path inputFile = Paths.get(inputFilePath);

            for (String commandLine : Files.readAllLines(inputFile)) {
                processCommandLine(commandLine);
            }
        } catch (IOException ex) {
            logger.error("Invalid Input File: {}", inputFilePath, ex);
            throw new RobotControllerException(String.format(
                "Unable to find input file in the given location' %s", inputFilePath));
        }
    }

    protected void processCommandLine(String commandLine) throws RobotControllerException {
        Map<String, Command> commandRegister = commandFactory.getCommandRegister();
        String[] commandParts = StringUtils.split(commandLine, " ");
        if (commandParts == null || commandParts.length < 1) {
            logger.error("Invalid command: {}", commandLine);
            throw new RobotControllerException(String.format("Invalid command: %s", commandLine));
        }
        String action = StringUtils.upperCase(commandParts[0]);
        String[] args = null;
        String argumentPart = commandParts.length > 1 ? commandParts[1] : null;
        if (StringUtils.isNotBlank(argumentPart)) {
            args = StringUtils.split(argumentPart, ",");
        }

        if (commandRegister.containsKey(action)) {
            Command command = commandRegister.get(action);
            Optional<Robot> optionalRobot = robot != null ? Optional.of(robot) : Optional.empty();
            robot = command.process(optionalRobot, args);
        } else {
            logger.error("Invalid command: {}", commandLine);
            throw new RobotControllerException(String.format("Invalid command: %s", commandLine));
        }

    }

    public Robot getRobot() {
        return robot;
    }
}
