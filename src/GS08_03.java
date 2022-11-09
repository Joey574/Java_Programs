import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class GS08_03
{

/*
Author: Joey Soroka
Problem:
Purpose:
Pseudocode:
Maintenance Log:
 */

    public static List<String> removeEvenLength(List<String> stringSet)
    {
        Iterator<String> i = stringSet.iterator();

        while(i.hasNext())
        {
            if (i.next().length() % 2 == 0)
            {
                i.remove();
            }
        }

        return stringSet;
    }

    public static void main(String[] args)
    {

    }
}
