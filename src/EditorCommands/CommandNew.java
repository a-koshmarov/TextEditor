package EditorCommands;

import App.EditorTab;

public class CommandNew implements Command{

    @Override
    public void execute(EditorTab tab) {
        System.out.println("Hello");
    }
}
