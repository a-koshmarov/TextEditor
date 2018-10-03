import javax.swing.*;
import javax.swing.text.*;
import javax.swing.text.rtf.RTFEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class EditorArea extends JTextPane implements KeyListener{


    public EditorArea(){
        this.addKeyListener(this);
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
//            as.addAttribute(StyleConstants.Italic, Boolean.TRUE);
            RTFEditorKit rtf = new RTFEditorKit();
            this.setEditorKit(rtf);
            StyledDocument doc = this.getStyledDocument();

            try {
                FileOutputStream fout = new FileOutputStream("file.rtf");
                rtf.write(fout, doc, 0, doc.getLength());
            } catch (IOException |BadLocationException ex) {
                ex.printStackTrace();
            }
            this.setStyledDocument(doc);
        } else if ((e.getKeyCode() == KeyEvent.VK_Q) && ((e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0)) {
            as.addAttribute(StyleConstants.Foreground, Color.BLACK);
            as.addAttribute(StyleConstants.Bold, Boolean.FALSE);
            as.addAttribute(StyleConstants.Italic, Boolean.FALSE);
        }
        this.setCharacterAttributes(as, false);

//        RTFEditorKit rtf = new RTFEditorKit();
//        editor.setEditorKit(rtf);
//        StyledDocument doc = new DefaultStyledDocument();
//        try {
//            FileInputStream fi = new FileInputStream("file.rtf");
//            rtf.read(fi, doc, 0);
//        } catch (IOException| BadLocationException ex) {
//            ex.printStackTrace();
//        }
//        editor = new EditorArea((doc);

//        writeToEditor(Character.toString(e.getKeyChar()), Color.RED);

    }

}
