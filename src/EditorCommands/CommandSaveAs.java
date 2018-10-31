package EditorCommands;

import App.EditorTabMenu;
import BL.Session;

public class CommandSaveAs implements Command {
    private Session session;

    public CommandSaveAs(Session session){
        this.session = session;
    }

    @Override
    public void execute() {
        System.out.println("Save as");
        session.save(EditorTabMenu.SAVE_AS);
    }
}
