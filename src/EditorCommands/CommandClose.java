package EditorCommands;

import App.EditorTabMenu;

public class CommandClose implements Command {
    private EditorTabMenu tabMenu;

    public CommandClose(EditorTabMenu tabMenu){
        this.tabMenu = tabMenu;
    }

    @Override
    public void execute() {
        System.out.println("Close");
        tabMenu.close();
    }
}
