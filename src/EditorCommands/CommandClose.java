package EditorCommands;

import BL.Session;

public class CommandClose implements Command {
    private Session session;

    public CommandClose(Session session){
        this.session = session;
    }

    @Override
    public void execute() {
        System.out.println("Close");
        session.close();
    }
}
