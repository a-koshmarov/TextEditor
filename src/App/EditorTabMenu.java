package App;

import BL.FileState;
import BL.Session;
import DAL.DAO.FileStateDAO;
import DAL.DTO.FileStateDTO;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

public class EditorTabMenu extends JTabbedPane {
    private EditorTab currentTab;
    private Session session;

    // Constants
    public static final int SAVE = 0;
    public static final int SAVE_AS = 1;

    public EditorTabMenu(Session session){
        this.session = session;
        init();
        setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        setTabPlacement(JTabbedPane.LEFT);
        setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 10));
    }

    private void init(){
        for (FileState fileState : session.getFiles()){
            if (fileState.isOpened()){
                newTab(fileState);
            }
        }
    }

    public void newTab(){
        String fileName = JOptionPane.showInputDialog(this, "What's your filename?");
        FileState fileState = new FileState(session.getUser().getID(), fileName);
        FileStateDAO.addFileState(new FileStateDTO(fileState));
        currentTab = new EditorTab(fileState);
        currentTab.setFocus();
        addTab(fileState.getFileName(), currentTab);
        setSelectedComponent(currentTab);
    }

    public void newTab(FileState fileState){
        currentTab = new EditorTab(fileState);
        currentTab.setFocus();
        addTab(fileState.getFileName(), currentTab);
        setSelectedComponent(currentTab);
    }

    public void save(int state) {
        if (state == SAVE_AS){
            String name = JOptionPane.showInputDialog(this, "What's your filename, big boi (or girl, I am not sexist)", currentTab.getFileName());
            if (name != null && !name.isEmpty()) {
                currentTab.setFileName(name);
                currentTab.setFocus();
                setTitleAt(getSelectedIndex(), name);
                FileStateDAO.addFileState(new FileStateDTO(currentTab.getFileState()));
            } else {
                return;
            }
        }

        try {
            File file = new File(currentTab.getFullPath());
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            HTMLWriter hw = new HTMLWriter(out, (HTMLDocument) currentTab.getDocument());
            hw.write();
            out.flush();
            out.close();
        } catch (IOException | BadLocationException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        if (getTabCount() == 0) {
            return;
        }

        save(SAVE);
        FileState fileState = currentTab.getFileState();
        fileState.setOpened(false);
        FileStateDAO.updateFileState(new FileStateDTO(fileState));
        remove(currentTab);
    }


    // TODO rework
    public void open() {
        File dir = new File(EditorTab.PATH);
        File[] dirListing = dir.listFiles();
        ArrayList<String> avFiles = new ArrayList<>();

        if (dirListing != null) {
            for (File child : dirListing) {
                if (child.isFile() && child.getName().endsWith(".html")) {
                    avFiles.add(child.getName());
                    System.out.println(child.getName());
                }
            }
        }

        if (avFiles.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Directory does not contain any .html files");
        } else {
            String[] files = avFiles.toArray(new String[avFiles.size()]);
            System.out.println("dir is not empty");
            String result = (String) JOptionPane.showInputDialog(this, "Выберите файл", "Открыть файл для редактирования",
                    JOptionPane.QUESTION_MESSAGE, null, files, files[0]);

            try {
                Set<String> fileNames = FileStateDAO.getAllFileNames(session.getUser().getID());
                File file = new File(EditorTab.PATH + result);
                String fileName = result.replace(EditorTab.EXTENSION, "");
                FileState fileState;

                if (fileNames.contains(fileName)){
                    fileState = new FileState(FileStateDAO.getFileState(session.getUser().getID(), fileName));
                } else {
                    fileState = new FileState(session.getUser().getID(), fileName);
                    FileStateDAO.addFileState(new FileStateDTO(fileState));
                }

                newTab(fileState);
                currentTab.setURL(file.toURI().toURL());


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
