Overview
========
I have developed my test in Java 8 with maven, no external libraries are used.

Commands are accepted in uppercase or lowercase.

You can overwrite lines or rectangles over other lines rectangles or painted canvas, but you only can paint in an empty position.

Valid characters to paint with bucket fill are letters, either uppercase or lowercase, or numbers.

## Exceptions
Throwing IllegalStateException when creating a canvas created previously, or when attempting to draw when canvas is not created yet.
Throwing IllegalArgumentException when input is not correct:
- Option is not recognised
- Number of parameters are not valid
- Parameters have wrong format
- Is not possible to draw a line or rectangle with parameters provided or are out of the boundaries of Canvas.

## Installation and running
You can install the application making a package of the project and output to a "jar" file with the command `mvn package`.

To start the application, you can execute `java -cp target/canvas-1.0-SNAPSHOT.jar com.springer.canvas.CanvasApp`.
