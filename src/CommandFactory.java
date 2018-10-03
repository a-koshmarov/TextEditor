import java.util.HashMap;

public class CommandFactory {
    private HashMap<String, Command> _commandNames = new HashMap<>();

    public CommandFactory() {

    }

    public Command getCommand(String commandName) {
        return _commandNames.get(commandName);
    }
}
