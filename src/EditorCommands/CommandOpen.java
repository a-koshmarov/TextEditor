package EditorCommands;

import App.EditorTabMenu;

public class CommandOpen implements Command {
    private EditorTabMenu tabMenu;

    public CommandOpen(EditorTabMenu tabMenu){
        this.tabMenu = tabMenu;
    }

    @Override
    public void execute() {
        System.out.println("Edit");
        tabMenu.open();
    }
}
