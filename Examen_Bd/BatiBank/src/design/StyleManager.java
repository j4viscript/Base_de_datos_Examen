package design;

import java.awt.*;

public class StyleManager {

    //declare font

    public static Font font = new Font("Gotham",Font.BOLD, 70);

    public static Font getfont() {
        return font;
    }

    public static Color noColor = new Color(0,0,0,0);

    public static Color spaceGray = new Color(40,40,40);

    public static Color whiteGray = new Color(220,220,220);

    public static Color darkGray = new Color(200,200,200);

    public static Color getButtons() {
        return Buttons;
    }

    public static void setButtons(Color buttons) {
        Buttons = buttons;
    }

    public static Color Buttons = new Color(226, 199, 11);
}
