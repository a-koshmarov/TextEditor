import EditorCommands.Command;
import EditorCommands.CommandFactory;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

//Invoker
public class AppView extends JFrame{
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

    private void setupUI() {
        setSize(400, 400);
        JPanel panel = new JPanel();
        JPanel menu = new JPanel();
        panel.setLayout(new BorderLayout());
        editor.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 5));
        editor.setMargin(new Insets(10, 10, 10, 10));

        CommandFactory cf = new CommandFactory();
        for (Object key:uiSettings.keySet()){
            JButton button = new JButton(uiSettings.getProperty((String) key));
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Command command = cf.getCommand(e.getActionCommand());
                    command.execute();
                }
            });
            menu.add(button);
        }

        panel.add(editor, BorderLayout.CENTER);
        panel.add(menu, BorderLayout.NORTH);
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

//        RTFEditorKit rtf = new RTFEditorKit();
//        editor.setEditorKit(rtf);
//        try {
//            FileInputStream fi = new FileInputStream("file.rtf");
//            rtf.read(fi, editor.getDocument(), 0);
//        } catch (IOException| BadLocationException e) {
//            e.printStackTrace();
//        }
    }
}
