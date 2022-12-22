/*
*Толочко Александр, 7 группа
*
* 9.	Задан текстовый файл input.txt. Требуется определить строки этого файла,
* содержащие максимальную по длине подстроку, состоящую только из знаков препинания.
* Перечень символов, подходящих под определение “знаки препинания”, задает пользователь.
*
* Тесты:
*
 input.txt
 *  hello w,.)-)=rld
    h,,. fg.,)
    pr,,,iv,,..,,
    hellll)-=
*
* Введите набор символов, которые будут считаться знаками препинания:
  ,.)-=
  [6, 3, 6, 3]
  Номера строк с наибольшей подстрокой из знаков препинания: 1 3
*  */



import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Class9_2 {
    static int findMaxSubstr(String str, String regexStr)
    {
        Pattern regex = Pattern.compile("[" + regexStr + "]+");
        int result = 0;
        Matcher matcher = regex.matcher(str);
        while (matcher.find()){
            if (matcher.group().length() > result)
                result = matcher.group().length();
        }
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Введите набор символов, которые будут считаться знаками препинания:");
        Scanner regexScan = new Scanner(System.in);
        String regex = regexScan.nextLine();
        Scanner fileScan = new Scanner(new File("D:\\JavaProjects\\Lab3\\src\\input.txt"));
        ArrayList<Integer> maxSubstr = new ArrayList<Integer>();
        int maxSubseq = 0;
        while (fileScan.hasNextLine()){
            int subseq = findMaxSubstr(fileScan.nextLine(), regex);
            maxSubstr.add(subseq);
            if (subseq > maxSubseq)
                maxSubseq = subseq;
        }
        System.out.println(maxSubstr.toString());
        System.out.print("Номера строк с наибольшей подстрокой из знаков препинания: ");
        for (int i = 0; i < maxSubstr.size(); i++){
            if (maxSubstr.get(i) == maxSubseq)
                System.out.print((i + 1) + " ");
        }
    }
}
