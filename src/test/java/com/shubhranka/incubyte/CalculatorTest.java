package com.shubhranka.incubyte;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class CalculatorTest {
    final private Calculator calculator = new Calculator();
    @Test 
    public void shouldReturnZeroOnEmptyString() {
        assertEquals(0, calculator.add(""));
    }

    @Test 
    public void shouldReturnSumIfSingleNumberIsProvided() {
        assertEquals(1, calculator.add("1"));
        assertEquals(2, calculator.add("2"));
        assertEquals(3, calculator.add("3"));
    }

    @Test
    public void shouldReturnSumIfNumbersAreProvidedDelimitedByComma() {
        assertEquals(3, calculator.add("1,2"));
        assertEquals(5, calculator.add("2,3"));
        assertEquals(6, calculator.add("1,2,3"));
        assertEquals(10, calculator.add("1,2,3,4"));
    }

    @Test
    public void shouldReturnSumIfNumbersAreProvidedDelimitedByNewLineAndComma() {
        assertEquals(3, calculator.add("1\n2"));
        assertEquals(6, calculator.add("1\n2\n3"));
        assertEquals(10, calculator.add("1,2\n3,4"));
        assertEquals(15, calculator.add("1\n2,3\n4,5"));
        assertEquals(1, calculator.add("1\n"));  
        assertEquals(1, calculator.add("1,"));
    }

    @Test
    public void shouldReturnSumIfCustomDelimiterIsProvided() {
        assertEquals(3, calculator.add("//;\n1;2"));
        assertEquals(6, calculator.add("//:\n1:2:3"));
        assertEquals(10, calculator.add("//t\n1t2t3t4"));
        assertEquals(10, calculator.add("//***\n1***2***3***4"));
    }

    @Test
    public void shouldThrowExceptionIfNegativeNumbersAreProvided() {
        assertThrows(IllegalArgumentException.class, () -> calculator.add("-1,2"));
        assertThrows(IllegalArgumentException.class, () -> calculator.add("-1,-2,-3"));
        assertThrows(IllegalArgumentException.class, () -> calculator.add("-1,-2,-3,-4"));
        assertDoesNotThrow(() -> calculator.add("1,2,3,4"));
    }

    @Test
    public void shouldReturnSumIfNumbersAreGreaterThan1000AreIgnored() {
        assertEquals(2, calculator.add("1001,2"));
        assertEquals(5, calculator.add("1001,2,3"));
        assertEquals(9, calculator.add("1001,2,3,4"));
    }

    @Test
    public void shouldReturnSumIfNumbersAreProvidedWithCustomDelimiterOfAnyLength() {
        assertEquals(6, calculator.add("//[***]\n1***2***3"));
        assertEquals(10, calculator.add("//[***]\n1***2***3***4"));
        assertEquals(15, calculator.add("//[ttt]\n1ttt2ttt3ttt4ttt5"));
        assertEquals(21, calculator.add("//[xyz]\n1xyz2xyz3xyz4xyz5xyz6"));
    }

    @Test
    public void shouldReturnSumIfNumbersAreProvidedWithMultipleCustomDelimitersOfAnyLength() {
        assertEquals(6, calculator.add("//[*][%]\n1*2%3"));
        assertEquals(15, calculator.add("//[*][%]\n1*2%3*4%5"));
        assertEquals(21, calculator.add("//[*][%][xyz]\n1*2%3xyz4%5xyz6"));
        assertEquals(15, calculator.add("//[**][%%]\n1**2%%3**4%%5"));
    }
}
