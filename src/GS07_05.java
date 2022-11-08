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
        
        String temp = "";
        
        for (int i = t.size(); i > 0; i--)
        {
            if (t.charAt(i) == ' ')
            {
                stop = i;
                temp += t.subString(start, stop);
                start = i;
            }
        }
        
        temp += t.subString(start);
        
        return temp;        
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
            File.add(i, reverse(File.at(i)));
            File.remove (i + 1);
        }
    }
}

