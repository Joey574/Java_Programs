import java.util.Scanner;

public class GS11_01 {

/*
Author: Joey Soroka
Problem:
Purpose:
Pseudocode:
Maintenance Log:
 */

    static void starString(int s)
    {
        if (s > 0) {
            System.out.print("*");
            starString(s - 1);
        }
    }


    public static void main(String[] args)
    {
        Scanner r = new Scanner(System.in);

        System.out.print("Enter input: ");
        int x = r.nextInt();
        starString((int) Math.pow(2, x));
    }
}
