package EditorCommands;

import App.EditorTabMenu;

public class CommandSaveAs implements Command {
    private EditorTabMenu tabMenu;

    public CommandSaveAs(EditorTabMenu tabMenu){
        this.tabMenu = tabMenu;
    }

    @Override
    public void execute() {
        System.out.println("Save as");
        tabMenu.save(EditorTabMenu.SAVE_AS);
    }
}
