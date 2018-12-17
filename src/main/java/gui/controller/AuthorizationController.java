package gui.controller;

import client.Context;
import client.UserManager;
import client.managerInterface.GenericUserManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class AuthorizationController {

    private GenericUserManager userManager = new UserManager();

    @FXML
    private TextField userField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label error;


    @FXML
    void onLogin(ActionEvent event) throws IOException {
        userManager.authorize(userField.getText(), passwordField.getText());
        if (Context.getInstance().isAuthorized()){
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("EditorView.fxml"));

            stage.setScene(new Scene(loader.load()));
        } else {
            passwordField.clear();
            error.setVisible(true);
        }
    }

    @FXML
    void onRegister(ActionEvent event) throws IOException{
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Registration.fxml"));

        stage.setScene(new Scene(loader.load()));
    }
}
