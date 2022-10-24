import java.util.Scanner;

public class GS05_02
{
/*
Author: Joey Soroka
Problem:
Purpose:
Pseudocode:
Maintenance Log:
 */

    static char [] guesses = new char[0];
    static char [] tempGuess = new char[0];
    static int progress = 0;
    static String word;


    public static void draw ()
    {

        boolean temp = false;

        if (progress > 0)
        {
            System.out.print("  o\n");
        }
        else
        {
            System.out.print("\n");
        }

        if (progress > 2)
        {
            System.out.print(" /");
        }
        else
        {
            System.out.print("  ");
        }

        if (progress > 1)
        {
            System.out.print("|");
        }
        else
        {
            System.out.print(" ");
        }

        if (progress > 3)
        {
            System.out.print("\\\n");
        }
        else
        {
            System.out.print("\n");
        }

        if (progress > 4)
        {
            System.out.print(" /");
        }
        else
        {
            System.out.print("  ");
        }

        if (progress > 5)
        {
            System.out.print(" \\\n");
        }
        else
        {
            System.out.print("\n");
        }

        for (int i = 0; i < word.length(); i++)
        {
            temp = false;
            for (int q = 0; q < guesses.length; q++)
            {
                if (word.charAt(i) == guesses[q])
                {
                    System.out.print(word.charAt(i) + " ");
                    temp = true;
                }
            }
            if (!temp)
            {
                System.out.print("_ ");
            }
        }

    }

    public static void charGuess()
    {
        boolean guessed = false;
        boolean cGuess = false;

        Scanner r = new Scanner(System.in);

        String temp;

        while(true)
        {
            System.out.print("\nEnter char to guess: ");
            temp = r.nextLine();

            for (int i = 0; i < guesses.length; i++)
            {
                if (temp.charAt(0) == guesses[i])
                {
                    guessed = true;
                }
            }

            if (guessed)
            {
                System.out.print("\nAlready guessed that char");
                continue;
            }
            else
            {
                tempGuess = new char [guesses.length];

                for (int i = 0; i < guesses.length; i++)
                {
                    tempGuess[i] = guesses[i];
                }

                guesses = new char [guesses.length + 1];

                for (int i = 0; i < tempGuess.length; i++)
                {
                    guesses[i] = tempGuess[i];
                }

                guesses[guesses.length - 1] = temp.charAt(0);
            }

            for (int i = 0; i < word.length(); i++)
            {
                if (guesses[guesses.length - 1] == word.charAt(i))
                {
                    cGuess = true;
                    break;
                }
            }

            if (!cGuess)
            {
                progress++;
            }
            break;
        }

    }


    public static void main(String[] args)
    {
        Scanner r = new Scanner(System.in);

        int temp;

        boolean win = false;
        boolean lose = false;


        System.out.print("Enter secret word: ");
        word = r.nextLine();

       while(!win && !lose)
       {
            if (progress > 5)
            {
                lose = true;
            }

            draw();

            while(true)
            {
                System.out.print("\n1: Guess letter (cost 1 if failed)\n2: Guess word (cost 2 if failed)\nInput: ");
                temp = r.nextInt();

                if (temp == 1)
                {
                    charGuess();
                }
                else if (temp == 2)
                {

                }
                else
                {
                    continue;
                }
                break;
            }


       }

    }
}
