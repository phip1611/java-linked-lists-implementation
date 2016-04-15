package de.phip1611.linked_lists;

/**
 * This is part of my Lists-Implementation.
 *
 * @author Philipp Schuster | https://phip1611.de | @phip1611
 */
public class Main {
    public static void main(String[] args) {
        SimpleList<String> mySimpleList = new SimpleList<>();
        mySimpleList.append("Hallo");
        mySimpleList.append("wie");
        mySimpleList.append("geht");
        mySimpleList.append("es");
        mySimpleList.append("dir.");
        mySimpleList.printList();
        for (String a : mySimpleList) {
            System.out.println(a.toString());
        }
        mySimpleList.clear();

        mySimpleList.printList();
        for (String a : mySimpleList) {
            System.out.println(a.toString());
        }
}
}
