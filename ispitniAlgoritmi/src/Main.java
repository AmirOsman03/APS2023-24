import given.DLL;
import given.DLLNode;
import given.SLL;
import given.SLLNode;

import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static <E> void main(String[] args) {
//
//
        DLL<Character> list = new DLL<>();
        list.insertLast('a');
        list.insertLast('b');
        list.insertLast('c');
        list.insertLast('d');
        list.insertLast('e');
        list.insertLast('f');
        list.insertLast('g');
        list.insertLast('h');
        list.insertLast('i');
        list.insertLast('j');

        System.out.print("Input: list = [ " + list + " ], " );
        list.podeliSamoglaski(list);

    }
}