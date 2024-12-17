package com.shubhranka.incubyte;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        String[] numbersArray;
        
        if (numbers.startsWith("//")) {
            numbersArray = splitByCustomDelimiter(numbers);
        }else{
            numbersArray = splitNumbersByCommaOrNewLine(numbers);
        }

        if (containsNegativeNumbers(numbersArray)) {
            String negativeNumbers = getNegativeNumbers(numbersArray);
            throw new IllegalArgumentException("Negative numbers are not allowed: " + negativeNumbers);
        }
        return sum(numbersArray);
    }

    private String[] splitByCustomDelimiter(String numbers) {
        String delimiterRegex = "//(.)\n(.*)";
        Pattern pattern = Pattern.compile(delimiterRegex);
        Matcher matcher = pattern.matcher(numbers);
        if (matcher.find()) {
            String delimiter = matcher.group(1);
            String numbersWithoutDelimiter = matcher.group(2);
            return numbersWithoutDelimiter.split(delimiter);
        }
        return new String[0];
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

    private boolean containsNegativeNumbers(String[] numbersArray) {
        for (String number : numbersArray) {
            if (Integer.parseInt(number) < 0) {
                return true;
            }
        }
        return false;
    }

    private String getNegativeNumbers(String[] numbersArray) {
        String negativeNumbers = "";
        for (String number : numbersArray) {
            if (Integer.parseInt(number) < 0) {
                negativeNumbers += number + " ";
            }
        }
        return negativeNumbers;
    }
}