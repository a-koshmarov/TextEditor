package App;

import javax.swing.*;
import javax.swing.text.*;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLWriter;
import javax.swing.text.rtf.RTFEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class EditorArea extends JTextPane implements KeyListener{
    private String fileName;
    private final String PATH = "files/";
    private final String extension = ".html";
    private JLabel label;


    public EditorArea(JLabel label){
        this.label = label;
        this.fileName = "default";
        this.label.setText(fileName+extension);
        this.addKeyListener(this);
        this.setContentType("text/html");
    }

    String getFileName(){
        return fileName + extension;
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        SimpleAttributeSet as = new SimpleAttributeSet();
        if ((e.getKeyCode() == KeyEvent.VK_R) && ((e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0)) {
            as.addAttribute(StyleConstants.Foreground, Color.RED);
        } else if ((e.getKeyCode() == KeyEvent.VK_Y) && ((e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0)) {
            as.addAttribute(StyleConstants.Foreground, Color.YELLOW);
        } else if ((e.getKeyCode() == KeyEvent.VK_G) && ((e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0)) {
            as.addAttribute(StyleConstants.Foreground, Color.GREEN);
        } else if ((e.getKeyCode() == KeyEvent.VK_B) && ((e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0)) {
            as.addAttribute(StyleConstants.Bold, Boolean.TRUE);
        } else if ((e.getKeyCode() == KeyEvent.VK_I) && ((e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0)) {
            as.addAttribute(StyleConstants.Italic, Boolean.TRUE);
        } else if ((e.getKeyCode() == KeyEvent.VK_Q) && ((e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0)) {
            as.addAttribute(StyleConstants.Foreground, Color.BLACK);
            as.addAttribute(StyleConstants.Bold, Boolean.FALSE);
            as.addAttribute(StyleConstants.Italic, Boolean.FALSE);
        }
        this.setCharacterAttributes(as, false);
    }

    public void close(){
        int option = JOptionPane.showConfirmDialog(this, "Do you want to save file?", "Exit", JOptionPane.YES_NO_OPTION);
        System.out.println(option);

        if (option==0) {
            String name = JOptionPane.showInputDialog(this, "What's your name, boi (or girl, I am not sexist)");
            if (name != null && !name.isEmpty()) {
                fileName = name;
            }
            System.out.println(fileName);
            save();
            label.setText(fileName+extension);
//            System.exit(0);
        } else if (option == 1) {
            System.exit(0);
        }
    }

    public void save(){
        try {
            File file = new File(PATH + fileName + extension);
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            HTMLWriter hw = new HTMLWriter(out, (HTMLDocument)this.getDocument());
            hw.write();

            out.flush();
            out.close();
        } catch (IOException | BadLocationException e) {
            e.printStackTrace();
        }
    }

    public void open(){
        File dir = new File(PATH);
        File[] dirListing = dir.listFiles();
        ArrayList<File> avFiles = new ArrayList<>();
//        String[] avFiles = new String[dirListing.length];

        if (dirListing != null) {
            for (File child : dirListing){
                if (child.isFile() && child.getName().endsWith(".html")) {
                    try {
                        avFiles.add(child.getCanonicalFile());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println(child.getName());
                }
            }
        }



        if (avFiles.isEmpty()){
            System.out.println("dir is empty");
        } else {
            File[] files = avFiles.toArray(new File[avFiles.size()]);
            System.out.println("dir is not empty");
            File result = (File)JOptionPane.showInputDialog(this, "Выберите файл", "Открыть файл для редактирования",
                    JOptionPane.QUESTION_MESSAGE, null, files, files[0]);
            System.out.println(result.getName());
        }
    }

}
