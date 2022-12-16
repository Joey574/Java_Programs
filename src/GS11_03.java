import java.util.Scanner;

public class GS11_03
{


/*
Author: Joey Soroka
Problem:
Purpose:
Pseudocode:
Maintenance Log:
 */

    static void writeSequence(int n)
    {
        if (n > 0) {
            System.out.print((n/2) + " ");
            writeSequence(n - 1);
            System.out.print((n/2) + " ");
        }
    }

    public static void main(String[] args)
    {
        Scanner r = new Scanner(System.in);

        System.out.print("Enter input: ");
        int x = r.nextInt();

        if (x != 0) {
            writeSequence(x);
        } else {
            System.out.print("Illegal Argument");
        }

    }
}
