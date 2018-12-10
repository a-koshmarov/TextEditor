package gui;

import bl.FileState;
import bl.managers.FileManager;
import dal.Connector;
import gui.controller.EditorController;
import gui.model.EditorModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.ArrayList;

public class EditorApp extends Application {

    private FileManager fileManager = new FileManager();
    private ListView mListView = new ListView();

    private void populateData(){
        ArrayList<FileState> files = fileManager.getBranch("branch");
        for (FileState fs : files){
            mListView.getItems().add(fs.toString());
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/EditorView.fxml"));
        loader.setControllerFactory(t -> new EditorController(new EditorModel()));
//
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    public static void main(String[] args){
        Connector connector = new Connector();
        launch(args);
    }
}
