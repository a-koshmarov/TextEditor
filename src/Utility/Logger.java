package Utility;

import java.io.PrintStream;

public class Logger {

    private static Logger logger = new Logger();
    private PrintStream stream = System.out;

    private Logger(){
    }

    public static Logger getInstance(){
        return logger;
    }

    public void setOutput(PrintStream stream){
        this.stream = stream;
    }

    public void log(TransactionWithException transaction){
        this.stream.println("-- Start of transaction");
        try {
            double startTime = System.nanoTime();
            transaction.run();
            System.out.println("-- Time: " + (System.nanoTime() - startTime) / 1e9);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public <T> T logWithReturn(TransactionWithReturn<T> transaction){
        this.stream.println("-- Start of transaction");
        try {
            double startTime = System.nanoTime();
            T object = transaction.runWithReturn();
            System.out.println("-- Time: " + (System.nanoTime() - startTime) / 1e9);
            return object;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
