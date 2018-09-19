import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Editor {
    private ArrayList<String> files = new ArrayList<>();
    private final String PATH = "files/";

    public Editor() {
    }

    public void launch() {
        checkDirectory();

        Scanner reader = new Scanner(System.in);
        String answer;
        do {
            clearScreen();
            System.out.println("Editor menu:");
            System.out.println("1. Create new file");
            System.out.println("2. Write to file");
            System.out.println("3. Print file");
            System.out.println("4. Exit");
            answer = reader.nextLine();
            switch (answer) {
                case "1": {
//                    clearScreen();
                    createNewFile();
                    break;
                }
                case "2": {
//                    clearScreen();
                    String fileName = PATH + chooseFile();
//                    clearScreen();
                    writeToFile(fileName);
                    break;
                }
                case "3": {
//                    clearScreen();
                    String fileName = PATH + chooseFile();
//                    clearScreen();
                    printFile(fileName);
                    break;
                }
            }
        } while (!answer.equals("4"));
    }

    private void checkDirectory(){

        Path path = Paths.get(PATH);
        if (!Files.exists(path)){
            try {
                Files.createDirectory(path);
            } catch (IOExceptionv e){
                e.printStackTrace();
            }
        }

        File folder = new File(PATH);
        for (File fileEntry : folder.listFiles()){
            files.add(fileEntry.getName());
        }

    }

    private String chooseFile(){
        int index = 1;

        System.out.println("Choose file");
        for(String f:files){
            System.out.println(index + ". " + f);
            index++;
        }

        Scanner reader = new Scanner(System.in);
        String answer = reader.nextLine();
        int number = tryParse(answer);
        while (number < 1 || number > files.size()) {
            System.out.println("Incorrect number. Try again");
            answer = reader.nextLine();
            number = tryParse(answer);
        }
        return files.get(number-1);

    }

    private void createNewFile() {
        Scanner reader = new Scanner(System.in);

        System.out.println("Enter file name:");
        String fileName = reader.nextLine();
        System.out.println(fileName);

        try {
            File file = new File(PATH + fileName);
            if (file.createNewFile()) {
                System.out.println("File created.");
                this.files.add(fileName);
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeToFile(String fileName) {
        try {
            PrintWriter fileStream = new PrintWriter(fileName);
            Scanner reader = new Scanner(System.in);

            System.out.println("Enter the text, blank line to stop.");

            while (true) {
                System.out.print(">");
                String line = reader.nextLine();
                if (line.length() == 0) {
                    fileStream.close();
                    break;
                } else {
                    fileStream.println(line);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void printFile(String fileName) {
        try {
            File file = new File(fileName);
            Scanner reader = new Scanner(file);

            while (reader.hasNext()) {
                String line = reader.nextLine();
                Parser parser = new Parser(line);
                String parsedLine = parser.getParsedLine();
                System.out.println(parsedLine);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static Integer tryParse(String answer){
        try {
            return Integer.parseInt(answer);
        } catch (NumberFormatException e){
            return 0;
        }
    }
}
