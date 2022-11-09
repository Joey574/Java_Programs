import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

public class GS07_05
{
    
    public static String reverse(String t)
    {
        int start = 0;
        int stop = 0;

        ArrayList <String> temp = new ArrayList<String>();

        String x;
        String out = "";
        
        for (int i = 0; i < t.length(); i++)
        {
            if (t.charAt(i) == ' ')
            {
                stop = i;

                x = t.substring(start, stop);
                x = x.strip();

                temp.add(0, x);
                start = i;
            }
        }

        if (t.charAt(t.length() - 1) != ' ')
        {
            temp.add(0, t.substring(start));
        }

        for (int i = 0; i < temp.size(); i++)
        {
            out += temp.get(i) + " ";
        }

        if (out.charAt(0) == ' ')
        {
            out = out.substring(1);
        }

        return out;
    }
    
    public static void main(String[] args) throws IOException {
        String fileName = "GS07_05_File.txt";

        FileReader fr = new FileReader(fileName);
        Scanner lineScanner = new Scanner(fr);

        ArrayList <String> fileReversed = new ArrayList<String>();

        while (lineScanner.hasNextLine())
        {
            fileReversed.add(0, lineScanner.nextLine());
        }

        for (int i = 0; i < fileReversed.size(); i++)
        {
            fileReversed.add(i, reverse(fileReversed.get(i)));
            fileReversed.remove (i + 1);
        }

        System.out.print(fileReversed);

        fr.close();

        FileChannel.open(Paths.get(fileName), StandardOpenOption.WRITE).truncate(0).close();

        FileWriter fw = new FileWriter((fileName));

        for (int i = 0; i < fileReversed.size(); i++)
        {
            fw.write(fileReversed.get(i) + "\n");
        }

        fw.close();
    }
}