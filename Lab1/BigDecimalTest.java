import java.math.BigDecimal;
import java.util.Scanner;

public class BigDecimalTest {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        BigDecimal dec = scan.nextBigDecimal();
        System.out.println(dec);
    }
}
