import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

class Editor {

//    Initialize list of filenames and path to their directory
    private ArrayList<String> files = new ArrayList<>();
    private final String PATH = "files/";

    Editor() {
    }

    void launch() {

//        Check whether there is a "files/" directory
//        and add all the files inside it to the list
        checkDirectory();

        Scanner reader = new Scanner(System.in);
        String answer;

//        Create menu loop
        do {
            System.out.println("\u001B[1mEditor menu:\u001B[0m");
            System.out.println("\u001B[1m1.\u001B[0m Create new file");
            System.out.println("\u001B[1m2.\u001B[0m Write to file");
            System.out.println("\u001B[1m3.\u001B[0m Print file");
            System.out.println("\u001B[1m4.\u001B[0m Exit");
            answer = reader.nextLine();
            switch (answer) {
                case "1": {
                    createNewFile();
                    break;
                }
                case "2": {
                    String fileName = PATH + chooseFile();
                    writeToFile(fileName);
                    break;
                }
                case "3": {
                    String fileName = PATH + chooseFile();
                    printFile(fileName);
                    break;
                }
            }
        } while (!answer.equals("4"));
    }

    private void checkDirectory(){

//        Create directory if there is none
        Path path = Paths.get(PATH);
        if (!Files.exists(path)){
            try {
                Files.createDirectory(path);
            } catch (IOException e){
                e.printStackTrace();
            }
        }

//        Get all the files inside the directory
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

//        Try to get the answer and convert it to int
        Scanner reader = new Scanner(System.in);
        String answer = reader.nextLine();
        int number = tryParse(answer);


        while (number < 1 || number > files.size()) {
            System.out.println("Incorrect number. Try again");
            answer = reader.nextLine();
            number = tryParse(answer);
        }

//        Return filename which corresponds to selected index
        return files.get(number-1);

    }

    private void createNewFile() {
        Scanner reader = new Scanner(System.in);

        System.out.println("Enter file name:");
        String fileName = reader.nextLine();
        System.out.println(fileName);

//        Try to create a file if it doesn't exist
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

        System.out.println("Do you want to write to this file? (y/n)");
        String answer = reader.nextLine();
        while (true){
            if (answer.toLowerCase().equals("y")){
                writeToFile(fileName);
                break;
            } else if (answer.toLowerCase().equals("n")) {
                break;
            } else {
                System.out.println("Invalid input. Try again");
                answer = reader.nextLine();
            }
        }
    }

    private void writeToFile(String fileName) {

//        Try to write to file
        try {
//            Open file stream
            PrintWriter fileStream = new PrintWriter(fileName);
            Scanner reader = new Scanner(System.in);

            System.out.println("Enter the text, blank line to stop.");

//            Write to file until user enters blank line
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

//        Try to open file
        try {
            File file = new File(fileName);
            Scanner reader = new Scanner(file);

//            Read file line by line
            while (reader.hasNext()) {
                String line = reader.nextLine();

//                Pass the line to the parser
                Parser parser = new Parser(line);
//                Get formatted line from parser
                String parsedLine = parser.getParsedLine();

                System.out.println(parsedLine);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Integer tryParse(String answer){

//        Return parsed int or 0 if parser throws exception
        try {
            return Integer.parseInt(answer);
        } catch (NumberFormatException e){
            return 0;
        }
    }
}
