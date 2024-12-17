package com.shubhranka.incubyte;

public class Calculator {
    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }
        
        String[] numbersArray = splitNumbersByCommaOrNewLine(numbers);
        return sum(numbersArray);
    }

    private String[] splitNumbersByCommaOrNewLine(String numbers) {
        return numbers.split(",|\n");
    }

    private int sum(String[] numbersArray) {
        int sum = 0;
        for (String number : numbersArray) {
            sum += Integer.parseInt(number);
        }
        return sum;
    }
}