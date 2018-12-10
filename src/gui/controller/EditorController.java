package gui.controller;

import bl.CustomTab;
import bl.FileBranch;
import bl.FileState;
import gui.model.EditorModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import java.io.IOException;

public class EditorController {

    @FXML
    private MenuBar menuBar;

    @FXML
    private TabPane tabPane;

    private SingleSelectionModel<Tab> selectionModel;
    private CustomTab currentTab;
    private EditorModel model;

    public EditorController(EditorModel model){
        this.model = model;
    }

    @FXML
    private void onNew(){
        currentTab = new CustomTab("new");
        tabPane.getTabs().addAll(currentTab);
        this.selectionModel = tabPane.getSelectionModel();
        selectionModel.select(currentTab);
    }

    @FXML
    private void onOpen(){

    }

    @FXML
    private void onSave() {
        FileState fileState = currentTab.getFileState();
        fileState.setContent(currentTab.getEditor().getText());
        if (model.exists(fileState.getID())){
            model.save(currentTab.getFileState(), EditorModel.SAVE);
        } else {
            FileBranch branch = new FileBranch(fileState);
            fileState.setPID(branch.getID());
            model.create(fileState);
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/EditorListView.fxml"));
        loader.setControllerFactory(t->new EditorController(new EditorModel()));

        Stage window = (Stage)menuBar.getScene().getWindow();
        window.setScene(new Scene(loader.load()));
        window.show();
    }
}
