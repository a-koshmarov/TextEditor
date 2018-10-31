package EditorCommands;

import BL.Session;

public class CommandOpen implements Command {
    private Session session;

    public CommandOpen(Session session){
        this.session = session;
    }

    @Override
    public void execute() {
        System.out.println("Edit");
        session.open();
    }
}
