package EditorCommands;

import App.EditorArea;

public class CommandSave implements Command {
    private EditorArea editorArea;

    public CommandSave(EditorArea editorArea){
        this.editorArea = editorArea;
    }

    @Override
    public void execute() {
        System.out.println("Save");
        editorArea.save(EditorArea.SAVE);
    }
}
