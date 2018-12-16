package gui.controller;

import client.*;
import client.managerInterface.GenericBranchManager;
import client.managerInterface.GenericFileStateManager;
import client.managerInterface.GenericUserManager;
import dao.BranchEntity;
import dao.FileStateEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EditorController {

    private FileStateEntity fileState;

    private GenericFileStateManager fileManager = new FileManager();

    private GenericBranchManager branchManager = new BranchManager();

    private GenericUserManager userManager = new UserManager();

//    public EditorController(UserEntity userEntity){
//        this.userEntity = userEntity;
//    }

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label commitField;

    @FXML
    private Label versionField;

    @FXML
    private Button diffButton;

    @FXML
    private ComboBox<BranchEntity> branchSelector;

    @FXML
    private ListView<FileStateEntity> fileList;

    @FXML
    private TextArea textArea;

    @FXML
    void createDifference(ActionEvent event) {
        FileStateEntity selectedItem = fileList.getSelectionModel().getSelectedItem();
        if (selectedItem!=fileState){
            DifferenceManager task = new DifferenceManager(selectedItem, fileState);
            task.registerListener(new ProgressBarListener(progressBar));

            task.setOnRunning((taskEvent)-> diffButton.setDisable(true));

            task.setOnSucceeded((taskEvent)->{
                versionField.setText(String.format("v. %s -> v. %s", selectedItem.getVersion(), fileState.getVersion()));
                textArea.setText(task.getValue());
                diffButton.setDisable(false);
            });

            ExecutorService executor = Executors.newFixedThreadPool(1);
            executor.execute(task);
            executor.shutdown();
        }
    }

    @FXML
    void branchChange(ActionEvent event) {
        listUpdate();
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
            fileState = selectedState;
            textArea.setText(fileState.getContent());
            versionField.setText(String.format("v. %s",fileState.getVersion()));
            if (!fileState.isLast()) {
                textArea.setEditable(false);
            } else {
                textArea.setEditable(true);
            }
        }
    }

    @FXML
    void onInfo(ActionEvent event) {

    }

    @FXML
    void onNew(ActionEvent event) {

    }

    @FXML
    void onSave(ActionEvent event) {
        fileState.setContent(textArea.getText());
        fileManager.saveOrUpdate(fileState);
    }

    @FXML
    void onClose(ActionEvent event) {
        fileState.setContent(textArea.getText());
        branchManager.saveOrUpdate(fileState.getBranchByBid());
    }

    @FXML
    void onCommit(ActionEvent event) {
        if (fileState.isLast()) {
            fileState.setContent(textArea.getText());
            fileState.setDate(formatCurrentDate());
            fileState.setMessage(showQuestionDialog());
            fileState.setLast(false);
            fileManager.saveOrUpdate(fileState);
            BranchEntity branch = fileState.getBranchByBid();

            fileState = new FileStateEntity(fileState);
            branch.setFileStateByHead(fileState);

            fileManager.saveOrUpdate(fileState);
            branchManager.saveOrUpdate(branch);
            branchManager.addFileState(branch, fileState);
            listUpdate();
        }
    }

    @FXML
    void initialize() {
        userManager.authorize("bob dylan", "knock");
        branchSelector.getItems().addAll(userManager.getBranches(Context.getInstance().getUser()));
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

    private String showQuestionDialog() {
        TextInputDialog dialog = new TextInputDialog("no message");
        dialog.setTitle("Commit message input");
        dialog.setHeaderText("Wow, a commit message? So cool!");
        dialog.setContentText("Please enter your message:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            return result.get();
        } else {
            return "no message";
        }
    }
}
