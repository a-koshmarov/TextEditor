package bl;

import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

public class CustomTab extends Tab {
    private TextArea textArea;
    private FileState fileState;

    public CustomTab(String fileName){
        super(fileName);
        fileState = new FileState(fileName);
        AnchorPane anchorPane = new AnchorPane();
        this.textArea = new TextArea();
        anchorPane.getChildren().add(textArea);
        AnchorPane.setTopAnchor(textArea, 0.0);
        AnchorPane.setLeftAnchor(textArea, 0.0);
        AnchorPane.setBottomAnchor(textArea, 0.0);
        AnchorPane.setRightAnchor(textArea, 0.0);
        this.setContent(anchorPane);
    }

    public TextArea getEditor(){
        return textArea;
    }

    public FileState getFileState() {
        return fileState;
    }
}
