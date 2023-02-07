import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class GS14_01
{

/*
Author: Joey Soroka
Helper: Jackson Heckert :)
Problem:
Purpose:
Pseudocode:
Maintenance Log:
 */



    public static boolean isEditDistance (String in1, String in2) {
        return letterDifference(in1, in2) < 2 && !in1.equals(in2);
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


    public static void main(String[] args) throws IOException {
        //String fileName = "dictionarySorted.txt";
        //String fileName = "dictionaryCatDog.txt";
        String fileName = "dictionarySortedLength.txt";

        FileReader fr = new FileReader(fileName);
        Scanner lineScanner = new Scanner(fr);

        LinkedList<String> words = new LinkedList<>();

        while (lineScanner.hasNextLine())
        {
            words.add(lineScanner.nextLine());
        }

        fr.close();
        System.out.println("File closed");
        
        String firstWord;
        String secondWord;

        HashMap<String, List<String>> EditNeighbors = new HashMap<String, List<String>>();

        Scanner r = new Scanner(System.in);

        System.out.print("Enter starting word: ");
        firstWord = r.nextLine();
        System.out.print("Enter ending word: ");
        secondWord = r.nextLine();

        long beginTime = System.currentTimeMillis();
        int smallestDifFound = letterDifference(firstWord, secondWord);
        int startDif = smallestDifFound;

        int smallestTargetLengthLoc;
        int smallestWordLength;
        int biggestWordLength;

        int smallBuffer = 0;
        int bigBuffer = 0;

        if (firstWord.length() > secondWord.length()) {
            smallestWordLength = secondWord.length();
            biggestWordLength = firstWord.length();
            if (smallestWordLength > 5) {
                smallBuffer = smallestWordLength / 3;
            }
            smallestTargetLengthLoc = Algorithms.binarySearchFirstLength(words, secondWord, smallBuffer);
        } else {
            smallestWordLength = firstWord.length();
            biggestWordLength = secondWord.length();
            if (smallestWordLength > 5) {
                smallBuffer = smallestWordLength / 3;
            }
            smallestTargetLengthLoc = Algorithms.binarySearchFirstLength(words, firstWord, smallBuffer);
        }

        if (biggestWordLength > 7) {
            bigBuffer = biggestWordLength / 3;
        }

        System.out.println("Binary search time (ms): " + (System.currentTimeMillis() - beginTime));

        for (int i = smallestTargetLengthLoc; i < words.size(); i++) {

           String x = words.get(i);
           
           if (x.length() > biggestWordLength + bigBuffer) {
               break;
           }

           boolean a = letterDifference(x, secondWord) <= smallestDifFound + smallestWordLength;
           boolean b = letterDifference(x, firstWord) <= startDif;

            if (Math.abs(firstWord.length() - secondWord.length()) >= Math.abs(x.length() - secondWord.length()) && a || b) {
                
                ArrayList<String> neighbors = new ArrayList<String>();

                if (letterDifference(x, secondWord) < smallestDifFound) {
                    smallestDifFound = letterDifference(x, secondWord);
                }

                for (String temp : words) {
                    if (Math.abs(temp.length() - x.length()) < 2) {
                        if (isEditDistance(temp, x)) {
                            neighbors.add(temp);
                        }
                    } else if (temp.length() > x.length()){
                        break;
                    }
                }
                EditNeighbors.put(x, neighbors);
            }
        }

        System.out.println("Map size: " + EditNeighbors.size());
        System.out.println("Total time elapsed to create map (ms): " + (System.currentTimeMillis() - beginTime));

        if (EditNeighbors.containsKey(firstWord) && EditNeighbors.containsKey(secondWord)) {

        } else {
            throw new RuntimeException("Word not found in map and or dictionary");
        }
    }
}
