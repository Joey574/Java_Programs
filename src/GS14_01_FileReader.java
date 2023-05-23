import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static java.lang.Thread.sleep;

public class GS14_01_FileReader
{

/*
Author: Joey Soroka
Helper: Jackson Heckert :)
Problem:
Purpose:
Pseudocode:
Maintenance Log:
 */

    static String firstWord;
    static String secondWord;
    static long beginTime;
    static HashMap<String, List<String>> EditNeighbors = new HashMap<String, List<String>>();
    static List<String> words = new ArrayList<>();

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

    public static void main(String[] args) throws IOException, InterruptedException {
        String fileName = "EditNeighbors.txt";
        //String fileName = "dictionaryMonkeyBusiness.txt";

        FileReader fr = new FileReader(fileName);
        Scanner lineScanner = new Scanner(fr);

        String temp;
        List<String> tempValues = new ArrayList<>();

        while (lineScanner.hasNextLine())
        {
            words.add(lineScanner.nextLine());

            if (words.get(words.size() - 1).contains(":")) {
                temp = words.get(words.size() - 1);
            }

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

        beginTime = System.currentTimeMillis();

        System.out.println("Binary search time (ms): " + (System.currentTimeMillis() - beginTime));

        if (EditNeighbors.containsKey(firstWord) && EditNeighbors.containsKey(secondWord)) { // check for if words are present in map
            ArrayList<String> path = new ArrayList<String>();
            Set<String> examined = new HashSet<String>();
            String target = firstWord;
            boolean complete = false;

            for (int i = 0; !target.equals(secondWord) && !complete; i++) { // looping until either path found or nowhere left to go
                ArrayList<String> values = new ArrayList<String>(EditNeighbors.get(target));
                ArrayList<String> pValues = new ArrayList<>();
                int smallestDif = letterDifference(target, secondWord);
                int smallestDifLoc = -1;

                if (examined.contains(target)) { // double failsafe
                    if (path.size() < 2) { // nowhere else to go
                        complete = true;
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
                        complete = true;
                    } else {
                        path.remove(path.size() - 1);
                        target = path.get(path.size() - 1);
                    }
                }
            }

            if (!complete) {
                System.out.println("Path found: " + path);
            } else {
                System.out.println("No path found: " + path);
            }
            System.out.println("Total time elapsed (ms): " + (System.currentTimeMillis() - beginTime));
            System.out.println("Map Size: " + EditNeighbors.size());
            System.out.println("Examined size: " + examined.size());
        } else {
            throw new RuntimeException("Word not found in map and or dictionary");
        }
    }
}
