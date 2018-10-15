package EditorCommands;

import App.EditorArea;

public class CommandEdit implements Command {
    private EditorArea editorArea;

    public CommandEdit(EditorArea editorArea) {
        this.editorArea = editorArea;
    }

    @Override
    public void execute() {
        System.out.println("Edit");
        editorArea.open();
    }
}
