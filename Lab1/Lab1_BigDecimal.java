/*
*Толочко Александр, 7 группа
*
* 9. sin x / x = 1 - x^2/3! + x^4/5! - ...; где x < (-беск, +беск)
*
* Тесты:
*
* Введите число и желаемую точность: 2000 4
  Собственный вывод: 0.000
  Вывод с помощью стандартных функций: 0,001
  *
  Введите число и желаемую точность: 100000 3
  Собственный вывод: -0.081
  Вывод с помощью стандартных функций: 0
*  */



import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Scanner;


public class Lab1_BigDecimal {

    public static void main(String[] args) {
        System.out.print("Введите число и желаемую точность: ");
        Scanner scan = new Scanner(System.in);
        BigDecimal x = scan.nextBigDecimal();
        int k = scan.nextInt();
        BigDecimal low_bound = BigDecimal.valueOf(Math.pow(10, -k));
        //System.out.println(low_bound);

        BigDecimal sum = BigDecimal.ZERO;
        if (!x.equals(BigDecimal.ZERO)) {
            BigDecimal nextAdd = new BigDecimal("1");

            int curFactorial = 1;
            while (nextAdd.abs().compareTo(low_bound) > 0) {
                sum = sum.add(nextAdd);
                //nextAdd = -nextAdd * x * x / (++curFactorial) / (++curFactorial);
                nextAdd = nextAdd.multiply(BigDecimal.valueOf(-1)).multiply(x).multiply(x);
                nextAdd = nextAdd.divide(BigDecimal.valueOf(++curFactorial), 3, RoundingMode.HALF_UP);
                nextAdd = nextAdd.divide(BigDecimal.valueOf(++curFactorial), 3, RoundingMode.HALF_UP);
                //System.out.println("cur: " + curFactorial + "; add: " + nextAdd + "; sum: " + sum);
            }
            DecimalFormat format = new DecimalFormat("#.###");
            format.setRoundingMode(RoundingMode.CEILING);
            System.out.println("Собственный вывод: " + sum);
            System.out.println("Вывод с помощью стандартных функций: " +
                    format.format(Math.sin(x.doubleValue()) / x.doubleValue()));

        }
    }
}
