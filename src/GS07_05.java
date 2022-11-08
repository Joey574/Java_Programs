import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class GS07_05
{
    
    public static String reverse(String t)
    {
     
        for (int i = 0; i < t.size(); i++)
        {
            if (t.charAt(i) == ' ')
            {
                   
            }
        }
        
    }
    
    public static void main(String[] args) throws FileNotFoundException
    {
        String fileName = "GS07_05_File.txt";

        FileReader fr = new FileReader(fileName);
        Scanner lineScanner = new Scanner(fr);

        ArrayList <String> File = new ArrayList<String>();

        for (int i = 0; lineScanner.hasNextLine(); i++)
        {
            File.add(0, lineScanner.nextLine());
            
           
        }
    }
}

