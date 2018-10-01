import java.util.HashMap;

public class CommandFactory {
    private HashMap<String, Command> _commandNames = new HashMap<>();

    public CommandFactory() {
        // commands
//        _commandNames.put();
    }

    public Command getCommand(String commandName) {
        return _commandNames.get(commandName);
    }
}
