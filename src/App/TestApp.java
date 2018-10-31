package App;

import com.sun.tools.javac.Main;

import javax.swing.*;

public class TestApp {
    public TestApp(){
        JTextField firstName = new JTextField();
        JTextField lastName = new JTextField();
        JPasswordField password = new JPasswordField();
        final JComponent[] inputs = new JComponent[] {
                new JLabel("First"),
                firstName,
                new JLabel("Last"),
                lastName,
                new JLabel("Password"),
                password
        };
        int result = JOptionPane.showConfirmDialog(null, inputs, "My custom dialog", JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            System.out.println("You entered " +
                    firstName.getText() + ", " +
                    lastName.getText() + ", " +
                    password.getText());
        } else {
            System.out.println("User canceled / closed the dialog, result = " + result);
        }
    }

    public static void main(String[] args) {
        TestApp ta = new TestApp();
    }
}
