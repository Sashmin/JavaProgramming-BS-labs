import java.util.Comparator;

public class SortByFirst implements Comparator<Tokens> {

    @Override
    public int compare(Tokens a, Tokens b) {
        return a.getLexems().get(0).compareTo(b.getLexems().get(0));
    }
}
