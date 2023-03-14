import java.util.Scanner;

public class GS11_04
{
/*
Author: Joey Soroka
Problem:
Purpose:
Pseudocode:
Maintenance Log:
 */


    static float sumTo(int n)
    {

        if (n == 1) {
            return 1;
        }
        float temp = n;
        return (1/temp) + sumTo(n - 1);
    }


    public static void main(String[] args)
    {
        float t = -1;

        Scanner r = new Scanner(System.in);

        System.out.print("Enter input: ");
        int x = r.nextInt();

        if (x >= 0) {
            t = sumTo(x);
        }
        else {
            throw new IllegalArgumentException("Value must be greater than 0");
        }

        System.out.print(t);
    }
}
