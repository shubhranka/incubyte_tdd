package com.shubhranka.incubyte;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class CalculatorTest {
    final private Calculator calculator = new Calculator();
    @Test 
    public void shouldReturnZeroOnEmptyString() {
        assertEquals(0, calculator.add(""));
    }
}
