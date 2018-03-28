package com.springer.canvas.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class CommandsEnumTest {

    @Test
    public void testCreate() {
        CommandsEnum.getCommand("C 20 4");
        CommandsEnum.getCommand("c 20 4");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateInvalidLessNumberParameters() {
        CommandsEnum.getCommand("C 20");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateInvalidMoreNumberParameters() {
        CommandsEnum.getCommand("C 20 4 3");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateInvalidFirstParameter() {
        CommandsEnum.getCommand("C no 4");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateInvalidSecondParameter() {
        CommandsEnum.getCommand("C 20 no");
    }

    @Test
    public void testLine() {
        CommandsEnum.getCommand("L 1 2 6 2");
        CommandsEnum.getCommand("l 1 2 6 2");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLineInvalidLessNumberParameters() {
        CommandsEnum.getCommand("L 1 2 6");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLineInvalidMoreNumberParameters() {
        CommandsEnum.getCommand("L 1 2 6 2 3");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLineInvalidFirstParameter() {
        CommandsEnum.getCommand("L no 2 6 2");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLineInvalidSecondParameter() {
        CommandsEnum.getCommand("L 1 no 6 2");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLineInvalidThirdParameter() {
        CommandsEnum.getCommand("L 1 2 no 2");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLineInvalidFourthParameter() {
        CommandsEnum.getCommand("L 1 2 6 no");
    }

    @Test
    public void testRectangle() {
        CommandsEnum.getCommand("R 16 1 20 3");
        CommandsEnum.getCommand("r 16 1 20 3");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRectangleInvalidLessNumberParameters() {
        CommandsEnum.getCommand("R 16 1 20");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRectangleInvalidMoreNumberParameters() {
        CommandsEnum.getCommand("R 16 1 20 3 5");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLRectangleInvalidFirstParameter() {
        CommandsEnum.getCommand("R no 1 20 3");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRectangleInvalidSecondParameter() {
        CommandsEnum.getCommand("R 16 no 20 3");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRectangleInvalidThirdParameter() {
        CommandsEnum.getCommand("R 16 1 no 3");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRectangleInvalidFourthParameter() {
        CommandsEnum.getCommand("R 16 1 20 no");
    }

    @Test
    public void testBucketFill() {
        CommandsEnum.getCommand("B 10 3 O");
        CommandsEnum.getCommand("b 10 3 o");
        CommandsEnum.getCommand("B 10 3 9");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBucketFillInvalidLessNumberParameters() {
        CommandsEnum.getCommand("B 10 3");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBucketFillInvalidMoreNumberParameters() {
        CommandsEnum.getCommand("B 10 3 o x");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBucketFillInvalidFirstParameter() {
        CommandsEnum.getCommand("B no 3 O");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBucketFillInvalidSecondParameter() {
        CommandsEnum.getCommand("B 10 no O");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBucketFillInvalidThirdParameter() {
        CommandsEnum.getCommand("B 10 3 *");
    }

    @Test
    public void testQuit() {
        CommandsEnum.getCommand("Q");
        CommandsEnum.getCommand("q");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQuitInvalidParameters() {
        CommandsEnum.getCommand("Q 3");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidCommand() {
        CommandsEnum.getCommand("I");
    }
}