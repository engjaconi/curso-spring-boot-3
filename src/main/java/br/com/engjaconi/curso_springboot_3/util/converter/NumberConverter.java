package br.com.engjaconi.curso_springboot_3.util.converter;

public final class NumberConverter {

    private NumberConverter() { }

    public static Double convertToDouble(String strNumber) {
        if(!isNumeric(strNumber)) {
            return 0D;
        }

        String number = strNumber.replace(",", ".");

        return Double.parseDouble(number);
    }

    public static boolean isNumeric(String strNumber) {
        if(strNumber == null) {
            return false;
        }

        String number = strNumber.replace(",", ".");

        return number.matches("[-+]?\\d*\\.?\\d+");
    }
}
