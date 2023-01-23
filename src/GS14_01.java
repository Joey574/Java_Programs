import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;


public class GS14_01
{

/*
Author: Joey Soroka
Problem:
Purpose:
Pseudocode:
Maintenance Log:
 */



    public static boolean isEditDistance (String in1, String in2) {
        for (int i = 0; i < in1.length() && i < in2.length(); i++) {
            in1.charAt(i) == in2.charAt(i);
        }
    }

    public static ArrayList<String> findNeighbors(String start, LinkedList<String> dictionary) {
        ArrayList<String> neighbors = new ArrayList<String>();

        for (String temp : dictionary) {
            if (isEditDistance(temp, start)) {
                neighbors.add(temp);
            }
        }

        return neighbors;
    }


    public static void main(String[] args) throws IOException {
        String fileName = "dictionarySorted.txt";

        FileReader fr = new FileReader(fileName);
        Scanner lineScanner = new Scanner(fr);

        LinkedList<String> words = new LinkedList<>();

        while (lineScanner.hasNextLine())
        {
            words.add(lineScanner.nextLine());
        }

        fr.close();
        
        String firstWord;
        String secondWord;
        int firstWordLoc;
        int secondWordLoc;

        HashMap<String, List<String>> EditNeighbors = new HashMap<String, List<String>>();

        Scanner r = new Scanner(System.in);


        for (int i = 0; i < words.size(); i++) {
            ArrayList<String> temp = findNeighbors(words.get(i), words);
            EditNeighbors.put(words.get(i), temp);
        }

        System.out.print("Enter starting word: ");
        firstWord = r.nextLine();
        System.out.print("Enter ending word: ");
        secondWord = r.nextLine();
    }
}
