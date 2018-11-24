package GUI;

import BL.FileState;
import BL.Managers.FileManager;
import DAL.Connector;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
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
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("src/GUI/View/ListView.fxml"));
//        loader.setControllerFactory(t -> new EditorController(new EditorModel()));
        stage.setTitle("ListView test");
        stage.setWidth(450);
        stage.setHeight(550);

        Label label = new Label("list of versions in branch");
        populateData();

        final VBox vbox = new VBox();
//        vbox.setSpacing(5);
        vbox.setPadding(new Insets(5, 5, 5, 5));
        vbox.getChildren().addAll(label, mListView);
        vbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){
        Connector connector = new Connector();
        launch(args);
    }
}
