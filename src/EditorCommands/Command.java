package EditorCommands;

import App.EditorTab;

public interface Command {
    public void execute(EditorTab tab);
}
