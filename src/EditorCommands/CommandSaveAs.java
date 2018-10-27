package EditorCommands;

import App.EditorTab;

public class CommandSaveAs implements Command {

    @Override
    public void execute(EditorTab tab) {
        System.out.println("Save as");
        tab.save(EditorTab.SAVE_AS);
    }
}
