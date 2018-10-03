package EditorCommands;

import EditorCommands.Command;

import java.util.HashMap;
import java.util.Properties;

public class CommandFactory {
    private HashMap<String, Command> _commandNames = new HashMap<>();

    public CommandFactory() {
        _commandNames.put(CommandNames.NEW, new CommandNew());
        _commandNames.put(CommandNames.EDIT, new CommandEdit());
        _commandNames.put(CommandNames.CLOSE, new CommandClose());
    }

    public Command getCommand(String commandName) {
        return _commandNames.get(commandName);
    }
}
