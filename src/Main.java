import java.util.Scanner;

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
        if (numbersArray.length == 1){
            throw new RuntimeException("Cтрока не является математической операцией");

        } else if (numbersArray.length != 2) {
            throw new RuntimeException("Формат математической операции не удовлетворяет заданию - два операнда и один " +
                    "оператор (+, -, /, *)");
        } else {
            String numTrim1 = numbersArray[0].trim();
            String numTrim2 = numbersArray[1].trim();
            if (isParsable(numTrim1) && isParsable(numTrim2)) {
                firstNumber = Integer.parseInt(numTrim1);
                secondNumber = Integer.parseInt(numTrim2);
                if (firstNumber <= 10 && firstNumber > 0 && secondNumber <= 10 && secondNumber >= 0) {
                    result = calc(firstNumber, secondNumber, operation);
                    System.out.println(result);
                }else {
                    throw new RuntimeException("Калькулятор должен принимать на вход числа от 1 до 10 " +
                            "включительно или от I до X");
                }
            } else if (isParsable(numTrim1) && !(isParsable(numTrim2)) || !(isParsable(numTrim1)) && isParsable(numTrim2)) {
                throw new RuntimeException("Используются одновременно разные системы счисления");
            } else {
                firstNumber = romanToNumber(numTrim1);
                secondNumber = romanToNumber(numTrim2);
                if (firstNumber < 0 || secondNumber < 0) {
                    throw new RuntimeException("Допустимы всего два значения от I до X! Первые и третьи элементы " +
                            "только могут быть операндами, второй элемент должен быть оператором!");
                } else if (firstNumber<secondNumber && operation=='-') {
                    throw new RuntimeException("В римской системе нет отрицательных чисел");
                } else {
                    result = calc(firstNumber, secondNumber, operation);
                    String resultRoman = convertNumToRoman(result);
                    System.out.println(resultRoman);
                }
            }
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
        return switch (roman) {
            case "I" -> 1;
            case "II" -> 2;
            case "III" -> 3;
            case "IV" -> 4;
            case "V" -> 5;
            case "VI" -> 6;
            case "VII" -> 7;
            case "VIII" -> 8;
            case "IX" -> 9;
            case "X" -> 10;
            default -> -1;
        };
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
        return roman[numArabian];
    }

    public static int calc (int num1, int num2, char op) {
        return switch (op) {
            case '+' -> num1 + num2;
            case '-' -> num1 - num2;
            case '*' -> num1 * num2;
            case '/' -> {
                if (num2 == 0) {
                    throw new RuntimeException("Нулевые параметры запрещены для деления");
                }
                yield num1 / num2;
            }
            default -> throw new IllegalArgumentException("Не верный знак операции");
        };
    }
}
