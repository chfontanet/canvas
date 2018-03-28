package com.springer.canvas.service.implementation;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CanvasTest {

    private Canvas canvas;

    @Before
    public void setUp() {
        canvas = new Canvas();
    }

    @Test
    public void testCreateCanvas() {
        Canvas result = canvas.createCanvas("C 20 4");
        assertEquals(result.toString(), getDefaultLayout());
    }

    @Test
    public void testCreateBigCanvas() {
        Canvas result = canvas.createCanvas("C 200 20");
        assertEquals(result.toString(), getBigLayout());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateCanvasInvalidWidthZero() {
        canvas.createCanvas("C 0 4");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateCanvasInvalidWidthNegative() {
        canvas.createCanvas("C -3 4");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateCanvasInvalidHeightZero() {
        canvas.createCanvas("C 20 0");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateCanvasInvalidHeightNegative() {
        canvas.createCanvas("C 20 -3");
    }

    @Test(expected = IllegalStateException.class)
    public void testCreateCanvasPreviouslyCreated() {
        canvas.createCanvas("C 20 4");
        canvas.createCanvas("C 20 4");
    }

    @Test(expected = IllegalStateException.class)
    public void testDrawLineCanvasNotCreated() {
        canvas.drawLine("L 1 2 6 2");
    }

    @Test
    public void testDrawHorizontalLine() {
        canvas.createCanvas("C 20 4");
        Canvas result = canvas.drawLine("L 1 2 6 2");
        assertEquals(result.toString(), getLayoutWithHorizontalLine());
    }

    @Test
    public void testDrawVerticalLine() {
        canvas.createCanvas("C 20 4");
        Canvas result = canvas.drawLine("L 6 3 6 4");
        assertEquals(result.toString(), getLayoutWithVerticalLine());
    }

    @Test
    public void testDrawHorizontalAndVerticalLine() {
        canvas.createCanvas("C 20 4");
        canvas.drawLine("L 1 2 6 2");
        Canvas result = canvas.drawLine("L 6 3 6 4");
        assertEquals(result.toString(), getLayoutWithHorizontalAndVerticalLine());
    }

    @Test
    public void testDrawHorizontalLineInvertedValues() {
        canvas.createCanvas("C 20 4");
        Canvas result = canvas.drawLine("L 6 2 1 2");
        assertEquals(result.toString(), getLayoutWithHorizontalLine());
    }

    @Test
    public void testDrawVerticalLineInvertedValues() {
        canvas.createCanvas("C 20 4");
        Canvas result = canvas.drawLine("L 6 4 6 3");
        assertEquals(result.toString(), getLayoutWithVerticalLine());
    }

    @Test(expected = IllegalStateException.class)
    public void testDrawRectangleCanvasNotCreated() {
        canvas.drawRectangle("R 16 1 20 3");
    }

    @Test
    public void testDrawRectangle() {
        canvas.createCanvas("C 20 4");
        Canvas result = canvas.drawRectangle("R 16 1 20 3");
        assertEquals(result.toString(), getLayoutWithRectangle());
    }

    @Test
    public void testDrawRectangleInvertedHorizontalValues() {
        canvas.createCanvas("C 20 4");
        Canvas result = canvas.drawRectangle("R 20 1 16 3");
        assertEquals(result.toString(), getLayoutWithRectangle());
    }

    @Test
    public void testDrawRectangleInvertedVerticalValues() {
        canvas.createCanvas("C 20 4");
        Canvas result = canvas.drawRectangle("R 16 3 20 1");
        assertEquals(result.toString(), getLayoutWithRectangle());
    }

    @Test
    public void testDrawRectangleInvertedAllValues() {
        canvas.createCanvas("C 20 4");
        Canvas result = canvas.drawRectangle("R 20 3 16 1");
        assertEquals(result.toString(), getLayoutWithRectangle());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDrawRectangleOutOfBoundsLeft() {
        canvas.createCanvas("C 20 4");
        canvas.drawRectangle("R 0 1 20 3");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDrawRectangleOutOfBoundsRight() {
        canvas.createCanvas("C 20 4");
        canvas.drawRectangle("R 16 1 21 3");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDrawRectangleOutOfBoundsTop() {
        canvas.createCanvas("C 20 4");
        canvas.drawRectangle("R 16 0 20 3");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDrawRectangleOutOfBoundsBottom() {
        canvas.createCanvas("C 20 4");
        canvas.drawRectangle("R 16 1 20 5");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDrawRectangleSameWidth() {
        canvas.createCanvas("C 20 4");
        canvas.drawRectangle("R 16 1 16 3");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDrawRectangleSameHeight() {
        canvas.createCanvas("C 20 4");
        canvas.drawRectangle("R 16 3 20 3");
    }

    @Test(expected = IllegalStateException.class)
    public void testBucketFillCanvasNotCreated() {
        canvas.bucketFill("B 10 3 o");
    }

    @Test
    public void testBucketFill() {
        canvas.createCanvas("C 20 4");
        Canvas result = canvas.bucketFill("B 10 3 o");
        assertEquals(result.toString(), getBucketFill());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBucketFillOutOfBoundsLeft() {
        canvas.createCanvas("C 20 4");
        canvas.bucketFill("B 0 3 o");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBucketFillOutOfBoundsRight() {
        canvas.createCanvas("C 20 4");
        canvas.bucketFill("B 21 3 o");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBucketFillOutOfBoundsTop() {
        canvas.createCanvas("C 20 4");
        canvas.bucketFill("B 10 0 o");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBucketFillOutOfBoundsBottom() {
        canvas.createCanvas("C 20 4");
        canvas.bucketFill("B 10 5 o");
    }

    @Test
    public void testBucketFillInNotEmptyPosition() {
        canvas.createCanvas("C 20 4");
        canvas.drawLine("L 1 2 6 2");
        Canvas result = canvas.bucketFill("B 3 2 o");
        assertEquals(result.toString(), getLayoutWithHorizontalLine());
    }

    @Test
    public void testBucketFillOutsideRectangle() {
        canvas.createCanvas("C 20 4");
        canvas.drawRectangle("R 16 1 20 3");
        Canvas result = canvas.bucketFill("B 10 3 o");
        assertEquals(result.toString(), getLayoutWithBucketFillOutsideRectangle());
    }

    @Test
    public void testBucketFillInsideRectangle() {
        canvas.createCanvas("C 20 4");
        canvas.drawRectangle("R 16 1 20 3");
        Canvas result = canvas.bucketFill("B 17 2 o");
        assertEquals(result.toString(), getLayoutWithBucketFillInsideRectangle());
    }

    private String getDefaultLayout() {
        return "----------------------\n"
                + "|                    |\n"
                + "|                    |\n"
                + "|                    |\n"
                + "|                    |\n"
                + "----------------------";
    }

    private String getBigLayout() {
        return "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n"
                + "|                                                                                                                                                                                                        |\n"
                + "|                                                                                                                                                                                                        |\n"
                + "|                                                                                                                                                                                                        |\n"
                + "|                                                                                                                                                                                                        |\n"
                + "|                                                                                                                                                                                                        |\n"
                + "|                                                                                                                                                                                                        |\n"
                + "|                                                                                                                                                                                                        |\n"
                + "|                                                                                                                                                                                                        |\n"
                + "|                                                                                                                                                                                                        |\n"
                + "|                                                                                                                                                                                                        |\n"
                + "|                                                                                                                                                                                                        |\n"
                + "|                                                                                                                                                                                                        |\n"
                + "|                                                                                                                                                                                                        |\n"
                + "|                                                                                                                                                                                                        |\n"
                + "|                                                                                                                                                                                                        |\n"
                + "|                                                                                                                                                                                                        |\n"
                + "|                                                                                                                                                                                                        |\n"
                + "|                                                                                                                                                                                                        |\n"
                + "|                                                                                                                                                                                                        |\n"
                + "|                                                                                                                                                                                                        |\n"
                + "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------";
    }

    private String getLayoutWithHorizontalLine() {
        return "----------------------\n"
                + "|                    |\n"
                + "|xxxxxx              |\n"
                + "|                    |\n"
                + "|                    |\n"
                + "----------------------";
    }

    private String getLayoutWithVerticalLine() {
        return "----------------------\n"
                + "|                    |\n"
                + "|                    |\n"
                + "|     x              |\n"
                + "|     x              |\n"
                + "----------------------";
    }

    private String getLayoutWithHorizontalAndVerticalLine() {
        return "----------------------\n"
                + "|                    |\n"
                + "|xxxxxx              |\n"
                + "|     x              |\n"
                + "|     x              |\n"
                + "----------------------";
    }

    private String getLayoutWithRectangle() {
        return "----------------------\n"
                + "|               xxxxx|\n"
                + "|               x   x|\n"
                + "|               xxxxx|\n"
                + "|                    |\n"
                + "----------------------";
    }

    private String getBucketFill() {
        return "----------------------\n"
                + "|oooooooooooooooooooo|\n"
                + "|oooooooooooooooooooo|\n"
                + "|oooooooooooooooooooo|\n"
                + "|oooooooooooooooooooo|\n"
                + "----------------------";
    }

    private String getLayoutWithBucketFillOutsideRectangle() {
        return "----------------------\n"
                + "|oooooooooooooooxxxxx|\n"
                + "|ooooooooooooooox   x|\n"
                + "|oooooooooooooooxxxxx|\n"
                + "|oooooooooooooooooooo|\n"
                + "----------------------";
    }

    private String getLayoutWithBucketFillInsideRectangle() {
        return "----------------------\n"
                + "|               xxxxx|\n"
                + "|               xooox|\n"
                + "|               xxxxx|\n"
                + "|                    |\n"
                + "----------------------";
    }
}