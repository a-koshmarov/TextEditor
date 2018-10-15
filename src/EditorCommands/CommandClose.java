package EditorCommands;

import App.EditorArea;

public class CommandClose implements Command {
    private EditorArea editorArea;

    public CommandClose(EditorArea editorArea){
        this.editorArea = editorArea;
    }

    @Override
    public void execute() {
        System.out.println("Close");
        editorArea.close();
    }
}
