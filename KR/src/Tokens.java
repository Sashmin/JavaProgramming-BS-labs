import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.*;

public class Tokens {
    private String initialStr;
    private List<String> lexems;

    public Tokens(String sentence) {
        initialStr = sentence;

        lexems = new ArrayList<>();
        String[] parts = sentence.split("[;_-]+");
        lexems.addAll(Arrays.asList(parts));
    }

    @Override
    public String toString() {
        String result = "";
        for (String str: lexems) {
            result += str + " ";
        }
        return result;
    }

    public String getInitialStr() {
        return initialStr;
    }

    public List<String> getLexems() {
        return lexems;
    }

    public String AllBigInt() {
        String result = "";
        for (String tok: lexems) {
            if (tok.matches("-?\\d+")) {
                BigInteger big = new BigInteger(tok);
                if (big.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) > 0) {
                    result += tok + "\n";
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Tokens tok = new Tokens("abs;;34e-_55555555555555555555555--3333333333333333333333-gg-");
        System.out.println(tok.AllBigInt());
    }
}


