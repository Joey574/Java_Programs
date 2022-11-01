import java.util.Random;
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

        System.out.print("\nGuessed chars:\n");
        for (int i = 0; i < guesses.length; i++)
        {
            System.out.print(guesses[i] + ", ");
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

            temp = temp.toLowerCase();

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
                guessed = false;
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

        Random randI = new Random();

        int temp = 0;

        boolean win = false;
        boolean lose = false;

        String wordArray [] = new String[10];
        wordArray[0] = "programming";
        wordArray[1] = "computers";
        wordArray[2] = "engineering";
        wordArray[3] = "science";
        wordArray[4] = "halloween";
        wordArray[5] = "jupiter";
        wordArray[6] = "desk";
        wordArray[7] = "olympics";
        wordArray[8] = "java";
        wordArray[9] = "unity";

        System.out.print("Menu\n1: Enter custom word\n2: Random word\nInput: ");
        temp = r.nextInt();

        if (temp == 1)
        {
            System.out.print("Enter secret word: ");
            word = r.nextLine();

            word = word.toLowerCase();

            System.out.print("\n\n\n\n\n\n\n\n\n\n");

        }
        else if (temp == 2)
        {
            word = wordArray[randI.nextInt(10)];
        }
        else
        {
            System.out.print("Error expected 1 or 2");
        }


       while(!win && !lose)
       {
            if (progress > 4)
            {
                lose = true;
            }

            temp = 0;

            for (int i = 0; i < guesses.length; i++)
            {
                for (int p = 0; p < word.length(); p++)
                {
                    if (word.charAt(p) == guesses[i])
                    {
                        temp++;
                    }
                }
            }

            if (temp == word.length() - 1)
            {
                win = true;
            }

            draw();
            charGuess();

       }

       if (win)
       {
           System.out.print("\n\nYou win! The secret word was " + word);
       }
       else if (lose)
       {
           System.out.print("\n\nYou've lost! The secret word was " + word);
       }
       System.out.print("\n");
    }
}
