import java.util.*;

public class GS13_04
{

/*
Author: Joey Soroka
Problem:
Purpose:
Pseudocode:
Maintenance Log:
 */


    public static int maxOccurrences(List<Integer> in) {
        int t = 0;

        HashMap<Integer, Integer> occurrence = new HashMap<Integer, Integer>();

        for (int i = 0; i < in.size(); i++) {
            if (occurrence.containsKey(in.get(i))) {
                occurrence.put(in.get(i), occurrence.get(in.get(i)) + 1);
            } else {
                occurrence.put(in.get(i), 1);
            }
        }

        ArrayList<Integer> temp = new ArrayList<Integer>(occurrence.values());

        for (int i = 0; i < temp.size(); i++) {
            if (temp.get(i) > t) {
                t = temp.get(i);
            }
        }

        return t;
    }

    public static void main(String[] args)
    {
        List<Integer> p = new ArrayList<>(Arrays.asList(1, 2, 1, 4, 7, 8, 1, 3, 2, 1, 3, 4, 6, 8, 9, 1));

        System.out.println("Input: " + p);

        int o = maxOccurrences(p);

        System.out.println("Output: " + o);
    }
}
