import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileMain {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);

        System.out.println("Введите имя файла: ");
        String fileName = scan.next();

        Scanner fileScan = new Scanner(new File(fileName));
        List<Contact> mass = new ArrayList<>();
        while (fileScan.hasNextLine()) {
            mass.add(new Contact(fileScan.nextLine()));
        }

        System.out.println();

        System.out.println("Введите номер поля для сортировки: ");
        int fieldNum = scan.nextInt();
        mass.sort(Contact.getComparator(fieldNum));

        System.out.println();
        System.out.println("Массив после сортировки по параметру " + Contact.getSortByName(fieldNum) + ":");

        for (Contact contact: mass){
            System.out.println(contact);
        }

    }
}
