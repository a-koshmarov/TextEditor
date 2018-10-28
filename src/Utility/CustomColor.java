package Utility;

import java.awt.*;

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

    public CustomColor(Color color, String name) {
        this.color = color;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public static Color getColorByIndex(int index){
        return colors[index].color;
    }
}
