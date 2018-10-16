package App;

import EditorCommands.*;
import Utility.CustomColor;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Random;

//Invoker
public class AppView extends JFrame implements ActionListener, CaretListener {
    private HashMap<Object, Command> _commandsByItem;
    private EditorArea editor;
    private int count = 0;
    private boolean isBold = false;
    private boolean isItalic = false;
    private Color curColor = Color.BLACK;

    public AppView() {
        super("Text editor");
        _commandsByItem = new HashMap<>();
    }

    private Color randColor() {
        Random random = new Random();
        float r = random.nextFloat();
        float g = random.nextFloat();
        float b = random.nextFloat();
        return new Color(r, g, b);
    }

    private JMenuBar setupMenu(JLabel label) {
        JMenuBar mb = new JMenuBar();
        JMenu file, edit, help;
        JMenuItem newFile, openFile, saveFile, saveFileAs, closeAction;

        newFile = new JMenuItem("New");
        newFile.addActionListener(this);
        newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        _commandsByItem.put(newFile, new CommandNew());

        openFile = new JMenuItem("Open");
        openFile.addActionListener(this);
        openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        _commandsByItem.put(openFile, new CommandOpen(editor));

        saveFile = new JMenuItem("Save");
        saveFile.addActionListener(this);
        saveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        _commandsByItem.put(saveFile, new CommandSave(editor));

        saveFileAs = new JMenuItem("Save as");
        saveFileAs.addActionListener(this);
        _commandsByItem.put(saveFileAs, new CommandSaveAs(editor));

        closeAction = new JMenuItem("Exit", KeyEvent.VK_U);
        closeAction.addActionListener(this);
        _commandsByItem.put(closeAction, new CommandClose(editor));

        file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);
        file.add(saveFileAs);
        file.addSeparator();
        file.add(closeAction);

        edit = setupStyleMenu();
        help = new JMenu("Help");
        help.addActionListener(this);

        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));

        mb.add(file);
        mb.add(edit);
        mb.add(help);
        mb.add(Box.createHorizontalGlue());
        mb.add(label);

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
                editor.setCharacterAttributes(as, false);
                curColor = color;
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
                    isBold = true;
                } else {
                    as.addAttribute(StyleConstants.Bold, false);
                    isBold = false;
                }
                editor.setCharacterAttributes(as, false);
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
                    isItalic = true;
                } else {
                    as.addAttribute(StyleConstants.Italic, false);
                    isItalic = false;
                }
                editor.setCharacterAttributes(as, false);
            }
        });
        mainMenu.add(italic);

        return mainMenu;
    }

    private void setupUI() {
        JPanel panel = new JPanel();
        JLabel fileName = new JLabel();
        editor = new EditorArea(fileName);
        JMenuBar mb = setupMenu(fileName);

//        JTabbedPane tabMenu = new JTabbedPane();
//        tabMenu.addTab("Tab1", editor);
//        tabMenu.addTab("Tab2", new JLabel("hallo"));
//        tabMenu.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        editor.setBounds(0, 0, 360, 320);
        editor.addCaretListener(this);
        editor.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(editor);
        panel.add(mb);
//        panel.add(tabMenu);

        getContentPane().add(panel);
        this.setJMenuBar(mb);
        this.setSize(400, 400);
    }

    public void launch() {
        setupUI();
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
//        ((JButton) e.getSource()).setBackground(Color.RED);
        Command command = _commandsByItem.get(e.getSource());
        command.execute();
    }

    @Override
    public void caretUpdate(CaretEvent e) {
        SimpleAttributeSet as = new SimpleAttributeSet();
        as.addAttribute(StyleConstants.Foreground, curColor);
        as.addAttribute(StyleConstants.Bold, isBold);
        as.addAttribute(StyleConstants.Italic, isItalic);
        editor.setCharacterAttributes(as, false);
    }
}
