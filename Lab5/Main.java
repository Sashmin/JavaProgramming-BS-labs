import java.util.*;

public class Main {

    public static String inputContact()
    {
        Scanner scan = new Scanner(System.in);
        String contactStr = new String();

        System.out.print("  Имя: ");
        contactStr += scan.nextLine() + "|";
        System.out.print("  Моб. тел.: ");
        contactStr += scan.nextLine() + "|";
        System.out.print("  Раб. тел.: ");
        contactStr += scan.nextLine() + "|";
        System.out.print("  Дом. тел.: ");
        contactStr += scan.nextLine() + "|";
        System.out.print("  Почта: ");
        contactStr += scan.nextLine() + "|";
        System.out.print("  Веб-страница: ");
        contactStr += scan.nextLine() + "|";
        System.out.print("  Адрес: ");
        contactStr += scan.nextLine();

        return contactStr;
    }

    public static void main(String[] args) throws Exception{
        Scanner scan = new Scanner(System.in);
        int size = 0;

        System.out.print("Введите число контактов в массиве: ");
        size = scan.nextInt();
        scan.nextLine();

        List<Contact> mass = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            System.out.print("Контакт " + (i+1) + ": \n");
            String temp = inputContact();
            mass.add(new Contact(temp));
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
