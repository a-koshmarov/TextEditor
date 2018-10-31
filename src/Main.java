import BL.AuthorizationWizard;
import BL.Session;
import BL.User;
import App.AppView;
import DAL.Connector;
import Utility.DirectoryListing;

public class Main {

    public static void main(String[] args) {
        Connector connector = new Connector();
        User user = AuthorizationWizard.logInUser("bob dylan", "123");
        AppView app = new AppView(new Session(user));
        app.launch();
<<<<<<< HEAD
=======
//        DirectoryListing.getListing("files");
>>>>>>> develop
    }
}
