import java.util.*;

public class GS09_04
{

/*
Author: Joey Soroka
Problem:
Purpose:
Pseudocode:
Maintenance Log:
 */

    public static void rarest (Map<String, Integer> in)
    {
        int r = 0;

        ArrayList<Integer> values = (ArrayList<Integer>) in.values();
        ArrayList<String> keys = (ArrayList<String>) in.keySet();
        int [] occurrence = new int [values.size()];

        for (int k = 0; k < keys.size(); k++)
        {
            for (int v = 0; v < values.size(); v++)
            {
                if (Objects.equals(in.get(keys.get(k)), values.get(v)))
                {
                    occurrence[v]++;
                }
            }
        }

            for (int i = 0; i < occurrence.length; i++)
            {
                if (occurrence[i] < occurrence[r])
                {
                    r = i;
                }
                else if (occurrence[i] == occurrence[r])
                {
                    if (values.get(i) < values.get(r))
                    {
                        r = i;
                    }
                }
            }
    }
}
