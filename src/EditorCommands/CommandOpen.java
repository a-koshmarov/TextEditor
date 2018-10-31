package EditorCommands;

import App.EditorArea;

public class CommandOpen implements Command {
    private EditorArea editorArea;

    public CommandOpen(EditorArea editorArea) {
        this.editorArea = editorArea;
    }

    @Override
    public void execute() {
        System.out.println("Edit");
        editorArea.open();
    }
}
