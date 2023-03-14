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

    static void writeSequence(int n) throws Exception {
        if (n <= 0) {
            throw new Exception("Illegal Argument");
        }

        if (n == 1) {
            System.out.print("1 ");
        } else if (n == 2) {
            System.out.print("1 1 ");
        } else {
            if (n % 2 == 0) {
                System.out.print(n/2 + " ");
            }
            else {
                System.out.print((n+1)/2 + " ");
            }
            writeSequence(n - 2);
            if (n % 2 == 0) {
                System.out.print(n/2 + " ");
            }
            else {
                System.out.print((n+1)/2 + " ");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner r = new Scanner(System.in);

        System.out.print("Enter input: ");
        int x = r.nextInt();

        if (x != 0) {
            for(int i = 1; i < 11; i++) {
                writeSequence(i);
                System.out.print("\n");
            }
        }

    }
}
