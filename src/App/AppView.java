package App;

import EditorCommands.*;
import Utility.CustomColor;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Random;

//Invoker
public class AppView extends JFrame implements ActionListener {
    private HashMap<Object, Command> _commandsByItem;
    private EditorArea editor;
    private int count = 0;

    public AppView() {
        super("Text editor");
        _commandsByItem = new HashMap<>();
    }
    private Color randColor(){
        Random random = new Random();
        float r = random.nextFloat();
        float g = random.nextFloat();
        float b = random.nextFloat();
        return new Color(r, g, b);
    }

    private JMenuBar setupMenu(JLabel label){
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

        edit = new JMenu("Edit");
        edit.setMnemonic(KeyEvent.VK_E);
        edit.add(setupStyleMenu());
        help = new JMenu("Help");

        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));

        mb.add(file);
        mb.add(edit);
        mb.add(help);
        mb.add(Box.createHorizontalGlue());
        mb.add(label);

        return mb;
    }

    private JMenu setupStyleMenu(){
        HashMap<Object, Color>  _colorsByItem = new HashMap<>();
        JMenu menu = new JMenu("Colors");
        menu.setMnemonic(KeyEvent.VK_C);


        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JMenuItem jmi = (JMenuItem) e.getSource();
                Color color = _colorsByItem.get(jmi);
                SimpleAttributeSet as = new SimpleAttributeSet();
                as.addAttribute(StyleConstants.Foreground, color);
                editor.setCharacterAttributes(as, false);
            }
        };

        for (CustomColor col : CustomColor.colors){
            JMenuItem item = new JMenuItem(col.toString());
            item.addActionListener(al);
            menu.add(item);
            _colorsByItem.put(item, col.getColor());
        }

        return menu;
    }
    private void setupUI() {
        JPanel panel = new JPanel();
        JLabel fileName = new JLabel();
        editor = new EditorArea(fileName);
        JMenuBar mb = setupMenu(fileName);

        editor.setBounds(0, 0, 360, 320);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//        panel.add(fileName);
//        fileName.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(editor);
        panel.add(mb);

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
}
