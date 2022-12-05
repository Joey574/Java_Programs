import java.util.Scanner;

public class GS10_02 {

/*
Author: Joey Soroka
Problem:
Purpose:
Pseudocode:
Maintenance Log:
 */

    static void writeSquares(int n)
    {
        if (n > 0) {
            if (n % 2 != 0) {
                System.out.print((n * n) + ", ");
            }
            writeSquares(n - 1);
            if (n % 2 == 0) {
                System.out.print((n * n) + ", ");
            }
        }

    }

    public static void main(String[] args)
    {
        Scanner r = new Scanner(System.in);

        System.out.print("Enter number to square: ");
        int temp = r.nextInt();

        writeSquares(temp);
    }
}
