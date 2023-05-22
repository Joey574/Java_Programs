import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static java.lang.Thread.sleep;

public class GS14_01_Graphing
{

/*
Author: Joey Soroka
Helper: Jackson Heckert :)
Problem:
Purpose:
Pseudocode:
Maintenance Log:
 */

    static int THREAD_NUM = 12;

    static int mapTarget;
    static int smallBuffer = 0;
    static int bigBuffer = 0;
    static long beginTime;
    static String firstWord;
    static String secondWord;
    static String smallestWord;
    static String biggestWord;
    static List<String> words = new ArrayList<>();
    static HashMap<String, List<String>> EditNeighbors = new HashMap<String, List<String>>();
    static int [] wordLoc = new int[32];

    public static int binarySearchFirstLength(String target, int smallBuffer) {

        if (wordLoc[target.length() - smallBuffer] != -1)
        {
            return wordLoc[target.length() - smallBuffer];
        }

        boolean complete = false;

        int out = -1;
        int start = 0;
        int end = words.size();

        for (int i = 0; !complete; i++) {
            int loc = (start + end) / 2;
            if (words.get(loc).length() == target.length()) {
                complete = true;
                out = loc;
            } else if (words.get(loc).length() > target.length()) {
                end = loc;
            } else if (words.get(loc).length() < target.length()) {
                start = loc;
            }
            if (start > end || start == end) {
                complete = true;
                out = -loc;
            }
        }

        while(words.get(out).length() >= target.length() - smallBuffer) {
            if (out > 30) {
                out -= 30;
            } else if (out > 0){
                out--;
            } else {
                return 0;
            }
        }

        while(words.get(out).length() != target.length() - smallBuffer) {
            out++;
        }

        wordLoc[target.length() - smallBuffer] = out;
        return out;
    }
    public static boolean isEditDistance (String in1, String in2) {
        int m = in1.length(), n = in2.length();

        int count = 0;

        int i = 0, j = 0;
        while (i < m && j < n)
        {
            if (in1.charAt(i) != in2.charAt(j)) {
                if (count == 1) {
                    return false;
                }
                if (m > n) {
                    i++;
                } else if (m < n) {
                    j++;
                } else {
                    i++;
                    j++;
                }
                count++;
            } else {
                i++;
                j++;
            }
        }
        if (i < m || j < n) {
            count++;
        }
        return count == 1;
    }
    public static int letterDifference(String i1, String i2) {
        int t = 0;
        int i = 0;

        for (i = 0; i < i1.length() && i < i2.length(); i++) {
            if (i1.charAt(i) != i2.charAt(i)) {
                t++;
            }
        }

        if (i < i1.length()) {
            t += i1.length() - i;
        } else if (i < i2.length()) {
            t += i2.length() - i;
        }

        return t;
    }

    public static void findNeighbors(String target) {
        ArrayList<String> neighbors;
        neighbors = new ArrayList<String>();

        for (int p = binarySearchFirstLength(target, 1); p < words.size(); p++) {
            String temp = words.get(p);
            if (temp.length() > target.length() + 1) {
               break;
            } else {
                if (isEditDistance(temp, target)) {
                    neighbors.add(temp);
                }
            }
        }
                EditNeighbors.put(target, neighbors);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        String fileName = "dictionarySortedLength.txt";
        //String fileName = "dictionaryMonkeyBusiness.txt";

        Object sync = new Object();

        Arrays.fill(wordLoc, -1);
        wordLoc[1] = 0;

        FileReader fr = new FileReader(fileName);
        Scanner lineScanner = new Scanner(fr);

        while (lineScanner.hasNextLine())
        {
            words.add(lineScanner.nextLine());
        }

        fr.close();
        System.out.println("File closed");

        Scanner r = new Scanner(System.in);

        System.out.print("Enter starting word: ");
        firstWord = r.nextLine();
        System.out.print("Enter ending word: ");
        secondWord = r.nextLine();

        firstWord = firstWord.replaceAll(" ", "");
        secondWord = secondWord.replaceAll(" ", "");

        if (firstWord.length() > secondWord.length()) {
            smallestWord = secondWord;
            biggestWord = firstWord;
        } else {
            smallestWord = firstWord;
            biggestWord = secondWord;
        }
        if (smallestWord.length() > 5) {
            smallBuffer = smallestWord.length() / 3;
        }
        if (biggestWord.length() > 7) {
            bigBuffer = biggestWord.length() / 3;
        }

        mapTarget = binarySearchFirstLength(smallestWord, smallBuffer);


        long pathTime = System.currentTimeMillis();

        if (true) { // check for if words are present in map
            ArrayList<String> path = new ArrayList<String>();
            Set<String> examined = new HashSet<String>();
            String target = firstWord;
            boolean failed = false;

            for (int i = 0; !target.equals(secondWord) && !failed; i++) { // looping until either path found or nowhere left to go

                findNeighbors(target);

                ArrayList<String> values = new ArrayList<String>(EditNeighbors.get(target));
                ArrayList<String> pValues = new ArrayList<>();
                int smallestDif = letterDifference(target, secondWord);
                int smallestDifLoc = -1;

                if (examined.contains(target)) { // double failsafe
                    if (path.size() < 2) { // nowhere else to go
                        failed = true;
                    } else {
                        path.remove(path.size() - 1);
                        target = path.get(path.size() - 1);
                    }
                }

                path.add(target);

                for (int p = 0; p < values.size(); p++) { // checking for possible values we can use
                    if (!Algorithms.containsString(values.get(p), path) && !examined.contains(values.get(p))) {
                        pValues.add(values.get(p));
                    }
                }

                if (pValues.size() > 0) { // haven't made it to end of path yet
                    smallestDifLoc = 0;
                    smallestDif = letterDifference(pValues.get(0), secondWord);

                    for (int x = 0; x < pValues.size(); x++) { // checking which possible value will get us the closest to end
                        if (letterDifference(pValues.get(x), secondWord) < smallestDif) {
                            smallestDifLoc = x;
                            smallestDif = letterDifference(pValues.get(x), secondWord);
                        }
                    }

                    target = pValues.get(smallestDifLoc);
                    if (secondWord.equals(target)) { // word found
                        path.add(target);
                    }

                } else { // no possible values found
                    examined.add(target);
                    if (path.size() < 2) { // nowhere else to go
                        failed = true;
                    } else {
                        path.remove(path.size() - 1);
                        target = path.get(path.size() - 1);
                    }
                }
            }

            if (!failed) {
                System.out.println("Path found: " + path);
            } else {
                System.out.println("No path found: " + path);
            }

            System.out.println("Total time elapsed to find path (ms): " + (System.currentTimeMillis() - pathTime));
            System.out.println("Total time elapsed (ms): " + (System.currentTimeMillis() - pathTime));

        } else {
            throw new RuntimeException("Word not found in map and or dictionary");
        }
    }
}
