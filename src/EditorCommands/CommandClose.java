package EditorCommands;

import App.EditorTab;

public class CommandClose implements Command {

    @Override
    public void execute(EditorTab tab) {
        System.out.println("Close");
        tab.close();
    }
}
