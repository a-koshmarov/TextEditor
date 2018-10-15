package App;

import EditorCommands.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

//Invoker
public class AppView extends JFrame implements ActionListener {
    private HashMap<JButton, Command> _commandsByButton;
    private EditorArea editor;

    public AppView() {
        super("Text editor");
    }

    private void initButtons(JPanel menu) {
        JPanel buttonPanel = new JPanel();
        _commandsByButton = new HashMap<>();

        JButton newButton = new JButton("new");
        newButton.addActionListener(this);
        _commandsByButton.put(newButton, new CommandNew());

        JButton editButton = new JButton("edit");
        editButton.addActionListener(this);
        _commandsByButton.put(editButton, new CommandEdit(editor));

        JButton closeButton = new JButton("close");
        closeButton.addActionListener(this);
        _commandsByButton.put(closeButton, new CommandClose(editor));

        menu.add(newButton);
        menu.add(editButton);
        menu.add(closeButton);


    }

    private void setupUI() {
        setSize(400, 400);
        JPanel panel = new JPanel();
        JPanel menu = new JPanel();
        JLabel fileName = new JLabel();

        editor = new EditorArea(fileName);

        BoxLayout box = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(box);
        editor.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 5));
        editor.setMargin(new Insets(10, 10, 10, 10));

        initButtons(menu);
        fileName.setAlignmentX(Component.CENTER_ALIGNMENT);
//        menu.add(fileName);

        panel.add(fileName);
        panel.add(editor);

        panel.add(menu);

        getContentPane().add(panel);
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
        Command command = _commandsByButton.get((JButton) e.getSource());
        command.execute();
    }
}
