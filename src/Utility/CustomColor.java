package Utility;

import java.awt.*;
import java.util.HashMap;

public class CustomColor{
    private final Color color;
    private final String name;
    public static final CustomColor[] colors = {
            new CustomColor(Color.BLACK, "Black"),
            new CustomColor(Color.RED, "Red"),
            new CustomColor(Color.GREEN, "Green"),
            new CustomColor(Color.BLUE, "Blue"),
            new CustomColor(Color.MAGENTA, "Magenta"),
            new CustomColor(Color.YELLOW, "Yellow"),
            new CustomColor(Color.ORANGE, "Orange"),
    };
    private HashMap<Color, Integer> indexedColors = new HashMap<>();

    public CustomColor(){
        this.color = Color.BLACK;
        this.name = "Black";
        indexedColors.put(Color.black, 0);
        indexedColors.put(Color.red, 1);
        indexedColors.put(Color.green, 2);
        indexedColors.put(Color.blue, 3);
        indexedColors.put(Color.magenta, 4);
        indexedColors.put(Color.yellow, 5);
        indexedColors.put(Color.orange, 6);
    }

    public CustomColor(Color color, String name) {
        this.color = color;
        this.name = name;
        indexedColors.put(Color.black, 0);
        indexedColors.put(Color.red, 1);
        indexedColors.put(Color.green, 2);
        indexedColors.put(Color.blue, 3);
        indexedColors.put(Color.magenta, 4);
        indexedColors.put(Color.yellow, 5);
        indexedColors.put(Color.orange, 6);
    }

    @Override
    public String toString() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public int getIndexByColor(Color color){
        return indexedColors.get(color);
    }

    public static Color getColorByIndex(int index){
        return colors[index].color;
    }
}
