package br.com.engjaconi.curso_springboot_3.util;

import br.com.engjaconi.curso_springboot_3.util.converter.NumberConverter;

public final class MathOperationUtil {
    private MathOperationUtil() {}

    public static Double sum(String numOne, String numTwo) {
        return NumberConverter.convertToDouble(numOne) + NumberConverter.convertToDouble(numTwo);
    }

    public static Double subtraction(String numOne, String numTwo) {
        return NumberConverter.convertToDouble(numOne) - NumberConverter.convertToDouble(numTwo);
    }

    public static Double multiplication(String numOne, String numTwo) {
        return NumberConverter.convertToDouble(numOne) * NumberConverter.convertToDouble(numTwo);
    }

    public static Double division(String numOne, String numTwo) {
        return NumberConverter.convertToDouble(numOne) / NumberConverter.convertToDouble(numTwo);
    }

    public static Double mean(String numOne, String numTwo) {
        return sum(numOne, numTwo) / 2;
    }

    public static Double squareRoot(String number) {
        return Math.sqrt(NumberConverter.convertToDouble(number));
    }
}
