import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class GS12_01
{

    static int binarySearchStringArray(ArrayList<String> in, String target) {
        boolean complete = false;
        int out = -1;
        char [] firstLetters = new char [2];
        firstLetters[0] = target.charAt(0);

        int start = 0;
        int end = in.size();

        for (int i = 0; !complete; i++) {
            int loc = (start + end) / 2;
            if (Objects.equals(in.get(loc), target)) {
                out = loc;
                complete = true;
            } else if (start == end || start > end) {
                out = -loc;
                complete = true;
            } else {
                firstLetters[1] = in.get(loc).charAt(0);
                if (firstLetters[0] > firstLetters[1]) {
                    start = loc;
                } else {
                    end = loc;
                }
            }
        }
        return out;
    }

    public static void main(String[] args) throws IOException {
        String fileName = "GS12_01_File.txt";

        int firstWordLoc;
        int secondWordLoc;

        FileReader fr = new FileReader(fileName);
        Scanner lineScanner = new Scanner(fr);

        ArrayList<String> words = new ArrayList<String>();

        while (lineScanner.hasNextLine())
        {
            while (lineScanner.hasNext()) {
                words.add(lineScanner.next());
            }
            System.out.println(words);
        }

        fr.close();

        String firstWord;
        String secondWord;

        Scanner r = new Scanner(System.in);

        System.out.print("Enter first word: ");
        firstWord = r.nextLine();
        System.out.print("Enter second word: ");
        secondWord = r.nextLine();

        firstWordLoc = binarySearchStringArray(words, firstWord);
        secondWordLoc = binarySearchStringArray(words, secondWord);

        System.out.println("First word location: " + firstWordLoc);
        System.out.println("Second word location: " + secondWordLoc);

        if (secondWordLoc - (firstWordLoc + 1) == 1) {
            System.out.println("There is " + (secondWordLoc - (firstWordLoc + 1) + " word in between your inputted words"));
        } else {
            System.out.println("There are " + (secondWordLoc - (firstWordLoc + 1) + " words in between your inputted words"));
        }
    }
}
