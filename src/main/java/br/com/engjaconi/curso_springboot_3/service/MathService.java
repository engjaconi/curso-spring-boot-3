package br.com.engjaconi.curso_springboot_3.service;

import br.com.engjaconi.curso_springboot_3.exception.UnsupportedMathOperationException;
import br.com.engjaconi.curso_springboot_3.util.MathOperationUtil;
import br.com.engjaconi.curso_springboot_3.util.converter.NumberConverter;
import org.springframework.stereotype.Service;

@Service
public class MathService {

    public Double sum(String numOne, String numTwo) throws UnsupportedMathOperationException {
        validatedNumber(numOne, numTwo);
        return MathOperationUtil.sum(numOne, numTwo);
    }

    public Double subtraction(String numOne, String numTwo) throws UnsupportedMathOperationException{
        validatedNumber(numOne, numTwo);
        return MathOperationUtil.subtraction(numOne, numTwo);
    }

    public Double multiplication(String numOne, String numTwo) throws UnsupportedMathOperationException{
        validatedNumber(numOne, numTwo);
        return MathOperationUtil.multiplication(numOne, numTwo);
    }

    public Double division(String numOne, String numTwo) throws UnsupportedMathOperationException{
        validatedNumber(numOne, numTwo);

        if(NumberConverter.convertToDouble(numTwo).equals(0D)) {
            throw new UnsupportedMathOperationException("O divisor deve ser diferente de zero.");
        }

        return MathOperationUtil.division(numOne, numTwo);
    }

    public Double mean(String numOne, String numTwo) throws UnsupportedMathOperationException{
        validatedNumber(numOne, numTwo);
        return MathOperationUtil.mean(numOne, numTwo);
    }

    public Double squareRoot(String number) throws UnsupportedMathOperationException{
        validatedNumber(number);
        return MathOperationUtil.squareRoot(number);
    }

    private void validatedNumber(String number) throws UnsupportedMathOperationException {
        if(!NumberConverter.isNumeric(number)) {
            throw new UnsupportedMathOperationException("Informe apenas um número.");
        }
    }

    private void validatedNumber(String numOne, String numTwo) throws UnsupportedMathOperationException {
        if(!NumberConverter.isNumeric(numOne) || !NumberConverter.isNumeric(numTwo)) {
            throw new UnsupportedMathOperationException("Verifique se foi informado apenas números.");
        }
    }
}
