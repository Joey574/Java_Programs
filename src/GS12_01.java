import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class GS12_01
{
    public static void main(String[] args) throws IOException {
        String fileName = "GS12_01_File.txt";

        FileReader fr = new FileReader(fileName);
        Scanner lineScanner = new Scanner(fr);

        ArrayList<String> words = new ArrayList<String>();

        while (lineScanner.hasNextLine())
        {
            while (lineScanner.hasNext()) {
                words.add(lineScanner.next());
            }
        }

        fr.close();

        String firstWord;
        String secondWord;

        Scanner r = new Scanner(System.in);

        System.out.print("Enter first word: ");
        firstWord = r.nextLine();
        System.out.print("Enter second word: ");
        secondWord = r.nextLine();

        Arrays.binarySearch()
    }
}
