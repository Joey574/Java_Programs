import java.awt.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class GS14_03
{

/*
Author: Joey Soroka
Problem:
Purpose:
Pseudocode:
Maintenance Log:
 */

    public static String canonicalForm(String word) {
        char[] string = new char[word.length()];
        for (int i = 0; i < word.length(); i ++) {
            string[i] = word.charAt(i);
        }
        mergeSort(string);
        StringBuilder result = new StringBuilder();
        for (char ch: string) {
            result.append(ch);
        }
        return result.toString();
    }

    public static void mergeSort(char[] string) {
        if (string.length > 1) {
            char[] left = Arrays.copyOfRange(string, 0, string.length/2);
            char[] right = Arrays.copyOfRange(string, string.length/2, string.length);
            mergeSort(left);
            mergeSort(right);
            merge(string, left, right);
        }
    }

    public static void merge(char[] string, char[] left, char[] right) {
        int i1 = 0;
        int i2 = 0;
        for (int i = 0; i < string.length; i++) {
            if (i1 < left.length && i2 < right.length) {
                if (left[i1] - right[i2] <= 0) {
                    string[i] = left[i1];
                    i1++;
                } else {
                    string[i] = right[i2];
                    i2++;
                }
            } else if (i2 >= right.length) {
                string[i] = left[i1];
                i1++;
            } else {
                string[i] = right[i2];
                i2++;
            }
        }
    }

    public static void main(String[] args) throws IOException {

        String fileName = "dictionarySorted.txt";
        FileReader fr = new FileReader(fileName);
        Scanner lineScanner = new Scanner(fr);

        HashMap<String, ArrayList<String>> Anagrams = new HashMap<>();

        while(lineScanner.hasNextLine()) {
            String temp = lineScanner.nextLine();
            String tempA = canonicalForm(temp);
            ArrayList<String> values = new ArrayList<>();
            if (Anagrams.containsKey(tempA)) {
                values = Anagrams.get(tempA);
                values.add(temp);
                Anagrams.put(tempA, values);
            } else {
                values.add(temp);
                Anagrams.put(tempA, values);
            }
        }

        fileName = "anagramOutput.txt";
        FileWriter fw = new FileWriter(fileName);

        ArrayList<String> keys = (ArrayList<String>) Anagrams.keySet();

        for (int i = 0; i < keys.size(); i++) {
            fw.write(keys.get(i) + ": " + Anagrams.get(keys.get(i)) + "\n");
        }
    }
}
