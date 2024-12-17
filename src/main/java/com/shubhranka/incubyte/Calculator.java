package com.shubhranka.incubyte;

public class Calculator {
    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }
        
        String[] numbersArray = splitNumbersByComma(numbers);
        return sum(numbersArray);
    }

    private String[] splitNumbersByComma(String numbers) {
        return numbers.split(",");
    }

    private int sum(String[] numbersArray) {
        int sum = 0;
        for (String number : numbersArray) {
            sum += Integer.parseInt(number);
        }
        return sum;
    }
}