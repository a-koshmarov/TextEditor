package EditorCommands;

import App.EditorArea;

public class CommandSaveAs implements Command {
    private EditorArea editorArea;

    public CommandSaveAs(EditorArea editorArea){
        this.editorArea = editorArea;
    }

    @Override
    public void execute() {
        System.out.println("Save as");
        editorArea.save(EditorArea.SAVE_AS);
    }
}
