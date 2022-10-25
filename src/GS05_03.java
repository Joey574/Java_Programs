import java.util.Scanner;

public class GS05_03
{
/*
Author: Joey Soroka
Problem:
Purpose:
Pseudocode:
Maintenance Log:
 */

    static byte secretNumber [] = new byte[4];

    static byte guessNumber [] = new byte[4];

    static boolean checked [] = new boolean[4];

    static byte bP = 0; // correct digit, right location
    static byte wP = 0; // correct digit, wrong location

    public static void guessHandler()
    {
        Scanner r = new Scanner(System.in);

        System.out.print("Enter ones place guess: ");
        guessNumber[3] = r.nextByte();
        System.out.print("Enter tens place guess: ");
        guessNumber[2] = r.nextByte();
        System.out.print("Enter hundreds place guess: ");
        guessNumber[1] = r.nextByte();
        System.out.print("Enter thousands place guess: ");
        guessNumber[0] = r.nextByte();

        System.out.print("Guess: ");

        for (int i = 0; i < guessNumber.length; i++)
        {
            System.out.print(guessNumber[i]);
        }
        System.out.print("\n\n");

    }

    public static void checkGuess()
    {
        bP = 0;
        wP = 0;

        checked[0] = false;
        checked[1] = false;
        checked[2] = false;
        checked[3] = false;

        for (int i = 0; i < guessNumber.length; i++)
        {
            if (guessNumber[i] == secretNumber[i])
            {
                bP++;
                checked[i] = true;
            }
        }

        for (int i = 0; i < guessNumber.length; i++)
        {
            for (int q = 0; q < secretNumber.length; q++)
            {

            }
        }

    }

    public static void main(String[] args)
    {
        Scanner r = new Scanner(System.in);

        int input;

        boolean win = false;

        System.out.print("Menu:\n1: Enter secret #\n2: Randomize secret #\nInput: ");
        input = r.nextInt();

        if (input == 1)
        {
            System.out.print("Enter ones place: ");
            secretNumber[3] = r.nextByte();
            System.out.print("Enter tens place: ");
            secretNumber[2] = r.nextByte();
            System.out.print("Enter hundreds place: ");
            secretNumber[1] = r.nextByte();
            System.out.print("Enter thousands place: ");
            secretNumber[0] = r.nextByte();
        }
        else if (input == 2)
        {
            secretNumber[0] += (int) ((java.lang.Math.random() - 0.1) * 10); // thousands place
            secretNumber[1] += (int) ((java.lang.Math.random() - 0.1) * 10); // hundreds place
            secretNumber[2] += (int) ((java.lang.Math.random() - 0.1) * 10); // tens place
            secretNumber[3] += (int) ((java.lang.Math.random() - 0.1) * 10); // ones place
        }

        while (!win)
        {
            guessHandler();
            checkGuess();

            System.out.print("Black pegs: " + bP + "\nWhite pegs: " + wP + "\n\n");

            if (bP == 4)
            {
                win = true;
            }
        }

        if (win)
        {
            System.out.print("Congrats you won! The secret number was:\n" + secretNumber);
        }


    }

}
