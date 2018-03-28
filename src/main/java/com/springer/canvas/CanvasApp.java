package com.springer.canvas;

import com.springer.canvas.service.implementation.Canvas;
import com.springer.canvas.utils.CommandsEnum;
import com.springer.canvas.utils.Constants;

import static com.springer.canvas.utils.CommandsEnum.getCommand;
import static java.lang.System.console;
import static java.lang.System.out;

public class CanvasApp {

    public static void main(String... args) {

        Canvas canvas = new Canvas();
        boolean quitCanvas = false;
        do {
            out.print("enter command: ");
            String input = console().readLine();

            // Check if input is informed
            if ((input == null) || (input.trim().equals(""))) {
                Constants.printMessageInConsole();
                continue;
            }

            try {
                // Check if is a valid command, if not is thrown an exception
                CommandsEnum option = getCommand(input.trim());
                switch (option) {
                    case CREATE:
                        canvas = canvas.createCanvas(input);
                        break;
                    case LINE:
                        canvas = canvas.drawLine(input);
                        break;
                    case RECTANGLE:
                        canvas = canvas.drawRectangle(input);
                        break;
                    case BUCKET_FILL:
                        canvas = canvas.bucketFill(input);
                        break;
                    case QUIT:
                        quitCanvas = true;
                        break;
                }

                // Draw Canvas in console if is created and we are not leaving the application
                if ((canvas != null) && (!option.equals(CommandsEnum.QUIT))) out.println(canvas);
            }
            catch (IllegalArgumentException iae) {
                Constants.printMessageInConsole();
            }
            catch (IllegalStateException ise) {
                out.println(ise.getMessage());
            }

        } while(!quitCanvas);
    }
}
