/*
*Толочко Александр, 7 группа
*
* 9. sin x / x = 1 - x^2/3! + x^4/5! - ...; где x < (-беск, +беск)
*
* Тесты:
*
* Введите число и желаемую точность: 2,333 8
  Собственный вывод: 0,311
  Вывод с помощью стандартных функций: 0,311
  *
  Введите число и желаемую точность: 707 3
  Собственный вывод: 24984279029246715000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000
  Вывод с помощью стандартных функций: -0
*  */



import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Scanner;


public class Lab1_regular {

    public static void main(String[] args) {
        System.out.print("Введите число и желаемую точность: ");
        Scanner scan = new Scanner(System.in);
        double x = scan.nextDouble();
        int k = scan.nextInt();
        double low_bound = Math.pow(10, -k);
        //System.out.println(low_bound);

        double sum = 0;
        if (x == 0)
            sum = Double.NaN;
        else {
            double nextAdd = 1;
            int curFactorial = 1;
            while (Math.abs(nextAdd) > low_bound) {
                sum += nextAdd;
                nextAdd = -nextAdd * x * x / (++curFactorial) / (++curFactorial);
            }
        }
        DecimalFormat format = new DecimalFormat("#.###");
        format.setRoundingMode(RoundingMode.CEILING);
        System.out.println("Собственный вывод: " + format.format(sum));
        System.out.println("Вывод с помощью стандартных функций: " + format.format(Math.sin(x)/x));
    }
}
