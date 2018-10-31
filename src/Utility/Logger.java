package Utility;

public class Logger {
    public static void measureTime(TransactionWithException transaction){
        System.out.println("Start of transaction");
        try {
            double startTime = System.nanoTime();
            transaction.run();
            System.out.println("Time: " + (System.nanoTime() - startTime) / 1e9);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
