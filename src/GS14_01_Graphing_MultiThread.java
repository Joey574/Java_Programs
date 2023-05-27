import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

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
    static AtomicInteger currentThreads;
    static String firstWord;
    static String secondWord;
    static String smallestWord;
    static String biggestWord;
    static long beginTime;
    static ArrayList<String> words = new ArrayList<>();
    static int [] wordLoc = new int[32];
    static ArrayList<MainThread> mainThreads = new ArrayList<>();

    public static int binarySearchFirstLength(String target) {
        if (wordLoc[target.length()] != -1)
        {
            return wordLoc[target.length()];
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

        while(words.get(out).length() >= target.length()) {
            if (out > 30) {
                out -= 30;
            } else if (out > 0){
                out--;
            } else {
                return 0;
            }
        }

        while(words.get(out).length() != target.length()) {
            out++;
        }

        wordLoc[target.length()] = out;
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

    public static void main(String[] args) throws IOException, InterruptedException {
        String fileName = "dictionarySortedLength.txt";
        //String fileName = "dictionaryMonkeyBusiness.txt";

        Object sync = new Object();

        Arrays.fill(wordLoc, -1);

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

        mainThreads.add(new MainThread(firstWord, secondWord, "T1", 0, sync));
        mainThreads.add(new MainThread(secondWord, firstWord, "T2", 1, sync));

        currentThreads.set(2);
        mainThreads.get(0).start();
        mainThreads.get(1).start();

        for (Thread thread : mainThreads) {
            thread.join();
        }

    }

    public static class MainThread extends Thread
    {
        private String firstWord;
        private String secondWord;
        private String threadName;
        private int loc;
        private Object x;
        private ConcurrentHashMap<String, List<String>> EditNeighborsLoc = new ConcurrentHashMap<>();

        MainThread(String firstWord, String secondWord, String threadName, int loc, Object x) {
            this.firstWord = firstWord;
            this.secondWord = secondWord;
            this.threadName = threadName;
            this.loc = loc;
            this.x = x;
        }

        public void run() {
            try {

            } catch (Exception e) {
                System.out.println(threadName + " error: " + e.getCause() + " :: " + e);
            }
        }

        private void pathForward() {

        }

        private void findNeighbors(String target) {
            ArrayList<String> neighbors = new ArrayList<>();

            for (int p = binarySearchFirstLength(target.substring(1)); p < words.size(); p++) {
                String temp = words.get(p);
                if (temp.length() > target.length() + 1) {
                    break;
                } else if (isEditDistance(temp, target)){
                    neighbors.add(temp);
                }
            }
            EditNeighborsLoc.put(target, neighbors);
        }

        private void createHelperThread(String target) {
            if (currentThreads.getAndIncrement() < MAX_THREADS) {
                HelperThread temp = new HelperThread(threadName + "-1", target, mainThreads.get(loc));
                temp.start();
            }
        }

        public void addMapping(HashMap<String, List<String>> in) {
            EditNeighborsLoc.putAll(in);
        }

        public ConcurrentHashMap<String, List<String>> getMap() {
            return EditNeighborsLoc;
        }
    }

    public static class HelperThread extends Thread
    {
        private String threadName;
        private String target;
        private MainThread parentThread;
        private HashMap<String, List<String>> EditNeighborsLoc = new HashMap<>();

        HelperThread(String threadName, String target, MainThread parentThread) {
            this.threadName = threadName;
            this.target = target;
            this.parentThread = parentThread;
        }

        public void run() {
            try {
                ArrayList<String> neighbors = new ArrayList<>();

                for (int p = binarySearchFirstLength(target.substring(1)); p < words.size(); p++) {
                    String temp = words.get(p);
                    if (temp.length() > target.length() + 1) {
                        break;
                    } else if (isEditDistance(temp, target)) {
                        neighbors.add(temp);
                    }
                }
                EditNeighborsLoc.put(target, neighbors);
                parentThread.addMapping(EditNeighborsLoc);
            } catch (Exception e) {
                System.out.println(threadName + " error: " + e.getCause() + " :: " + e);
            }
        }
    }
}