package GUI;

import App.EditorTab;
import App.EditorTabMenu;
import BL.Session;
import EditorCommands.*;
import Utility.CustomColor;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class AppView extends JFrame implements ActionListener, CaretListener {
    private HashMap<Object, Command> _commandsByItem; // Commands associated with menu items
//    private EditorTabMenu tabMenu;
    private Session session;

    public AppView(Session session) {
        super("Text editor");
        _commandsByItem = new HashMap<>();
        this.session = session;
    }

    private JMenuBar setupMenu() {
        // Create main menu and submenus

        JMenuBar mb = new JMenuBar();
        JMenu file, edit;
        JMenuItem newFile, openFile, saveFile, saveFileAs, closeAction;
        JButton help;

        // Add action listeners and accelerators to the menu items

        newFile = new JMenuItem("New");
        newFile.addActionListener(this);
        newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        _commandsByItem.put(newFile, new CommandNew(session));

        openFile = new JMenuItem("Open");
        openFile.addActionListener(this);
        openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        _commandsByItem.put(openFile, new CommandOpen(session));

        saveFile = new JMenuItem("Save");
        saveFile.addActionListener(this);
        saveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        _commandsByItem.put(saveFile, new CommandSave(session));

        saveFileAs = new JMenuItem("Save as");
        saveFileAs.addActionListener(this);
        _commandsByItem.put(saveFileAs, new CommandSaveAs(session));

        closeAction = new JMenuItem("Exit", KeyEvent.VK_U);
        closeAction.addActionListener(this);
        _commandsByItem.put(closeAction, new CommandClose(session));

        // Create file menu and add menu items

        file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);
        file.add(newFile);
        file.add(openFile);
        file.addSeparator();
        file.add(saveFile);
        file.add(saveFileAs);
        file.addSeparator();
        file.add(closeAction);

        // Create edit menu

        edit = setupStyleMenu();

        // Add all submenus to the main window

        mb.add(file);
        mb.add(edit);
        mb.add(Box.createHorizontalGlue());
//        mb.add(label);

        return mb;
    }

    private JMenu setupStyleMenu() {
        HashMap<Object, Color> _colorsByItem = new HashMap<>();
        JMenu mainMenu = new JMenu("Edit");
        JMenu colorMenu = new JMenu("Colors");
        mainMenu.setMnemonic(KeyEvent.VK_E);
        colorMenu.setMnemonic(KeyEvent.VK_C);


        ActionListener colorListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JMenuItem jmi = (JMenuItem) e.getSource();
                Color color = _colorsByItem.get(jmi);
                SimpleAttributeSet as = new SimpleAttributeSet();
                as.addAttribute(StyleConstants.Foreground, color);
//                editor.setCharacterAttributes(as, false);
//                curColor = color;
            }
        };

        for (CustomColor col : CustomColor.colors) {
            JMenuItem item = new JMenuItem(col.toString());
            item.addActionListener(colorListener);
            colorMenu.add(item);
            _colorsByItem.put(item, col.getColor());
        }

        mainMenu.add(colorMenu);

        JCheckBoxMenuItem bold = new JCheckBoxMenuItem("Bold");
        bold.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JMenuItem jmi = (JMenuItem) e.getSource();
                SimpleAttributeSet as = new SimpleAttributeSet();
                boolean selected = jmi.getModel().isSelected();
                if (selected) {
                    as.addAttribute(StyleConstants.Bold, true);
//                    isBold = true;
                } else {
                    as.addAttribute(StyleConstants.Bold, false);
//                    isBold = false;
                }
//                editor.setCharacterAttributes(as, false);
            }
        });

        mainMenu.add(bold);

        JCheckBoxMenuItem italic = new JCheckBoxMenuItem("Italic");
        italic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JMenuItem jmi = (JMenuItem) e.getSource();
                SimpleAttributeSet as = new SimpleAttributeSet();
                boolean selected = jmi.getModel().isSelected();
                if (selected) {
                    as.addAttribute(StyleConstants.Italic, true);
//                    isItalic = true;
                } else {
                    as.addAttribute(StyleConstants.Italic, false);
//                    isItalic = false;
                }
//                editor.setCharacterAttributes(as, false);
            }
        });
        mainMenu.add(italic);

        return mainMenu;
    }

    public void launch() {
        JPanel panel = new JPanel();
        JMenuBar mb = setupMenu();

//        editor.addCaretListener(this);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(mb);
        panel.add(session.getEditorMenu());

        getContentPane().add(panel);
        this.setJMenuBar(mb);
        this.setSize(800, 800);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        Command command = _commandsByItem.get(e.getSource());
        command.execute();
    }

    @Override
    public void caretUpdate(CaretEvent e) {
        SimpleAttributeSet as = new SimpleAttributeSet();
//        as.addAttribute(StyleConstants.Foreground, curColor);
//        as.addAttribute(StyleConstants.Bold, isBold);
//        as.addAttribute(StyleConstants.Italic, isItalic);
//        editor.setCharacterAttributes(as, false);
    }
}
