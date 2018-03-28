package com.springer.canvas.utils;

import static java.lang.System.out;

public class Constants {

    public static final String HORIZONTAL_BORDER = "-";
    public static final String VERTICAL_BORDER = "|";
    public static final String LINE_CHARACTER = "x";
    public static final String EMPTY_PIXEL = " ";

    public static void printMessageInConsole() {
        out.println("Invalid command, wrong number of parameters, wrong format or position!");
        out.println("Valid commands:");
        out.println("  Create canvas:  C w h");
        out.println("  Draw line:      L x1 y1 x2 y2");
        out.println("  Draw rectangle: R x1 y1 x2 y2");
        out.println("  Bucket fill:    B x y c");
        out.println("  Quit program:   Q");
        out.println();
    }
}
