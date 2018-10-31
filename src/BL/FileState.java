package BL;

import DAL.DTO.FileStateDTO;
import Utility.CustomColor;

import java.awt.*;

public class FileState {
    private int userID;
    private String fileName;
    private int cursor;
    private Color color;
    private boolean italic;
    private boolean bold;
    private boolean opened;

    public FileState(int ID, String fileName){
        this.userID = ID;
        this.fileName = fileName;
        this.cursor = 0;
        this.color = Color.BLACK;
        this.italic = false;
        this.bold = false;
        this.opened = true;
    }

    public FileState(FileStateDTO file){
        this.userID = file.getUserID();
        this.fileName = file.getFileName();
        this.cursor = file.getCursor();
        this.color = CustomColor.getColorByIndex(file.getColor());
        this.italic = file.getItalic() == 1;
        this.bold = file.getBold() == 1;
        this.opened = file.getOpened() == 1;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getCursor() {
        return cursor;
    }

    public void setCursor(int cursor) {
        this.cursor = cursor;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isItalic() {
        return italic;
    }

    public void setItalic(boolean italic) {
        this.italic = italic;
    }

    public boolean isBold() {
        return bold;
    }

    public void setBold(boolean bold) {
        this.bold = bold;
    }

    public boolean isOpened() {
        return opened;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %s", userID, fileName, cursor, opened);
    }
}
