package App;

import javax.swing.*;
import javax.swing.text.*;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.HTMLWriter;;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.ArrayList;

public class EditorArea extends JTextPane implements KeyListener {
    private String fileName;
    private final String PATH = "files/";
    private final String extension = ".html";
    private JLabel label;


    public EditorArea(JLabel label) {
        this.label = label;
        setFileName(" ");
        this.addKeyListener(this);
        this.setContentType("text/html");
    }

    public String getFileName() {
        return fileName + extension;
    }

    private void setFileName(String name) {
        this.fileName = name;
        if (name.trim().isEmpty()) {
            label.setText(name);
        } else {
            label.setText(name + extension);
        }
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

    public void close() {
        int option = JOptionPane.showConfirmDialog(this, "Do you want to save file?", "Exit", JOptionPane.YES_NO_OPTION);
        System.out.println(option);

        if (option == 0) {
//            save();
            System.exit(0);
        } else if (option == 1) {
            System.exit(0);
        }
    }

    public void save(boolean state) {
        // state 0 - save as
        // state 1 - save
        if (!state) {
            String name = JOptionPane.showInputDialog(this, "What's your name, boi (or girl, I am not sexist)", fileName);
            if (name != null && !name.isEmpty()) {
                setFileName(name);
            }
        } else {
            String name = fileName + extension;
        }

        try {
            File file = new File(PATH + fileName + extension);
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            HTMLWriter hw = new HTMLWriter(out, (HTMLDocument) this.getDocument());
            hw.write();

            out.flush();
            out.close();
        } catch (IOException | BadLocationException e) {
            e.printStackTrace();
        }
    }

    public void open() {
        File dir = new File(PATH);
        File[] dirListing = dir.listFiles();
        ArrayList<String> avFiles = new ArrayList<>();
//        String[] avFiles = new String[dirListing.length];

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
                BufferedReader bf = new BufferedReader(new FileReader(PATH + result));
                HTMLEditorKit kit = (HTMLEditorKit) this.getEditorKit();
                HTMLDocument doc = (HTMLDocument) this.getDocument();
                kit.read(bf, doc, 0);
                setFileName(result.replace(extension, ""));
            } catch (IOException | BadLocationException e) {
                e.printStackTrace();
            }


//            try {
////                BufferedReader br = new BufferedReader(new FileReader(PATH+result));
////                String line;
////                this.setText("");
////                while((line = br.readLine()) != null){
////                    System.out.println(line);
////                    this.add
////                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }


    }

}
