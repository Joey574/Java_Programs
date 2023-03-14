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
            for (int i = s; i > 0; i--) {
                starString(i);
                System.out.print("*");
            }
        }
    }


    public static void main(String[] args)
    {
        Scanner r = new Scanner(System.in);

        System.out.print("Enter input: ");
        int x = r.nextInt();
        starString(x);
    }
}
