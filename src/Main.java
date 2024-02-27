import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {

    static char operation;
    static int result;
    static int firstNumber;
    static int secondNumber;

    public static void main(String[] args) {
        Scanner x = new Scanner(System.in);
        String input = x.nextLine();
        char[] forOperationArray = new char[10];
        for (int i = 0; i < input.length(); i++) {
            forOperationArray[i] = input.charAt(i);
            switch (forOperationArray[i]){
                case '+':
                    operation = '+';
                    break;
                case '-':
                    operation = '-';
                    break;
                case '*':
                    operation = '*';
                    break;
                case '/':
                    operation = '/';
                    break;
            }
        }
        String[] numbersArray = input.split("[+-/*]");
        if (numbersArray.length == 2){
            String numTrim1 = numbersArray[0].trim();
            String numTrim2 = numbersArray[1].trim();
            if (isParsable(numTrim1) && isParsable(numTrim2)) {
                firstNumber = Integer.parseInt(numTrim1);
                secondNumber = Integer.parseInt(numTrim2);
                if (firstNumber <= 10 && firstNumber > 0 && secondNumber <= 10 && secondNumber > 0) {
                    result = calc(firstNumber, secondNumber, operation);
                    System.out.println(result);
                }else {
                    System.err.println("Калькулятор должен принимать на вход числа от 1 до 10 включительно" +
                            " или от I до X");
                }
            } else if (isParsable(numTrim1) && !(isParsable(numTrim2)) || !(isParsable(numTrim1)) && isParsable(numTrim2)) {
                System.err.println("Используются одновременно разные системы счисления");
            } else {
                firstNumber = romanToNumber(numTrim1);
                secondNumber = romanToNumber(numTrim2);
                if (firstNumber < 0 && secondNumber < 0) {
                    System.err.println("Допустимы всего два значения от I до X! Первые и третьи элементы только " +
                            "могут быть операндами, второй элемент должен быть оператором!");
                } else if (firstNumber<secondNumber && operation=='-') {
                    System.err.println("В римской системе нет отрицательных чисел");
                } else {
                    result = calc(firstNumber, secondNumber, operation);
                    String resultRoman = convertNumToRoman(result);
                    System.out.println(resultRoman);
                }
            }
        }else {
            System.err.println("формат математической операции не удовлетворяет заданию - два операнда и один " +
                    "оператор (+, -, /, *)");
        }





    }

    public static boolean isParsable(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (final NumberFormatException e) {
            return false;
        }
    }


    private static int romanToNumber (String roman) {
        try {
            switch (roman){
                case "I":
                    return 1;
                case "II":
                    return 2;
                case "III":
                    return 3;
                case "IV":
                    return 4;
                case "V":
                    return 5;
                case "VI":
                    return 6;
                case "VII":
                    return 7;
                case "VIII":
                    return 8;
                case "IX":
                    return 9;
                case "X":
                    return 10;
            }
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Неверный формат данных");
        }
        return -1;
    }

    private static String convertNumToRoman (int numArabian) {
        String[] roman = {"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII",
                "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV",
                "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV",
                "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI",
                "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII",
                "LIX", "LX", "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };
        final String s = roman[numArabian];
        return s;
    }

    public static int calc (int num1, int num2, char op) {
        int result = 0;
        switch (op) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                try {
                    result = (int)(num1 / num2);
                } catch (ArithmeticException | InputMismatchException e) {
                    System.out.println("Exception : " + e);
                    System.out.println("Нулевые параметры запрещены для деления");
                    break;
                }
                break;
            default:
                throw new IllegalArgumentException("Не верный знак операции");
        }
        return result;
    }
}
