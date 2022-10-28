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

    public static byte [] guessHandler()
    {
        byte guessDigits [] = new byte [4];

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

        return guessDigits;

    }

    public static byte [] checkGuess(byte guessDigits[], byte secretDigits[])
    {
        boolean tChecked [] = new boolean[4];
        boolean bChecked [] = new boolean[4];

        byte bP = 0;
        byte wP = 0;

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

        return new byte[] {bP, wP};

    }

    public static void main(String[] args)
    {
        byte secretDigits[] = new byte[4];

        byte guessDigits[] = new byte[4];

        byte pegs[] = new byte[2];


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
            guessDigits = guessHandler();
            pegs = checkGuess(guessDigits, secretDigits);

            System.out.print("Black pegs: " + pegs[0] + "\nWhite pegs: " + pegs[1] + "\n\n");

            if (pegs[0] == 4)
            {
                win = true;
            }
        }

        if (win)
        {
            System.out.print("Congrats you won! The secret number was:\n\n");
        }

        for (int i = 0 ; i < secretDigits.length; i++)
        {
            System.out.print(secretDigits[i]);
        }

        System.out.print("\n");


    }

}
