import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

//Invoker
public class AppUI {
    private HashMap<String, Command> _commands = new HashMap<>();
    private Properties uiSettings;

    public AppUI(String settingsFile) {
        uiSettings = new Properties();
        try {
            uiSettings.load(new FileInputStream(settingsFile));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayMenu() {
        System.out.println("Editor menu:");
        for (Object key : uiSettings.keySet()){
            System.out.println(key+". "+uiSettings.getProperty((String) key));
        }
    }

    private void registerCommands() {
        CommandFactory cf = new CommandFactory();
        for (Object key : uiSettings.keySet()) {
            _commands.put((String) key, cf.getCommand(uiSettings.getProperty((String) key)));
        }
    }

    public void launch(){
        registerCommands();
        displayMenu();
    }
}
