package Utility;

import java.io.OutputStream;

public class Logger {

    private static Logger logger = new Logger();
    private OutputStream os;

    private Logger(){

    }

    public static Logger getInstance(){
        return logger;
    }

    public void setOutput(OutputStream os){
        this.os = os;
    }

    public void log(TransactionWithException transaction){
        this.os.write();println("-- Start of transaction");
        try {
            double startTime = System.nanoTime();
            transaction.run();
            System.out.println("-- Time: " + (System.nanoTime() - startTime) / 1e9);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static <T> T logWithReturn(TransactionWithReturn<T> transaction){
        System.out.println("-- Start of transaction");
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
