import java.util.Scanner;

public class GS11_02
{

/*
Author: Joey Soroka
Problem:
Purpose:
Pseudocode:
Maintenance Log:
 */

    static void writeNums(int i)
    {
        if (i == 1) {
            System.out.print("1, ");
        } else {
            writeNums(i - 1);
            System.out.print(i + ", ");
        }
    }

    public static void main(String[] args)
    {
        Scanner r = new Scanner(System.in);

        System.out.print("Enter input: ");
        int x = r.nextInt();
        writeNums(x);
    }
}
