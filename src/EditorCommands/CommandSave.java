package EditorCommands;

import App.EditorTab;

public class CommandSave implements Command {

    @Override
    public void execute(EditorTab tab) {
        System.out.println("Save");
        tab.save(EditorTab.SAVE);
    }
}