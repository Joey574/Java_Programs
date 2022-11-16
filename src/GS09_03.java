import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

public class GS09_03
{

/*
Author: Joey Soroka
Problem:
Purpose:
Pseudocode:
Maintenance Log:
 */

    public static int maxLength (Set<String> in)
    {
        int length = 0;

        String temp;
        String longest = "";

        LinkedList<String> stringSet = new LinkedList<String>(in);

        Iterator<String> i = stringSet.iterator();

        while(i.hasNext())
        {
            temp = i.next();

            if (temp.length() > longest.length())
            {
                longest = temp;
            }
        }

        return longest.length();
    }
}
