package client;

import client.managerInterface.GenericProgressListener;
import javafx.scene.control.ProgressBar;

public class ProgressBarListener implements GenericProgressListener {

    private ProgressBar progressBar;

    public ProgressBarListener(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    @Override
    public void onProgressChange(int progress, int total) {
        progressBar.setProgress((double) progress / total);
    }
}
