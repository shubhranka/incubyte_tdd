package com.shubhranka.incubyte;

import java.util.ArrayList;
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
        String delimiterRegex = "//(.+)\n(.*)";
        Pattern pattern = Pattern.compile(delimiterRegex);
        Matcher matcher = pattern.matcher(numbers);
        
        if (matcher.find()) {
            if (containsMultipleDelimiters(matcher.group(1))) {
                return splitByMultipleCustomDelimiters(matcher.group(1), matcher.group(2));
            } else {
                return splitByCustomDelimiter(matcher.group(1), matcher.group(2));
            }
        }
        return new String[0];
    }

    private boolean containsMultipleDelimiters(String str) {
        return str.contains("[") && str.contains("]");
    }

    private String[] splitByMultipleCustomDelimiters(String delimiter, String numbers) {
        Pattern delimiterPattern = Pattern.compile("\\[(.+?)\\]");
        Matcher delimiterMatcher = delimiterPattern.matcher(delimiter);
        ArrayList<String> delimiters = new ArrayList<>();
        while (delimiterMatcher.find()) {
            delimiters.add(Pattern.quote(delimiterMatcher.group(1)));
        }
        String splitRegex = String.join("|", delimiters);
        return numbers.split(splitRegex);
    }
    
    private String[] splitByCustomDelimiter(String delimiter, String numbers) {
        String quotedDelimiter = Pattern.quote(delimiter);
        return numbers.split(quotedDelimiter);
    }

    private String[] splitNumbersByCommaOrNewLine(String numbers) {
        return numbers.split(",|\n");
    }

    private int sum(String[] numbersArray) {
        int sum = 0;
        for (String number : numbersArray) {
            if (Integer.parseInt(number) > 1000) {
                continue;
            }
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