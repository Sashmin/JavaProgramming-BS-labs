/*
*Толочко Александр, 7 группа
*
* 9. Найти строку заданной матрицы, в которой длина максимальной серии
* (последовательности одинаковых элементов) минимальна.
*
* Тесты:
*
*   Введите число строк и длину каждой строки,
    затем по очереди каждую строку: 3 3 4 5
    Строка 1:
    3 2 2
    Строка 2:
    3 4 4 4
    Строка 3:
    2 2 2 6  9
    Полученная матрица:
    3 2 2
    3 4 4 4
    2 2 2 6 9

Номер строки с наименьшей максимальной последовательностью: 1
*
*
*  */

import java.util.Scanner;

public class AppClass {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Введите число строк и длину каждой строки,\nзатем по очереди каждую строку: ");

        int rowCount = scan.nextInt();
        int[][] matrix = new int[rowCount][];
        for (int i = 0; i < rowCount; i++)
            matrix[i] = new int[scan.nextInt()];

        for (int i = 0; i < rowCount; i++){
            System.out.println("Строка " + (i + 1) + ": ");
            for (int k = 0; k < matrix[i].length; k++) {
                matrix[i][k] = scan.nextInt();
            }
        }

        System.out.println("Полученная матрица: ");
        for (int i = 0; i < rowCount; i++){
            for (int k = 0; k < matrix[i].length; k++) {
                System.out.print(matrix[i][k] + " ");
            }
            System.out.println("");
        }

        System.out.println("");


        int maxSeq, curSeq, minMax = Integer.MAX_VALUE, minMaxRow = 0;
        for (int i = 0; i < rowCount; i++){
            maxSeq = curSeq = 1;

            for (int k = 0; k < matrix[i].length; k++) {
                if (k != matrix[i].length - 1 && matrix[i][k] == matrix[i][k+1])
                    curSeq++;
                else {
                    if (curSeq > maxSeq)
                        maxSeq = curSeq;
                    curSeq = 1;
                }
            }

            if (maxSeq < minMax) {
                minMax = maxSeq;
                minMaxRow = i + 1;
            }
        }

        System.out.println("Номер строки с наименьшей максимальной последовательностью: " + minMaxRow);
    }
}
