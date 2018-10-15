package App;

import EditorCommands.*;

import javax.swing.*;
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

//    private void initButtons(JPanel menu) {
//        _commandsByItem = new HashMap<>();
//        JLabel lab = new JLabel("Hello counter: ");
//        JLabel c = new JLabel(String.format("%d", count));
//        JPanel p = new JPanel();
//        p.add(lab);
//        p.add(c);
//
//        JButton newButton = new JButton("new");
//        newButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                count++;
//                c.setForeground(randColor());
//                c.setText(String.format("%d", count));
//
//            }
//        });
//        _commandsByButton.put(newButton, new CommandNew());
//
//        JButton editButton = new JButton("edit");
//        editButton.addActionListener(this);
//        _commandsByButton.put(editButton, new CommandOpen(editor));
//
//        JButton closeButton = new JButton("close");
//        closeButton.addActionListener(this);
//        _commandsByButton.put(closeButton, new CommandClose(editor));
//
//        menu.add(newButton);
//        menu.add(editButton);
//        menu.add(closeButton);
//
//
//        menu.add(p);
//    }

    private JMenuBar setupMenu(JLabel label){
        JLabel lab = label;
        JMenuBar mb = new JMenuBar();
        JMenu file, edit, help;
        JMenuItem newFile, openFile, saveFile, saveFileAs, closeAction;

        newFile = new JMenuItem("New");
        newFile.addActionListener(this);
        _commandsByItem.put(newFile, new CommandNew());

        openFile = new JMenuItem("Open");
        openFile.addActionListener(this);
        _commandsByItem.put(openFile, new CommandOpen(editor));

        saveFile = new JMenuItem("Save");
        saveFile.addActionListener(this);
        _commandsByItem.put(saveFile, new CommandSave(editor));

        saveFileAs = new JMenuItem("Save as");
        saveFileAs.addActionListener(this);
        _commandsByItem.put(saveFileAs, new CommandSaveAs(editor));

        closeAction = new JMenuItem("Exit");
        closeAction.addActionListener(this);
        _commandsByItem.put(closeAction, new CommandClose(editor));

        file = new JMenu("File");
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);
        file.add(saveFileAs);
        file.addSeparator();
        file.add(closeAction);

        edit = new JMenu("Edit");
        help = new JMenu("Help");

        lab.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));

        mb.add(file);
        mb.add(edit);
        mb.add(help);
        mb.add(Box.createHorizontalGlue());
        mb.add(lab);

        return mb;
    }

    private void setupUI() {
        JPanel panel = new JPanel();
//        JPanel menu = new JPanel();
        JLabel fileName = new JLabel();
        editor = new EditorArea(fileName);
        JMenuBar mb = setupMenu(fileName);

//        BoxLayout box = new BoxLayout(panel, BoxLayout.Y_AXIS);
//        panel.setLayout(box);
//        editor.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 5));
        editor.setBounds(0, 0, 360, 320);
//        editor.setMargin(new Insets(10, 10, 10, 10));

//        initButtons(menu);
//        fileName.setAlignmentX(Component.CENTER_ALIGNMENT);
//        menu.add(fileName);


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
