import java.util.Scanner;

public class GS05_01 {
/*
Author: Joey Soroka
Problem:
Purpose:
Pseudocode:
Maintenance Log:
 */

    public static void display(int board[][])
    {
        int temp = 1;

        for (int i = 0; i < 3; i++)
        {
            for (int p = 0; p < 3; p++)
            {
                if (board[i][p] == 0)
                {
                    System.out.print(temp);
                }
                else if (board[i][p] == 1)
                {
                    System.out.print("X");
                }
                else if (board[i][p] == 2)
                {
                    System.out.print("O");
                }

                if (p < 2) {
                    System.out.print("|");
                }

                temp++;

            }

            if (i < 2) {
                System.out.print("\n-----\n");
            }
        }
    }

    public static boolean gameCheck(int board[][])
    {
        boolean gameOver = false;

        for (int i = 0; i < 3; i++)
        {
            if (board[i][0] == 1 && board[i][1] == 1 && board[i][2] == 1)
            {
                gameOver = true;
            }
            else if (board[i][0] == 2 && board[i][1] == 2 && board[i][2] == 2)
            {
                gameOver = true;
            }
            else if (board[0][i] == 1 && board[1][i] == 1 && board[2][i] == 1)
            {
                gameOver = true;
            }
            else if (board[0][i] == 2 && board[1][i] == 2 && board[2][i] == 2)
            {
                gameOver = true;
            }
        }

        if (board[0][0] == 1 && board[1][1] == 1 && board[2][2] == 1)
        {
            gameOver = true;
        }
        else if (board[0][2] == 1 && board[1][1] == 1 && board[2][0] == 1)
        {
            gameOver = true;
        }
        else if (board[0][0] == 2 && board[1][1] == 2 && board[2][2] == 2)
        {
            gameOver = true;
        }
        else if (board[0][2] == 2 && board[1][1] == 2 && board[2][0] == 2)
        {
            gameOver = true;
        }

        return gameOver;
    }

    public static void turnManagaer(int board[][], int turn)
    {
        Scanner reader= new Scanner(System.in);

        int choice;

        while(true)
        {
            System.out.print("\nEnter num: ");
            choice = reader.nextInt();

            if (choice == 1 && board [0][0] == 0)
            {
                board [0][0] = (turn % 2) + 1;
            }
            else if (choice == 2 && board [0][1] == 0)
            {
                board [0][1] = (turn % 2) + 1;
            }
            else if (choice == 3 && board [0][2] == 0)
            {
                board [0][2] = (turn % 2) + 1;
            }
            else if (choice == 4 && board [1][0] == 0)
            {
                board [1][0] = (turn % 2) + 1;
            }
            else if (choice == 5 && board [1][1] == 0)
            {
                board [1][1] = (turn % 2) + 1;
            }
            else if (choice == 6 && board [1][2] == 0)
            {
                board [1][2] = (turn % 2) + 1;
            }
            else if (choice == 7 && board [2][0] == 0)
            {
                board [2][0] = (turn % 2) + 1;
            }
            else if (choice == 8 && board [2][1] == 0)
            {
                board [2][1] = (turn % 2) + 1;
            }
            else if (choice == 9 && board [2][2] == 0)
            {
                board [2][2] = (turn % 2) + 1;
            }
            else
            {
                continue;
            }
            break;
        }
    }

    public static void main(String[] args)
    {
        int board [] [] = new int [3] [3];
        int turn = 1;
        int temp = 0;

        boolean gameOver = false;
        boolean tie = false;

        while (!gameOver)
        {
            if (turn % 2 == 0)
            {
                System.out.println("\nPlayer 2 (X's) turn: ");
            }
            else
            {
                System.out.println("\nPlayer 1 (O's) turn: ");
            }

            display(board);

            turnManagaer(board, turn);

            gameOver = gameCheck(board);

            temp = 0;
            for (int i = 0; i < 3; i++)
            {
                for (int p = 0; p < 3; p++)
                {
                    if (board[i][p] != 0)
                    {
                        temp++;

                        if (temp == 9)
                        {
                            tie = true;
                        }
                    }
                }
            }

            if (tie)
            {
                break;
            }

            turn++;
        }

        display(board);

        if (!tie)
        {
            System.out.print("\n\nPlayer " + ((turn % 2) + 1) + " wins!\n");
        }
        else
        {
            System.out.print("\n\nIt's a tie!\n");
        }


    }
}
