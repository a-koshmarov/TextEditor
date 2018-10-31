package EditorCommands;

import BL.Session;

public class CommandNew implements Command{
    private Session session;

    public CommandNew(Session session){
        this.session = session;
    }

    @Override
    public void execute() {
        System.out.println("Hello");
        session.newTab();
    }
}
