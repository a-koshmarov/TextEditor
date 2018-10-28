package EditorCommands;

import App.EditorTabMenu;
import BL.Session;

public class CommandSave implements Command {
    private Session session;

    public CommandSave(Session session){
        this.session = session;
    }

    @Override
    public void execute() {
        System.out.println("Save");
        session.save(EditorTabMenu.SAVE);
    }
}