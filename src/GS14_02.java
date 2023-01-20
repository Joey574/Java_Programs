import java.util.Scanner;

public class GS14_02
{

/*
Author: Joey Soroka
Problem:
Purpose:
Pseudocode:
Maintenance Log:
 */


    public static boolean isReverse(String in1, String in2) {
        boolean out = true;

        String temp1 = in1.substring(1);
        String temp2 = in2.substring(0, in2.length() - 1);

        System.out.println("Substrings: " + temp1 + ", " + temp2);

        if (in1.length() != in2.length()) {
            out = false;
        } else if (in1.length() > 1 && in2.length() > 1) {
            out = isReverse(temp1, temp2);
        } else if (!in1.equalsIgnoreCase(in2)) {
            out = false;
        }
        return out;
    }

    public static void main(String[] args)
    {
        Scanner r = new Scanner(System.in);

        System.out.print("Enter string one: ");
        String s1 = r.nextLine();
        System.out.print("Enter string two: ");
        String s2 = r.nextLine();

        System.out.println("Input:" + s1 + ", " + s2);

        boolean t = isReverse(s1, s2);

        System.out.println("Output: " + t);

    }
}
