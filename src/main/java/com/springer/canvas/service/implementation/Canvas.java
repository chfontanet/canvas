package com.springer.canvas.service.implementation;

import com.springer.canvas.service.api.ICanvas;
import com.springer.canvas.utils.Constants;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Canvas implements ICanvas {

    private String[][] layout;
    private int width1 = 0;
    private int width2 = 0;
    private int height1 = 0;
    private int height2 = 0;

    @Override
    public Canvas createCanvas(String input) {
        if (layout != null)
            throw new IllegalStateException("Canvas previously initialized!");

        // Initialize boundaries of Canvas for its use in drawing
        final String[] parameters = getParameters(input);
        width2 = checkBoundaries(parameters[0]);
        height2 = checkBoundaries(parameters[1]);
        final int columns = width2+2;
        final int rows = height2 +2;

        layout = new String[rows][columns];

        // Create the layout with borders
        IntStream.range(0, layout.length).forEach(x -> Arrays.setAll(
                layout[x], y -> {
                    if ((x == 0) || (x == rows-1)) return Constants.HORIZONTAL_BORDER;
                    else if ((y == 0) || (y == columns-1)) return Constants.VERTICAL_BORDER;
                    return Constants.EMPTY_PIXEL;
                }
        ));

        return this;
    }

    @Override
    public Canvas drawLine(String input) {
        if (layout == null)
            throw new IllegalStateException("Canvas not initialized!");

        // Check if values for line are valid
        final int[] positions = checkAndOrderPairValuesInLine(getParameters(input));

        draw(positions);
        return this;
    }

    @Override
    public Canvas drawRectangle(String input) {
        if (layout == null)
            throw new IllegalStateException("Canvas not initialized!");

        // Check if values for rectangle are valid
        final int[] positions = checkAndOrderValuesInRectangle(getParameters(input));

        // Draw lines individually
        //Top line: x1, y1, x2, y1
        final int[] topLine = new int[]{positions[0], positions[1], positions[2], positions[1]};
        draw(topLine);
		//Left line: x1, y1, x1, y2
        final int[] leftLine = new int[]{positions[0], positions[1], positions[0], positions[3]};
        draw(leftLine);
		//Right line: x2, y1, x2, y2
        final int[] rightLine = new int[]{positions[2], positions[1], positions[2], positions[3]};
        draw(rightLine);
		//Bottom line: x1, y2, x2, y2
        final int[] bottomLine = new int[]{positions[0], positions[3], positions[2], positions[3]};
        draw(bottomLine);

        return this;
    }

    @Override
    public Canvas bucketFill(String input) {
        if (layout == null)
            throw new IllegalStateException("Canvas not initialized!");

        // Check values for bucket fill are valid
        final String[] parameters = getParameters(input);
        final int[] positions = checkValuesInBucketFill(parameters);
        final String colour = parameters[2];

        paint(positions[0], positions[1], colour);

        return this;
    }

    // Method to get the parameters received
    private String[] getParameters(String input) {
        return input.substring(2).split(" ");
    }

    /**
     * Override toString method to return a String with the Canvas to write in console
     */
    @Override
    public String toString() {
        return Arrays.stream(layout).map(a -> String.join("", a)).collect(Collectors.joining("\n"));
    }

    /**
     * Method to draw a line based in the received parameters
     * @param positions array with the values x1, y1, x2, y2
     */
    private void draw(int[] positions) {
        // HORIZONTAL VALUES: Parameters[0] = x1 and parameters[2] = x2
        // VERTICAL VALUES: Parameters[1] = y1 and parameters[3] = y2
        // Draw horizontal line
        if (isHorizontalLine(positions)) {
            // Draw only affected row
            for (int x = width1; x <= width2; x++) {
                if ((x >= positions[0]) && (x <= positions[2])) {
                    layout[positions[1]][x] = Constants.LINE_CHARACTER;
                }
            }
        }
        // Draw vertical line
        else {
            // Draw only affected column
            for (int y = height1; y <= height2; y++) {
                if ((y >= positions[1]) && (y <= positions[3])) {
                    layout[y][positions[0]] = Constants.LINE_CHARACTER;
                }
            }
        }
    }

    /**
     * Method to paint bucketFill tool
     * @param row position in the row
     * @param column position in the column
     * @param colour character used as a colour
     */
    private void paint(int row, int column, String colour) {
        // Valid position
        if ((row > width1) && (row <= width2) && (column > height1) && (column <= height2)) {
            // Only paint if is an empty position
            if (layout[column][row].equals(Constants.EMPTY_PIXEL)) {
                layout[column][row] = colour;
                paint(row+1, column, colour);
                paint(row - 1, column, colour);
                paint(row, column - 1, colour);
                paint(row, column+1, colour);
            }
        }
    }

    // Check valid position when creating Canvas
    private int checkBoundaries(String value) {
        int valueInt = Integer.parseInt(value);
        if (valueInt <= 0)
            throw new IllegalArgumentException();
        return valueInt;
    }

    // Check valid horizontal position between boundaries of Canvas
    private int checkHorizontalPosition(String value) {
        int valueInt = Integer.parseInt(value);
        if ((valueInt <= width1) || (valueInt > width2))
            throw new IllegalArgumentException();
        return valueInt;
    }

    // Check valid vertical position between boundaries of Canvas
    private int checkVerticalPosition(String value) {
        int valueInt = Integer.parseInt(value);
        if ((valueInt <= height1) || (valueInt > height2))
            throw new IllegalArgumentException();
        return valueInt;
    }

    /**
     * Check valid values for line, inside boundaries of Canvas and valid positions to draw a line
     * @param values array with the values x1, y1, x2, y2
     * @return an array with converted values to int, ordered in pairs (horizontal and vertical) from lowest to highest
     */
    private int[] checkAndOrderPairValuesInLine(String[] values) {
        int x1 = checkHorizontalPosition(values[0]);
        int y1 = checkVerticalPosition(values[1]);
        int x2 = checkHorizontalPosition(values[2]);
        int y2 = checkVerticalPosition(values[3]);

        int[] result;
        // Check there are two pairs of values
        if (((x1 != x2) && (y1 != y2)) || ((x1 == x2) && (y1 == y2)))
            throw new IllegalArgumentException();

        // Order array with higher value at the end
        if (x1 == x2) {
            result = (y1 > y2) ? new int[]{x1, y2, x2, y1} : new int[]{x1, y1, x2, y2};
        }
        else {
            result = (x1 > x2) ? new int[]{x2, y1, x1, y2} : new int[]{x1, y1, x2, y2};
        }
        return result;
    }

    /**
     * Check valid values for rectangle, inside boundaries of Canvas and drawing a valid rectangle
     * @param values array with the values x1, y1, x2, y2
     * @return an array with converted values to int, ordered from top/left to bottom/right
     */
    private int[] checkAndOrderValuesInRectangle(String[] values) {
        int x1 = checkHorizontalPosition(values[0]);
        int y1 = checkVerticalPosition(values[1]);
        int x2 = checkHorizontalPosition(values[2]);
        int y2 = checkVerticalPosition(values[3]);

        int[] result;
        // Check pair values are different to create a square
        if ((x1 == x2) || (y1 == y2))
            throw new IllegalArgumentException();

        // Order array with higher value at the end
        if (x1 > x2) {
            result = (y1 > y2) ? new int[]{x2, y2, x1, y1} : new int[]{x2, y1, x1, y2};
        }
        else {
            result = (y1 > y2) ? new int[]{x1, y2, x2, y1} : new int[]{x1, y1, x2, y2};
        }
        return result;
    }

    /**
     * Check values for bucket fill, inside boundaries of Canvas
     * @param values array with the values x1, y1 (Colour is ignored)
     * @return an array with converted values to int
     */
    private int[] checkValuesInBucketFill(String [] values) {
        int x = checkHorizontalPosition(values[0]);
        int y = checkVerticalPosition(values[1]);

        return new int[]{x, y};
    }

    // Check if line to draw is horizontal or vertical
    private boolean isHorizontalLine(int[] values) {
        return values[1] == values[3];
    }
}
