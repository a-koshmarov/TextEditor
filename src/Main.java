import BL.AuthorizationWizard;
import BL.Session;
import BL.User;
import App.AppView;
import DAL.Connector;

import javax.swing.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Connector connector = new Connector();

        User user = AuthorizationWizard.logInUser("bob dylan", "123");
//        AppView app = new AppView(new Session(user));
        Session session = new Session(user);
        session.printFiles();

        String ans = JOptionPane.showInputDialog(null, "select filename");
        session.newFile(ans);
        session.printFiles();
    }
}

