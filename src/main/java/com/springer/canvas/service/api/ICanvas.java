package com.springer.canvas.service.api;

public interface ICanvas {

    ICanvas createCanvas(String input);
    ICanvas drawLine(String input);
    ICanvas drawRectangle(String input);
    ICanvas bucketFill(String input);
}
