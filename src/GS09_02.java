import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GS09_02
{

/*
Author: Joey Soroka
Problem:
Purpose:
Pseudocode:
Maintenance Log:
 */

    public static int countUnique (List<Integer> in)
    {
        Set<Integer> temp = new HashSet<Integer>(in);

        return temp.size();
    }
}
