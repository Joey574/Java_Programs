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

    static byte secretDigits [] = new byte[4];

    static byte guessDigits [] = new byte[4];

    static boolean tChecked [] = new boolean[4];
    static boolean bChecked [] = new boolean[4];

    static byte bP = 0; // correct digit, right location
    static byte wP = 0; // correct digit, wrong location

    public static void guessHandler()
    {
        Scanner r = new Scanner(System.in);

        System.out.print("Enter ones place guess: ");
        guessDigits[3] = r.nextByte();
        System.out.print("Enter tens place guess: ");
        guessDigits[2] = r.nextByte();
        System.out.print("Enter hundreds place guess: ");
        guessDigits[1] = r.nextByte();
        System.out.print("Enter thousands place guess: ");
        guessDigits[0] = r.nextByte();

        System.out.print("Guess: ");

        for (int i = 0; i < guessDigits.length; i++)
        {
            System.out.print(guessDigits[i]);
        }
        System.out.print("\n\n");

    }

    public static void checkGuess()
    {
        bP = 0;
        wP = 0;

        tChecked[0] = false; bChecked[0] = false;
        tChecked[1] = false; bChecked[1] = false;
        tChecked[2] = false; bChecked[2] = false;
        tChecked[3] = false; bChecked[3] = false;

        for (int i = 0; i < guessDigits.length; i++)
        {
            if (guessDigits[i] == secretDigits[i])
            {
                bP++;
                tChecked[i] = true;
                bChecked[i] = true;
            }
        }

        for (int i = 0; i < guessDigits.length; i++)
        {
            for (int q = 0; q < secretDigits.length; q++)
            {
                if (guessDigits[i] == secretDigits[q] && !bChecked[q] && !tChecked[i])
                {
                    wP++;

                    tChecked[i] = true;
                    bChecked[q] = true;
                }
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
            secretDigits[3] = r.nextByte();
            System.out.print("Enter tens place: ");
            secretDigits[2] = r.nextByte();
            System.out.print("Enter hundreds place: ");
            secretDigits[1] = r.nextByte();
            System.out.print("Enter thousands place: ");
            secretDigits[0] = r.nextByte();
            System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n");
        }
        else if (input == 2)
        {
            secretDigits[0] += (int) ((java.lang.Math.random() - 0.1) * 10); // thousands place
            secretDigits[1] += (int) ((java.lang.Math.random() - 0.1) * 10); // hundreds place
            secretDigits[2] += (int) ((java.lang.Math.random() - 0.1) * 10); // tens place
            secretDigits[3] += (int) ((java.lang.Math.random() - 0.1) * 10); // ones place
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
            System.out.print("Congrats you won! The secret number was:\n");
        }

        for (int i = 0 ; i < secretDigits.length; i++)
        {
            System.out.print(secretDigits[i]);
        }

        System.out.print("\n\n");


    }

}
