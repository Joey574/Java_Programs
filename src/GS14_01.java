import java.io.FileReader;
import java.io.IOException;
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
        return letterDifference(in1, in2) < 2;
    }

    public static int letterDifference(String i1, String i2) {
        int t = 0;
        int i = 0;

        for (i = 0; i < i1.length() && i < i2.length(); i++) {
            if (i1.charAt(i) != i2.charAt(i)) {
                t++;
            }
        }

        if (i < i1.length() - 1) {
            t += i1.length() - i;
        } else if (i < i2.length() - 1) {
            t += i2.length() - i;
        }

        return t;
    }


    public static void main(String[] args) throws IOException {
        //String fileName = "dictionarySorted.txt";
        String fileName = "dictionaryCatDog.txt"

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

        int startDif = letterDifference(firstWord, secondWord);

        for (int i = 0; i < words.size(); i++) {
            long startTime = System.currentTimeMillis();
            boolean examined = false;

            ArrayList<String> neighbors = new ArrayList<String>();
            String x = words.get(i);

            if (Math.abs(firstWord.length() - secondWord.length()) > Math.abs(x.length() - secondWord.length()) && letterDifference(x, secondWord) < startDif) {
                examined = true;
                for (String temp : words) {
                    if (Math.abs(temp.length() - x.length()) < 2) {
                        if (isEditDistance(temp, x)) {
                            neighbors.add(temp);
                        }
                    }
                }
            }
            if (examined) {
                EditNeighbors.put(x, neighbors);
            }

            System.out.println(i + " Ms: " + (System.currentTimeMillis() - startTime));
        }

        System.out.println("Map size: " + EditNeighbors.size());

        if (EditNeighbors.containsKey(firstWord) && EditNeighbors.containsKey(secondWord)) {
            String t = firstWord;

            for (int i = 0; !t.equals(secondWord); i++) {
                ArrayList<String> values = new ArrayList<String>(EditNeighbors.get(t));
                ArrayList<Integer> valueDif = new ArrayList<>();
                for (String value : values) {
                    valueDif.add(letterDifference(t, value));
                }
                int smallestDif = valueDif.get(0);
                int smallestDifLoc = 0;
                for (int p = 0; p < valueDif.size(); p++) {
                    if (valueDif.get(p) < smallestDif) {
                        smallestDif = valueDif.get(p);
                        smallestDifLoc = p;
                    }
                }
                t = values.get(smallestDifLoc);
                System.out.println("New value: " + t);
            }

            System.out.println("Value found");

        } else {
            throw new RuntimeException("One or more words not found");
        }
    }
}
