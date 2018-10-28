package EditorCommands;

import App.EditorTabMenu;

public class CommandNew implements Command{
    private EditorTabMenu tabMenu;

    public CommandNew(EditorTabMenu tabMenu){
        this.tabMenu = tabMenu;
    }

    @Override
    public void execute() {
        System.out.println("Hello");
        tabMenu.newTab();
    }
}
