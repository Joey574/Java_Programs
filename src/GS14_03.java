import java.awt.*;
import java.io.FileReader;
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


    public static HashMap<Character, Integer> mapValues(String in) {
        HashMap<Character, Integer> out = new HashMap<Character, Integer>();

        for (int i = 0; i < in.length(); i++) {
            if (out.containsKey(in.charAt(i))) {
                out.put(in.charAt(i), out.get(in.charAt(i)) + 1);
            } else {
                out.put(in.charAt(i), 1);
            }
        }

        return out;
    }

    public static class compareMaps implements Comparator<Map<Character, Integer>>
    {
        public int compare(Map<Character, Integer> o1, Map<Character, Integer> o2) {
            if (o1.equals(o2)) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String fileName = "dictionarySortedLength.txt";

        FileReader fr = new FileReader(fileName);
        Scanner lineScanner = new Scanner(fr);

        LinkedList<String> words = new LinkedList<>();

        while (lineScanner.hasNextLine()) {
            words.add(lineScanner.nextLine());
        }

        fr.close();
        System.out.println("File closed");

        Scanner r = new Scanner(System.in);

        System.out.print("Enter word: ");
        String word = r.nextLine();

        int startLoc = Algorithms.binarySearchFirstLength(words, word, 0);

        HashMap<Character, Integer> startMap = mapValues(word);
        ArrayList<String> anagrams = new ArrayList<>();
        List<HashMap<Character, Integer>> mapList = new LinkedList<>();
        mapList.add(startMap);

        for (int i = startLoc; words.get(i).length() <= word.length(); i++) {
            HashMap<Character, Integer> temp = mapValues(words.get(i));
            mapList.add(temp);
            Collections.sort(mapList, new compareMaps());

            if ( && !words.get(i).equals(word)) {
                anagrams.add(words.get(i));
            }
        }
        System.out.println(anagrams);
    }
}
