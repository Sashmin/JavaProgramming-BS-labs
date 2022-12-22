/*
*Толочко Александр, 7 группа
*
* 9.	Дана строка, слова в которой состоят из букв латинского алфавита и десятичных цифр.
*  Остальные символы считаются разделителями между словами. Получить новую строку,
* выполняя в заданной строке замены по следующему правилу. Все слова, состоящие только из букв,
*  записываются строчными буквами. Слова в исходной строке разделяются
*  некоторым множеством разделителей. Слова в новой строке должны разделяться ровно одним пробелом.
*
* Тесты:
*
     Введите строку:
     HeLLo*4WOrld   777*(*&^%&*))(hello
     hello 4WOrld 777 hello
*
*
*  */

import java.util.Arrays;
import java.util.Scanner;

public class Class9_1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите строку: ");
        String str = scan.nextLine();
        String[] tokens = str.split("[^a-zA-Z0-9]+");
        StringBuilder newStr = new StringBuilder(new String());
        for (String token: tokens){
            if (token.matches("[a-zA-Z]+"))
                token = token.toLowerCase();
            newStr.append(token + " ");
        }
        System.out.println(newStr);
    }
}
