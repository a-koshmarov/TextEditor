package GUI.Controller;

import BL.FileState;
import GUI.Model.EditorModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;


public class EditorController{

    @FXML
    private MenuBar menuBar;

    @FXML
    private TextArea textArea;

    private EditorModel model;
    private FileState fileState;

    public EditorController(EditorModel model){
        this.model = model;
    }

    @FXML
    private void onSave() {
        if (fileState == null) {
            fileState = new FileState("new");
            fileState.setContent(textArea.getText());
        }
    }

    @FXML
    private void onSaveAs(){

    }

    @FXML
    private void onCommit(){

    }

    @FXML
    private void onClose(){

    }

    @FXML
    public void showBranch() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/EditorListView.fxml"));
        loader.setControllerFactory(t->new EditorController(new EditorModel()));

        Stage window = (Stage)menuBar.getScene().getWindow();
        window.setScene(new Scene(loader.load()));
        window.show();
    }

}
