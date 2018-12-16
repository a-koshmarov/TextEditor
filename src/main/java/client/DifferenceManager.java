package client;

import client.managerInterface.GenericProgressListener;
import dao.FileStateEntity;
import javafx.concurrent.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class DifferenceManager extends Task<String> {

    private List<GenericProgressListener> listeners = new ArrayList<>();
    private FileStateEntity firstFile;
    private FileStateEntity secondFile;

    public DifferenceManager(FileStateEntity firstFile, FileStateEntity secondFile) {
        this.firstFile = firstFile;
        this.secondFile = secondFile;
    }

    @Override
    public String call() throws Exception {
        String first = firstFile.getContent();
        String second = secondFile.getContent();

        String[] firstSplit = first.split("\\r?\\n");
        List<String> secondSplit = Arrays.asList(second.split("\\r?\\n"));

        Iterator<String> it = secondSplit.iterator();

        List<String> result = new ArrayList<>();

        int total = firstSplit.length>=secondSplit.size()?firstSplit.length:secondSplit.size();
        int current = 0;

        for (String firstLine : firstSplit) {
            if (it.hasNext()) {
                String secondLine = it.next();
                if (firstLine.equals(secondLine)) {
                    result.add(firstLine);
                } else {
                    result.add("- " + firstLine);
                    if (!secondLine.isEmpty()) {
                        result.add("+ " + secondLine);
                    }
                }
            } else {
                if (!firstLine.isEmpty()) {
                    result.add("- " + firstLine);
                }
            }
        }

        while (it.hasNext()) {
            String secondLine = it.next();
            if (!secondLine.isEmpty()) {
                result.add("+ " + secondLine);
            }
        }

        for (int i = 0; i <= 1000; i++) {
            if (i%100==0){
                notifyAllListeners(i, 1000);
            }
            
            Thread.sleep(5);
        }

        return String.join("\n", result);
    }

    public void registerListener(GenericProgressListener listener){
        this.listeners.add(listener);
    }

    private void notifyAllListeners(int progress, int total){
        this.listeners.forEach(listener -> listener.onProgressChange(progress, total));
    }

}
