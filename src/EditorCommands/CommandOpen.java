package EditorCommands;

import App.EditorTab;

public class CommandOpen implements Command {

    @Override
    public void execute(EditorTab tab) {
        System.out.println("Edit");
        tab.open();
    }
}
