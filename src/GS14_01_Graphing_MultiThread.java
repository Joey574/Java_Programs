import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static java.lang.Thread.sleep;

public class GS14_01_Graphing_MultiThread
{

/*
Author: Joey Soroka
Helper: Jackson Heckert :)
Problem:
Purpose:
Pseudocode:
Maintenance Log:
 */

    static int MAX_THREADS = 12;
    static int currentThreads;
    static String firstWord;
    static String secondWord;
    static String smallestWord;
    static String biggestWord;
    static long beginTime;
    static ArrayList<String> words = new ArrayList<>();
    static int [] wordLoc = new int[32];

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

        beginTime = System.currentTimeMillis();

        if (firstWord.length() > secondWord.length()) {
            smallestWord = secondWord;
            biggestWord = firstWord;
        } else {
            smallestWord = firstWord;
            biggestWord = secondWord;
        }

        ArrayList<MainThread> mainThreads = new ArrayList<>();
        mainThreads.add(new MainThread(firstWord, secondWord, "T1", sync));
        mainThreads.add(new MainThread(secondWord, firstWord, "T2", sync));

        currentThreads += 2;
        mainThreads.get(0).start();
        mainThreads.get(1).start();

    }

    public static class MainThread extends Thread
    {
        private String firstWord;
        private String secondWord;
        private String threadName;
        private Object x;

        MainThread(String firstWord, String secondWord, String threadName, Object x) {
            this.firstWord = firstWord;
            this.secondWord = secondWord;
            this.threadName = threadName;
            this.x = x;
        }

        public void run() {
            try {

            } catch (Exception e) {
                System.out.println(threadName + " error: " + e.getCause() + " :: " + e);
            }
        }

        public void pathForward() {

        }

        public void findNeighbors() {

        }

        public void createThread() {
            if ()
        }
    }
}
