import java.util.*;

public class GS08_02
{

/*
Author: Joey Soroka
Problem:
Purpose:
Pseudocode:
Maintenance Log:
 */

    public static ArrayList<Integer> sortAndRemoveDuplicates(ArrayList<Integer> x)
    {
        x.sort(Comparator.naturalOrder());

        Set<Integer> t = new HashSet<Integer>(x);

        Integer temp [] = new Integer[t.size()];

        t.toArray(temp);

        return new ArrayList<Integer>(Arrays.asList(temp));
    }

    public static void main(String[] args)
    {
        ArrayList<Integer> t = new ArrayList<Integer>(Arrays.asList(7, 4, -9, 4, 15, 8, 27, 7, 11, -5, 32, -9, -9));

        System.out.print("Input: " + t);

        t = sortAndRemoveDuplicates(t);

        System.out.print("\nOutput: " + t);
    }
}
