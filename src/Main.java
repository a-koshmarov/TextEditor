import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
	    Editor editor = new Editor();

//	    editor.createNewFile();
        editor.launch();


//        System.out.println("\u001B[31m\u001B[1mText goes here\u001B[0m");
    }
}
