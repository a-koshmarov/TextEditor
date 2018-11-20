package DAL.DTO;

import BL.FileState;
import Utility.CustomColor;

public class FileStateDTO {
    private int userID;
    private String fileName;
    private int cursor;
    private int color;
    private int italic;
    private int bold;
    private int opened;

    public FileStateDTO(){
    }

    public FileStateDTO(FileState fileState){
        this.userID = fileState.getUserID();
        this.fileName = fileState.getFileName();
        this.cursor = fileState.getCursor();
        CustomColor customColor = new CustomColor();
        this.color = customColor.getIndexByColor(fileState.getColor());
        this.italic = fileState.isItalic() ? 1 : 0;
        this.bold = fileState.isBold() ? 1 : 0;
        this.opened = fileState.isOpened() ? 1 : 0;
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

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getItalic() {
        return italic;
    }

    public void setItalic(int italic) {
        this.italic = italic;
    }

    public int getBold() {
        return bold;
    }

    public void setBold(int bold) {
        this.bold = bold;
    }

    public int getOpened() {
        return opened;
    }

    public void setOpened(int opened) {
        this.opened = opened;
    }
}