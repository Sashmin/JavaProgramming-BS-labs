import java.util.*;

public class Class23 {

    public static boolean isLocalMax(ArrayList<ArrayList<Integer>> matrix, int h, int w)
    {
        return (matrix.get(h).get(w) > matrix.get(h+1).get(w)
                && matrix.get(h).get(w) > matrix.get(h-1).get(w)
                && matrix.get(h).get(w) > matrix.get(h).get(w+1)
                && matrix.get(h).get(w) > matrix.get(h).get(w-1));
    }

    public static String matrixString(ArrayList<ArrayList<Integer>> matrix)
    {
        String str = new String();
        for (var row: matrix){
            for (var element: row){
                str = str.concat(element + " ");
            }
            str = str.concat("\n");
        }
        return str;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Введите размеры матрицы через пробел: ");
        int height = scan.nextInt();
        int width = scan.nextInt();
        Random rand = new Random();
        ArrayList<ArrayList<Integer>> matrix= new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> tempArray;
        for (int i = 0; i < height; i++){
            tempArray = new ArrayList<Integer>();

            for (int j = 0; j < width; j++)
                tempArray.add(rand.nextInt(100));

            matrix.add(tempArray);
        }

        ArrayList<ArrayList<Integer>> modifiedMatrix = new ArrayList<ArrayList<Integer>>();

        tempArray = new ArrayList<Integer>();
        for (int i = 0; i < width + 1; i++)
            tempArray.add(-1);
        modifiedMatrix.add(tempArray);

        for (int i = 0; i < height; i++){
            tempArray = new ArrayList<Integer>();

            tempArray.add(-1);
            for (int j = 0; j < width; j++)
                tempArray.add(matrix.get(i).get(j));
            tempArray.add(-1);

            modifiedMatrix.add(tempArray);
        }

        tempArray = new ArrayList<Integer>();
        for (int i = 0; i < width + 1; i++)
            tempArray.add(-1);
        modifiedMatrix.add(tempArray);

        Vector<Integer> vec = new Vector<Integer>();
        for (int i = 1; i < height + 1; i++){
            for (int j = 1; j < width + 1; j++){
                if (isLocalMax(modifiedMatrix, i, j))
                    vec.add(modifiedMatrix.get(i).get(j));
            }
        }
        System.out.println("Исходная матрица: \n" + matrixString(matrix));
        System.out.println("Все локальные максимумы: " + vec.toString());
        System.out.println("Минимальный локальный максимум: " + Collections.min(vec));
    }
}
