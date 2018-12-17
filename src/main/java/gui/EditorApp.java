package gui;

import dao.HibernateSessionFactory;
import gui.controller.AuthorizationController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class EditorApp extends Application {

    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Authorization.fxml"));

        stage.setScene(new Scene(loader.load()));
        stage.setTitle("Text editor!");
        stage.getIcons().add(new Image("icon.png"));
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
        HibernateSessionFactory.shutdown();
    }

}
