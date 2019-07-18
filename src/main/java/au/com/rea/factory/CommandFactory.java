package au.com.rea.factory;

import java.util.HashMap;
import java.util.Map;

import au.com.rea.command.*;
import au.com.rea.domain.Rotation;

/**
 * The command factory holds the mapping between commands and their action classes.
 * Each command is processed based on it's own implementation.
 */
public class CommandFactory {
    private final Map<String, Command> commandRegister;

    public CommandFactory(DirectionFactory directionFactory) {
        this.commandRegister = new HashMap<>();
        commandRegister.put("PLACE", new PlaceCommand(directionFactory));
        commandRegister.put("MOVE", new MoveCommand());
        commandRegister.put("LEFT", new RotateCommand(directionFactory, Rotation.LEFT));
        commandRegister.put("RIGHT", new RotateCommand(directionFactory, Rotation.RIGHT));
        commandRegister.put("REPORT", new ReportCommand());
    }

    public Map<String, Command> getCommandRegister() {
        return commandRegister;
    }
}
