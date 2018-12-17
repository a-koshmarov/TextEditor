package gui.controller;

import client.UserManager;
import client.managerInterface.GenericUserManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistrationController {

    private GenericUserManager userManager= new UserManager();

    @FXML
    private PasswordField passField;

    @FXML
    private PasswordField secondPassField;

    @FXML
    private TextField userField;

    @FXML
    void onConfirm(ActionEvent event) throws IOException {
        if(!userField.getText().isEmpty() && !passField.getText().isEmpty() && passField.getText().equals(secondPassField.getText())) {
            userManager.createNewUser(userField.getText(), passField.getText());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("EditorView.fxml"));

            stage.setScene(new Scene(loader.load()));
        }
    }

    @FXML
    void onCancel(ActionEvent event) throws IOException{
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Authorization.fxml"));

        stage.setScene(new Scene(loader.load()));
    }
}
