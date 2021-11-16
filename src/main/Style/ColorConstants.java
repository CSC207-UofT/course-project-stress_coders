package Style;

import java.util.HashMap;
import java.util.Map;

/**
 * Class used to add style and color to console
 */
public class ColorConstants {
    public static final Map<String, String> COLORS = new HashMap<>();

    static  {
        COLORS.put("RESET", "\u001B[0m");
        COLORS.put("BLACK_BACKGROUND","\u001B[30m");
        COLORS.put("RED","\u001B[31m");
        COLORS.put("RED_BACKGROUND","\u001B[41m");
        COLORS.put("GREEN","\u001B[32m");
        COLORS.put("GREEN_BACKGROUND","\u001B[42m");
        COLORS.put("YELLOW","\u001B[33m");
        COLORS.put("YELLOW_BACKGROUND","\u001B[43m");
        COLORS.put("BLUE","\u001B[34m");
        COLORS.put("BLUE_BACKGROUND","\u001B[44m");
        COLORS.put("PURPLE","\u001B[35m");
        COLORS.put("PURPLE_BACKGROUND","\u001B[45m");
        COLORS.put("CYAN","\u001B[36m");
        COLORS.put("CYAN_HIGHLIGHT","\u001B[46m");
        COLORS.put("WHITE","\u001B[37m");
        COLORS.put("WHITE_BACKGROUND","\u001B[47m");
    }

    public static String getColorCode(String color) {return COLORS.get(color);}
}
