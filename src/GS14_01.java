import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static java.lang.Thread.sleep;

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

    public static int getTarget()
    {
        mapTarget++;
        return mapTarget - 1;
    }

    static class mapThread extends Thread {
        private final String threadName;
        private final HashMap<String, List<String>> EditNeighborsLoc = new HashMap<String, List<String>>();
        private Thread t;
        final Object obj;

        mapThread(String name, Object o) {
            threadName = name;
            obj = o;
        }

        public void run() {
            System.out.println(threadName + "Running");

            int target = 0;
            ArrayList<String> neighbors;
            try {
                for (int i = 0; i < words.size(); i++) {

                    synchronized (obj) {
                        target = getTarget();
                    }

                    String x = words.get(target);

                    if (x.length() > biggestWord.length() + bigBuffer) {
                        break;
                    }

                    neighbors = new ArrayList<String>();

                    for (int p = binarySearchFirstLength((x.substring(1))); p < words.size(); p++) {
                        String temp = words.get(p);
                        if (temp.length() > x.length() + 1) {
                            break;
                        } else {
                            if (isEditDistance(temp, x)) {
                                neighbors.add(temp);
                            }
                        }
                    }
                    EditNeighborsLoc.put(x, neighbors);
                }
            } catch (Exception e) {
                System.out.println(threadName + " Error: " + e.getCause() + " :: " + e);
            }
            System.out.println(threadName + " complete");
        }

        public HashMap<String, List<String>> getEditNeighborsLoc()
        {
            return EditNeighborsLoc;
        }
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

        beginTime = System.currentTimeMillis();

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

        mapTarget = binarySearchFirstLength(smallestWord.substring(smallBuffer));

        System.out.println("Binary search time (ms): " + (System.currentTimeMillis() - beginTime));

        ArrayList<mapThread> threads = new ArrayList<>();

        for (int i = 0; i < THREAD_NUM; i++) {
            String name = "Thread " + i + ": ";
            mapThread temp = new mapThread(name, sync);
            threads.add(temp);
            temp.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        for (int i = 0; i < THREAD_NUM; i++) {
            EditNeighbors.putAll(threads.get(i).getEditNeighborsLoc());
        }

        System.out.println("Total time elapsed to create map (ms): " + (System.currentTimeMillis() - beginTime));

        EditNeighbors.forEach((k, v) -> { // remove elements from values that aren't mapped to avoid null pointer errors
        for (int i = 0; i < v.size(); i++) {
            v.removeIf((e) -> { // I still don't understand -> like why does the code need directions? just like look over there yourself
                return !EditNeighbors.containsKey(e);
            });
            EditNeighbors.put(k, v);
            }
        });

        System.out.println("Map size: " + EditNeighbors.size());

        long pathTime = System.currentTimeMillis();

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

            System.out.println("Total time elapsed to find path (ms): " + (System.currentTimeMillis() - pathTime));
            System.out.println("Total time elapsed (ms): " + (System.currentTimeMillis() - beginTime));
            System.out.println("Examined size: " + examined.size());
        } else {
            throw new RuntimeException("Word not found in map and or dictionary");
        }
    }
}
