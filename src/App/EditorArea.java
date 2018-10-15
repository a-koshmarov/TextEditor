package App;

import javax.print.Doc;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.HTMLWriter;;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.ArrayList;

public class EditorArea extends JTextPane implements DocumentListener {
    private String fileName;
    private final String PATH = "files/";
    private final String extension = ".html";
    private JLabel label;
    private boolean isEmpty = true;
    private boolean isSaved = true;

    public static final int SAVE = 0;
    public static final int SAVE_AS = 1;


    public EditorArea(JLabel label) {
        this.label = label;
        this.setFileName("def");
        this.setContentType("text/html");
//        Document doc = this.getDocument();
//        doc.addDocumentListener(this);
    }

    private void setFileName(String name) {
        this.fileName = name;
        if (isEmpty) {
            label.setText(name);
        } else {
            label.setText(name + extension);
        }
    }

    private void setSavedStatus(boolean status) {
        isSaved = status;
        if (isSaved) {
            label.setForeground(Color.GREEN);
        } else {
            label.setForeground(Color.RED);
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

    public void save(int state) {
        if (state == SAVE && !isEmpty) {
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
            HTMLWriter hw = new HTMLWriter(out, (HTMLDocument) this.getDocument());
            hw.write();

            out.flush();
            out.close();
//            setSavedStatus(true);
        } catch (IOException | BadLocationException e) {
            e.printStackTrace();
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
                this.setPage(file.toURI().toURL());
                this.setEditable(true);
                isEmpty = false;
                setFileName(result.replace(extension, ""));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        setSavedStatus(false);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        setSavedStatus(false);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
    }
}