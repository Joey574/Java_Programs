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

    public static void draw (int progress, String word, char [] guesses)
    {

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
            for (int q = 0; q < guesses.length; q++)
            {
                if (word.charAt(i) == guesses[q])
                {
                    System.out.print(word.charAt(i) + " ");
                }
                else
                {
                    System.out.print("_ ");
                }
            }
        }

    }

    public static void charGuess(char [] guesses, String word)
    {
        Scanner r = new Scanner(System.in);

        String temp;

        System.out.print("\nEnter char to guess: ");
        temp = r.nextLine();
        guesses = temp.charAt(0);
    }


    public static void main(String[] args)
    {
        Scanner r = new Scanner(System.in);

        String word;

        int progress = 0;
        int temp;

        char [] guesses = new char[1];

        boolean win = false;
        boolean lose = false;


        System.out.print("Enter secret word: ");
        word = r.nextLine();

        //while(!win && !lose)
        //{
            if (progress > 5)
            {
                lose = true;
            }

            draw(progress, word, guesses);

            while(true)
            {
                System.out.print("\n 1: Guess letter (cost 1 if failed)\n2: Guess word (cost 2 if failed)\nInput: ");
                temp = r.nextInt();

                if (temp == 1)
                {
                    charGuess(guesses, word);
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


       // }

    }
}
