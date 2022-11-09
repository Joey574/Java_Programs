import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class GS07_05
{
    
    public static String reverse(String t)
    {
        int start = 0;
        int stop = 0;

        ArrayList <String> temp = new ArrayList<String>();

        String out = "";
        
        for (int i = 0; i < t.length(); i++)
        {
            if (t.charAt(i) == ' ')
            {
                stop = i;
                temp.add(0, t.substring(start, stop)) ;
                start = i;
            }
        }
        
        for (int i = 0; i < temp.size(); i++)
        {
            out += temp.get(i);
        }
        
        return out;
    }
    
    public static void main(String[] args) throws FileNotFoundException
    {
        String fileName = "GS07_05_File.txt";

        FileReader fr = new FileReader(fileName);
        Scanner lineScanner = new Scanner(fr);

        ArrayList <String> File = new ArrayList<String>();

        while (lineScanner.hasNextLine())
        {
            File.add(0, lineScanner.nextLine());           
        }
        
        for (int i = 0; i < File.size(); i++)
        {
            File.add(i, reverse(File.get(i)));
            File.remove (i + 1);
        }

        for (int i = 0; i < File.size(); i++)
        {
            System.out.println(File.get(i));
        }
    }
}

