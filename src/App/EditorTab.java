package App;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLWriter;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class EditorTab extends JPanel{
    // File information
    private String fileName;
    private static final String PATH = "files/";
    private static final String extension = ".html";
    private boolean isEmpty = true;

    // Editor pane
    private EditorArea editor;

    // Constants
    public static final int SAVE = 0;
    public static final int SAVE_AS = 1;

    EditorTab(){
        setLayout(new BorderLayout());
        editor = new EditorArea();
        add(editor);
    }

    private void setFileName(String name) {
        fileName = name;
    }

    public String getNameWithExt(){
        return fileName + extension;
    }

    public void save(int state) {
        if (state == SAVE && !isEmpty) {
            // TODO redo after new command
            String name = fileName + extension;
        } else {
            String name = JOptionPane.showInputDialog(this, "What's your filename, big boi (or girl, I am not sexist)", fileName.trim());
            if (name != null && !name.isEmpty()) {
                isEmpty = false;
                setFileName(name);
            }
        }
        try {
            File file = new File(PATH + fileName + extension);
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            HTMLWriter hw = new HTMLWriter(out, (HTMLDocument) editor.getDocument());
            hw.write();
            out.flush();
            out.close();
        } catch (IOException | BadLocationException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        int option = JOptionPane.showConfirmDialog(this, "Do you want to save file?", "Exit", JOptionPane.YES_NO_OPTION);
        System.out.println(option);

        if (option == 0) {
            if (fileName.trim().isEmpty()) {
                save(SAVE_AS);
            } else {
                save(SAVE);
            }
//            System.exit(0);
        } else if (option == 1) {
            System.exit(0);
        }
    }

    public void open() {
        File dir = new File(PATH);
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
            System.out.println(result);
            try {
                File file = new File(PATH + result);
                editor.setPage(file.toURI().toURL());
                editor.setEditable(true);
                isEmpty = false;
                setFileName(result.replace(extension, ""));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
