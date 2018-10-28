import BL.AuthorizationWizard;
import BL.Session;
import BL.User;
import GUI.AppView;

public class Main {

    public static void main(String[] args) {
        User user = AuthorizationWizard.logInUser("bob dylan", "123");
        AppView app = new AppView(new Session(user));
        app.launch();
    }
}
