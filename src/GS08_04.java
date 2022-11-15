import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GS08_04
{

/*
Author: Joey Soroka
Problem:
Purpose:
Pseudocode:
Maintenance Log:
 */

    public static boolean isUnique(Map<String, String> input)
    {
        Set<String> temp = new HashSet<String>(input.values());

        return input.size() == temp.size();
    }

    public static void main(String[] args)
    {

    }
}
