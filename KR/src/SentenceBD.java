import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceBD {
    private ArrayList<Tokens> SentenceList;

    public SentenceBD() {
        SentenceList = new ArrayList<>();
    }

    public SentenceBD(SentenceBD other) {
        SentenceList = new ArrayList<>(other.SentenceList);
    }

    public void inputFromFile(String fileName) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(fileName)).useDelimiter("[?!]+");
        while (scan.hasNext()) {
            SentenceList.add(new Tokens(scan.next()));
        }
    }

    @Override
    public String toString() {
        String result = "";
        for (Tokens tok: SentenceList) {
            result += tok + "\n";
        }

        return result;
    }

    public void mapOutput(String fileName) throws IOException{
        FileWriter writer = new FileWriter(fileName);
        HashMap<String, List<String>> map = new HashMap<>();

        for (Tokens tok: SentenceList) {
            map.put(tok.getLexems().get(0), tok.getLexems());
        }

        for (Map.Entry<String, List<String>> entry: map.entrySet()) {
            writer.write("Key: " + entry.getKey() + ", " + entry.getValue().toString() + "\n");
        }
        writer.close();
    }

    public void outputBigInt(String fileName) throws IOException {
        FileWriter writer = new FileWriter(fileName);

        for (Tokens tok: SentenceList) {
            writer.write(tok.AllBigInt());
        }
        writer.close();
    }

    public void outputSorted(String fileName) throws IOException{
        FileWriter writer = new FileWriter(fileName);
        SentenceBD newBD = new SentenceBD(this);

        newBD.SentenceList.sort(new SortByFirst());

        writer.write(newBD.toString());
        writer.close();
    }

    public void removeBetweenNumbers(String fileName) throws IOException {
        FileWriter writer = new FileWriter(fileName);
        String init = "";
        for (Tokens tok: SentenceList) {
            init += tok.getInitialStr();
        }
        Pattern regex = Pattern.compile("((?<=\\D|^)-?\\d++(\\.\\d++)?).+?\\D(\\1)(?=\\D|$)");
        Matcher matcher = regex.matcher(init);

        writer.write(matcher.replaceAll("$3$3"));
        writer.close();
    }

    public void writeToXml(String xmlName) throws IOException{
        FileWriter writer = new FileWriter(xmlName);
        writer.write("<sentences>\n");
        for (Tokens tok: SentenceList) {
            writer.write("\t<sentence>\n");
            for (String lex: tok.getLexems()) {
                writer.write("\t\t<token>" + lex + "</token>\n");
            }
            writer.write("\t</sentence>\n");
        }
        writer.write("</sentences>");
        writer.close();
    }

    public static void main(String[] args) throws IOException {
        SentenceBD bd = new SentenceBD();
        bd.inputFromFile("input.txt");
        System.out.println(bd);
        bd.outputBigInt("rezult1.txt");
        bd.mapOutput("rezult2.txt");
        bd.outputSorted("rezult3.txt");
        bd.removeBetweenNumbers("rezult4.txt");
        bd.writeToXml("result5.xml");
    }
}
