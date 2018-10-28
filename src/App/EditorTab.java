package App;

import BL.FileState;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.io.IOException;
import java.net.URL;


public class EditorTab extends JPanel{
    // File information
    private String fileName;
    static final String PATH = "files/";
    static final String EXTENSION = ".html";

    // Style constants
    private FileState fileState;
    private int cursor;
    private Color color;
    private boolean italic;
    private boolean bold;

    // Editor pane
    private EditorArea editor;

    EditorTab(String fileName){
        this.fileName = fileName;
        setLayout(new BorderLayout());
        editor = new EditorArea();
        add(editor);
    }

    EditorTab(FileState fileState){
        this.fileState = fileState;
        this.fileName = fileState.getFileName();
        this.cursor = fileState.getCursor();
        this.color = fileState.getColor();
        this.italic = fileState.isItalic();
        this.bold = fileState.isBold();

        SimpleAttributeSet sat = new SimpleAttributeSet();
        editor = new EditorArea();
        editor.setCaretPosition(0);
        sat.addAttribute(StyleConstants.Foreground, color);
        sat.addAttribute(StyleConstants.Italic, italic);
        sat.addAttribute(StyleConstants.Bold, bold);
        editor.setCharacterAttributes(sat, false);

        setLayout(new BorderLayout());
        add(editor);
    }


    String getFullPath(){
        return PATH + fileName + EXTENSION;
    }

    String getFileName() {
        return fileName;
    }

    Document getDocument(){
        return editor.getDocument();
    }

    FileState getFileState(){
        fileState.setFileName(fileName);
        fileState.setCursor(editor.getCaretPosition());
        fileState.setColor(color);
        fileState.setItalic(italic);
        fileState.setBold(bold);
        return fileState;
    }

    void setFocus(){
        editor.requestFocusInWindow();
    }

    void setURL(URL url){
        try {
            editor.setPage(url);
            System.out.println(editor.getText().length());
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

    public void setColor(Color color) {
        SimpleAttributeSet sat = new SimpleAttributeSet();
        sat.addAttribute(StyleConstants.Foreground, color);
        editor.setCharacterAttributes(sat, false);
        this.color = color;
    }

    public void setItalic(boolean italic) {
        SimpleAttributeSet sat = new SimpleAttributeSet();
        sat.addAttribute(StyleConstants.Italic, italic);
        editor.setCharacterAttributes(sat, false);
        this.italic = italic;
    }

    public void setBold(boolean bold) {
        SimpleAttributeSet sat = new SimpleAttributeSet();
        sat.addAttribute(StyleConstants.Bold, bold);
        editor.setCharacterAttributes(sat, false);
        this.bold = bold;
    }

}
