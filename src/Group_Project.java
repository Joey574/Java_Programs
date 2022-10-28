import java.util.Random;

public class Group_Project
{
    /*
Author: Joey Soroka, Slater Swart, Gavin Rodgers
Problem:
Purpose:
Pseudocode:
Maintenance Log:
 */

    public static void main(String[] args)
    {
        boolean complete = false;

        Random randI = new Random();

        int ones = 0;
        int fifty = 0;
        int hundreds = 0;

        int laps;
        int rand = randI.nextInt(101);

        for (laps = 0; !complete; laps++, rand = randI.nextInt(101))
        {
            if (rand == 1)
            {
                ones++;
            }
            else if (rand == 50)
            {
                fifty++;
            }
            else if (rand == 100)
            {
                hundreds++;
            }

            if (ones >= 1000 && fifty >= 1000 && hundreds >= 1000)
            {
                complete = true;
            }
        }

        System.out.print("Complete!\nLaps taken: " + laps + "\nOnes: " + ones + "\nFifties: " + fifty + "\nHundreds: " + hundreds);

        // laps: 103369, ones: 1000, fifties: 1068, hundreds: 1030

    }
}
