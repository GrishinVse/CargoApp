package coursework_ui.utils;

import java.util.Arrays;

public class UtilsClass {
    public static boolean isDigit(String string) throws NumberFormatException {
        try {
            System.out.println("1 " + string);
            String str = string.replace(".", ",");
            System.out.println("2 " + str);
            Double.parseDouble(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isPlateFormat(String string){
        final String[] chars = new String[]{"А", "В", "Е", "К", "М", "Н", "О", "Р", "С", "Т", "У", "Х", };

        String firstLetter = string.substring(0, 1);
        String numPart_1 = string.substring(1, 4);
        String secondLetter = string.substring(4, 5);
        String thirdLetter = string.substring(5, 6);
        String numPart_2 = string.substring(6);

        System.out.println(firstLetter + numPart_1 + secondLetter + thirdLetter + numPart_2);

        if (Arrays.asList(chars).contains(firstLetter) & Arrays.asList(chars).contains(secondLetter) & Arrays.asList(chars).contains(thirdLetter)){
            boolean res = true;
            for (int i = 0; i < numPart_1.length(); i++){
                System.out.println(numPart_1.substring(i, i+1));
                res = res & UtilsClass.isDigit(numPart_1.substring(i, i+1));
            }

            for (int i = 0; i < numPart_2.length(); i++){
                System.out.println(numPart_2.substring(i, i+1));
                res = res & UtilsClass.isDigit(numPart_2.substring(i, i+1));
            }

            return res;
        }

        return false;
    }
}
