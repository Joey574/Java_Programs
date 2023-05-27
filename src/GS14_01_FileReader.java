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

        while (lineScanner.hasNextLine())
        {
            words.add(lineScanner.nextLine());
        }

        fr.close();
        System.out.println("File closed");

        String temp = "";
        List<String> tempValues = new ArrayList<>();
        int beginLoc = 1;
        int endLoc = 0;

        for (int i = 0; i < words.size(); i++) { // iterating over entire words array
            tempValues = new ArrayList<>();
            if (words.get(i).contains(":")) { // find if word is key
                temp = words.get(i).replace(":", ""); // assign temp key
            } else if (words.get(i).contains("[")) { // find if word is value
                beginLoc = 1;
                endLoc = 0;
                for (int x = 1; x < words.get(i).length(); x++) { // iterate over words.get(i) length
                    if (words.get(i).charAt(x) == ',' || words.get(i).charAt(x) == ']') { // comma-delimited words with bracket at end
                        endLoc = x;
                        tempValues.add(words.get(i).substring(beginLoc, endLoc));
                        beginLoc = x + 2; // accommodate for , and space after
                    }
                }
            }
            EditNeighbors.put(temp, tempValues);
        }

        System.out.println("EditNeighbors built\nMap size: " + EditNeighbors.size());

        Scanner r = new Scanner(System.in);

        System.out.print("Enter starting word: ");
        firstWord = r.nextLine();
        System.out.print("Enter ending word: ");
        secondWord = r.nextLine();

        firstWord = firstWord.replaceAll(" ", "");
        secondWord = secondWord.replaceAll(" ", "");

        beginTime = System.currentTimeMillis();

        if (EditNeighbors.containsKey(firstWord) && EditNeighbors.containsKey(secondWord)) { // check for if words are present in map
            ArrayList<String> path = new ArrayList<String>();
            Set<String> examined = new HashSet<String>();
            String target = firstWord;
            boolean failed = false;

            for (int i = 0; !target.equals(secondWord) && !failed; i++) { // looping until either path found or nowhere left to go
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
            System.out.println("Total time elapsed (ms): " + (System.currentTimeMillis() - beginTime));
            System.out.println("Examined size: " + examined.size());
        } else {
            throw new RuntimeException("Word not found in map and or dictionary");
        }
    }
}
