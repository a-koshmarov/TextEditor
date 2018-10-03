import javafx.scene.input.KeyCode;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.Scanner;

//Invoker
public class AppView extends JFrame implements ActionListener, KeyListener {
    private HashMap<String, Command> _commands = new HashMap<>();
    private Properties uiSettings;
    private EditorArea editor;

    public AppView(String settingsFile) {
        super("Text editor");
        uiSettings = new Properties();
        try {
            uiSettings.load(new FileInputStream(settingsFile));

        } catch (IOException e) {
            e.printStackTrace();
        }
        editor = new EditorArea();
    }

    private void setupUI(){
        editor.addKeyListener(this);
        JPanel panel = new JPanel();
        JPanel menu = new JPanel();
        panel.setLayout(new BorderLayout());
        editor.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 5));
        editor.setMargin(new Insets(10, 10, 10, 10));


        menu.add(new JButton("new"));
        menu.add(new JButton("edit"));
        menu.add(new JButton("save"));


        panel.add(editor, BorderLayout.CENTER);
        panel.add(menu, BorderLayout.NORTH);
        getContentPane().add(panel);
    }

    private void displayMenu() {
        System.out.println("Editor menu:");
        for (Object key : uiSettings.keySet()){
            System.out.println(key+". "+uiSettings.getProperty((String) key));
        }
    }

    private void registerCommands() {
        CommandFactory cf = new CommandFactory();
        for (Object key : uiSettings.keySet()) {
            _commands.put((String) key, cf.getCommand(uiSettings.getProperty((String) key)));
        }
    }

    public void launch(){
//        registerCommands();
        setupUI();
        setSize(400, 400);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    private void writeToEditor(String msg, Color color){
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, color);
        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Helvetica");

        int len = editor.getDocument().getLength();
        editor.setCaretPosition(len);
        editor.setCharacterAttributes(aset, false);
        editor.replaceSelection(msg);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        if ((e.getKeyCode() == KeyEvent.VK_R) && ((e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0)) {
            AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.RED);
            editor.setCharacterAttributes(aset, false);
        } else if ((e.getKeyCode() == KeyEvent.VK_Y) && ((e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0)) {
            AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.YELLOW);
            editor.setCharacterAttributes(aset, false);
        } else if ((e.getKeyCode() == KeyEvent.VK_G) && ((e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0)){
            AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.GREEN);
            editor.setCharacterAttributes(aset, false);
        } else if ((e.getKeyCode() == KeyEvent.VK_B) && ((e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0)) {
            AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.BLUE);
            editor.setCharacterAttributes(aset, false);
        }


//        writeToEditor(Character.toString(e.getKeyChar()), Color.RED);

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
