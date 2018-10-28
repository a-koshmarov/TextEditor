package App;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class EditorTabMenu extends JTabbedPane {
    private EditorTab currentTab;

    // Constants
    private static final String DEFAULT = "untitled";
    public static final int SAVE = 0;
    public static final int SAVE_AS = 1;

    EditorTabMenu(){
        currentTab = new EditorTab(DEFAULT);
        currentTab.setFocus();
        addTab(currentTab.getFileName(), currentTab);
        setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        setTabPlacement(JTabbedPane.LEFT);
        setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 10));
    }

    public void newTab(){
        String name = JOptionPane.showInputDialog(this, "What's your filename?");
        currentTab = new EditorTab(name);
        currentTab.setFocus();
        addTab(name, currentTab);
        setSelectedComponent(currentTab);
    }

    public void newTab(String name){
        currentTab = new EditorTab(name);
        currentTab.setFocus();
        addTab(name, currentTab);
        setSelectedComponent(currentTab);
    }

    public void save(int state) {
        if (state == SAVE_AS || (state == SAVE && currentTab.getFileName().equals(DEFAULT))){
            String name = JOptionPane.showInputDialog(this, "What's your filename, big boi (or girl, I am not sexist)", currentTab.getFileName());
            if (name != null && !name.isEmpty()) {
                if (name.equals(DEFAULT)) {
                    JOptionPane.showMessageDialog(this,  "Invalid filename. Please, pick another one.", "Invalid name", JOptionPane.WARNING_MESSAGE);
                }
                currentTab.setFileName(name);
                currentTab.setFocus();
                setTitleAt(getSelectedIndex(), name);
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
        int option = JOptionPane.showConfirmDialog(this, "Do you want to save file?", "Confirmation", JOptionPane.YES_NO_OPTION);
        System.out.println(option);

        if (option == 0) {
            if (currentTab.getFileName().equals(DEFAULT)) {
                save(SAVE_AS);
            } else {
                save(SAVE);
            }
        }
        if (getTabCount() == 1){
            System.exit(0);
        } else {
            remove(currentTab);
        }
    }

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
                File file = new File(EditorTab.PATH + result);
                newTab(result.replace(EditorTab.EXTENSION, ""));
                currentTab.setURL(file.toURI().toURL());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
