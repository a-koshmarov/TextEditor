package App;

import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import java.awt.*;
import java.io.IOException;
import java.net.URL;


public class EditorTab extends JPanel{
    // File information
    private String fileName;
    public static final String PATH = "files/";
    public static final String EXTENSION = ".html";
    private boolean isEmpty = true;

    // Editor pane
    private EditorArea editor;

    EditorTab(String fileName){
        this.fileName = fileName;
        setLayout(new BorderLayout());
        editor = new EditorArea();
        add(editor);
    }

    boolean isEmpty() {
        return isEmpty;
    }

    String getFullPath(){
        return PATH + fileName + EXTENSION;
    }

    String getNameWithExt(){
        return fileName + EXTENSION;
    }

    String getFileName() {
        return fileName;
    }

    Document getDocument(){
        return editor.getDocument();
    }

    void setFocus(){
        editor.requestFocusInWindow();
    }

    void setURL(URL url){
        try {
            editor.setPage(url);
        } catch (IOException e){
            e.printStackTrace();
        }
        editor.setEditable(true);
    }

    void setStyle(SimpleAttributeSet sat){
        editor.setCharacterAttributes(sat, false);
    }

    void setFileName(String name) {
        fileName = name;
    }


}
