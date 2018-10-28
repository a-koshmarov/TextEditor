package EditorCommands;

import App.EditorTabMenu;

public class CommandSave implements Command {
    private EditorTabMenu tabMenu;

    public CommandSave(EditorTabMenu tabMenu){
        this.tabMenu = tabMenu;
    }

    @Override
    public void execute() {
        System.out.println("Save");
        tabMenu.save(EditorTabMenu.SAVE);
    }
}