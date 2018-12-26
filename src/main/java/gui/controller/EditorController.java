package gui.controller;

import client.*;
import client.managerInterface.GenericBranchManager;
import client.managerInterface.GenericFileStateManager;
import client.managerInterface.GenericUserManager;
import dao.BranchEntity;
import dao.FileStateEntity;
import gui.ProgressBarListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EditorController {

    private FileStateEntity fileState;

    private GenericFileStateManager fileManager = new FileManager();

    private GenericBranchManager branchManager = new BranchManager();

    private GenericUserManager userManager = new UserManager();

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label commitField;

    @FXML
    private Label versionField;

    @FXML
    private Label userField;

    @FXML
    private MenuItem logOutMenu;

    @FXML
    private ComboBox<BranchEntity> branchSelector;

    @FXML
    private ListView<FileStateEntity> fileList;

    @FXML
    private TextArea textArea;

    @FXML
    void branchChange(ActionEvent event) {
        listUpdate();
        fileList.getSelectionModel().selectFirst();
        fileState = fileList.getSelectionModel().getSelectedItem();

        textArea.setText(fileState.getContent());
        versionField.setText(String.format("v. %s", fileState.getVersion()));
        textArea.setEditable(true);
    }

    @FXML
    void changeFile(MouseEvent event) {
        FileStateEntity selectedState = fileList.getSelectionModel().getSelectedItem();
        if (event.getClickCount() == 1 && branchSelector.getValue() != null) {
            if (selectedState.getMessage() != null) {
                commitField.setText(selectedState.getMessage());
            } else {
                commitField.setText("");
            }
        } else if (event.getClickCount() == 2 && branchSelector.getValue() != null) {
            if (fileState.isLast()){
                fileState.setContent(textArea.getText());
                fileManager.saveOrUpdate(fileState);
            }

            if (!selectedState.isLast()) {
                textArea.setEditable(false);
            } else {
                textArea.setEditable(true);
            }

            fileState = selectedState;
            textArea.setText(fileState.getContent());
            versionField.setText(String.format("v. %s", fileState.getVersion()));
        }
    }

    @FXML
    void onDifference(ActionEvent event) {
        FileStateEntity selectedItem = fileList.getSelectionModel().getSelectedItem();
        if (selectedItem != fileState) {
            DifferenceManager task = new DifferenceManager(selectedItem, fileState);
            task.registerListener(new ProgressBarListener(progressBar));

            task.setOnSucceeded((taskEvent) -> {
                versionField.setText(String.format("v. %s -> v. %s", selectedItem.getVersion(), fileState.getVersion()));
                textArea.setText(task.getValue());
                progressBar.setProgress(0);
            });

            ExecutorService executor = Executors.newFixedThreadPool(1);
            executor.execute(task);
            executor.shutdown();
        }
    }

    @FXML
    void logOut(ActionEvent event) throws IOException {
        Context.getInstance().logOut();
        Stage stage = (Stage) versionField.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Authorization.fxml"));

        stage.setScene(new Scene(loader.load()));
    }

    @FXML
    void onNew(ActionEvent event) {
        BranchEntity branch = new BranchEntity(showQuestionDialog("hey! can i get a branch name", "enter your branchName here"));
        FileStateEntity file = new FileStateEntity("");
        branch.setFileStateByHead(file);

        fileManager.saveOrUpdate(file);
        branchManager.addFileState(branch, file);
        branchManager.saveOrUpdate(branch);

        file.setBranchByBid(branch);
        fileManager.saveOrUpdate(file);

        branchSelector.getItems().add(branch);
        branchSelector.getSelectionModel().select(branch);
    }

    @FXML
    void onSave(ActionEvent event) {
        fileState.setContent(textArea.getText());
        fileManager.saveOrUpdate(fileState);
    }

    @FXML
    void onDelete(ActionEvent event) {
        BranchEntity branch = branchSelector.getValue();
        Collection<FileStateEntity> files = branch.getFileStatesById();

        branch.setFileStateByHead(null);
        branchManager.saveOrUpdate(branch);
        branchManager.delete(branch);

        for (FileStateEntity file : files) {
            fileManager.delete(file);
        }

        branchSelector.getItems().remove(branch);
    }

    @FXML
    void onCommit(ActionEvent event) {
        String name = showQuestionDialog("Wow, a commit message? So cool!", "Please enter your message:");
        if (fileState.isLast() && name!=null) {
            fileState.setContent(textArea.getText());
            fileState.setDate(formatCurrentDate());
            fileState.setMessage(name);
            fileState.setLast(false);
            fileManager.saveOrUpdate(fileState);
            BranchEntity branch = fileState.getBranchByBid();

            fileState = new FileStateEntity(fileState);
            branch.setFileStateByHead(fileState);

            fileManager.saveOrUpdate(fileState);
            branchManager.saveOrUpdate(branch);
            branchManager.addFileState(branch, fileState);
            listUpdate();

            textArea.setText(fileState.getContent());
            versionField.setText(String.format("v. %s", fileState.getVersion()));
        }
    }

    @FXML
    void initialize() {
        branchSelector.getItems().addAll(userManager.getBranches(Context.getInstance().getUser()));
        textArea.setEditable(false);
        textArea.setPromptText("*choose file to start editing*");
        userField.setText(Context.getInstance().getUser().getUserName());
    }

    private void listUpdate() {
        BranchEntity branch = branchSelector.getValue();
        fileList.getItems().clear();
        List<FileStateEntity> list = new ArrayList<>(branchManager.getFiles(branch));
        Collections.reverse(list);
        fileList.getItems().addAll(list);
    }

    private String formatCurrentDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dtf.format(LocalDateTime.now());
    }

    private String showQuestionDialog(String header, String content) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("User input");
        dialog.setHeaderText(header);
        dialog.setContentText(content);

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            return result.get();
        } else {
            return null;
        }
    }
}
