import java.io.FileReader;
import java.io.FileWriter;
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

    static int THREAD_NUM = 16;

    static int threadsComplete = 0;
    static int startLoc;
    static int startDif;
    static int smallestDifFound;
    static int smallBuffer = 0;
    static int bigBuffer = 0;
    static long beginTime;
    static String firstWord;
    static String secondWord;
    static String smallestWord;
    static String biggestWord;
    static LinkedList<String> words = new LinkedList<>();
    static HashMap<String, List<String>> EditNeighbors = new HashMap<String, List<String>>();

    public static int binarySearchFirstLength(String target, int smallBuffer) {
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

        return out;
    }

    public static boolean isEditDistance (String in1, String in2) {
        int m = in1.length(), n = in2.length();

        if (Math.abs(m - n) > 1)
            return false;

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

    public static class sync {
        static private int mapTarget = 0;

        static int getTarget() {
            mapTarget++;
            return mapTarget - 1;
        }

        static void setMapTarget(int i) {
            mapTarget = i;
        }
    }

    static class mapThread implements Runnable {
        private final String threadName;
        private Thread t;
        final sync obj;

        mapThread(String name, sync o) {
            threadName = name;
            obj = o;
        }

        public void run() {
            System.out.println("Running " + threadName);
            try {
                int target;

                for (int i = 0; i < words.size(); i++) {

                    synchronized (obj) {
                        target = obj.getTarget();
                    }

                    String x = words.get(target);

                    if (x.length() > biggestWord.length() + bigBuffer) {
                        break;
                    }

                    ArrayList<String> neighbors = new ArrayList<String>();
                    for (int t = binarySearchFirstLength(x, 1); t < words.size(); t++) {
                        String temp = words.get(t);
                        if (Math.abs(temp.length() - x.length()) < 2) {
                            if (!EditNeighbors.containsKey(x) || !EditNeighbors.get(x).contains(temp)) {
                                if (isEditDistance(temp, x)) {
                                    neighbors.add(temp);
                                    if (EditNeighbors.containsKey(temp)) {
                                        List<String> tempL = EditNeighbors.get(temp);
                                        tempL.add(temp);
                                        EditNeighbors.put(temp, tempL);
                                    } else {
                                        List<String> tempVal = new ArrayList<>(List.of(x));
                                        EditNeighbors.put(temp, tempVal);
                                    }
                                }
                            }
                        } else if (temp.length() > x.length() + 1){
                            break;
                        }
                    }
                    EditNeighbors.put(x, neighbors);
                }
                System.out.println(threadName + " total time elapsed to create map (ms): " + (System.currentTimeMillis() - beginTime));
            } catch (Exception e) {
                System.out.println(threadName + " Error: " + e.getCause());
            }
            System.out.println(threadName + " complete");
            threadsComplete++;
        }

        public void start() {
            if (t == null) {
                t = new Thread(this, threadName);
                t.start();
            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        String fileName = "dictionarySortedLength.txt";
        //String fileName = "dictionaryMonkeyBusiness.txt";

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

           sync o = new sync();

           int smallestTargetLengthLoc = binarySearchFirstLength(smallestWord, smallBuffer);
           startLoc = binarySearchFirstLength(smallestWord, 1);
           o.setMapTarget(smallestTargetLengthLoc);

           System.out.println("Binary search time (ms): " + (System.currentTimeMillis() - beginTime));

           startDif = letterDifference(firstWord, secondWord);
           smallestDifFound = startDif;

           for (int i = 0; i < THREAD_NUM; i++) {
               String name = "Thread " + i + ": ";
               mapThread temp = new mapThread(name, o);
               temp.start();
           }

           while(threadsComplete != THREAD_NUM) {
               sleep(1);
           }
           EditNeighbors.forEach((k, v) -> { // remove elements from values that aren't mapped to avoid null pointer errors
               for (int i = 0; i < v.size(); i++) {
                   v.removeIf((e) -> { // i still don't understand -> like why does the code need directions? just like look over there yourself
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

                //System.out.println("Examined: " + examined.size());
                //System.out.println("Path: " + path.size());
                //System.out.println("Target: " + target);
                //System.out.println("Values: " + values.size());

                System.out.println("Examined: " + examined);
                System.out.println("Path: " + path);
                System.out.println("Target: " + target);
                System.out.println("Values: " + values);

                for (int p = 0; p < values.size(); p++) { // checking for possible values we can use
                    if (!Algorithms.containsString(values.get(p), path) && !examined.contains(values.get(p))) {
                        pValues.add(values.get(p));
                    }
                }

                //System.out.println("Possible values: " + pValues.size());
                System.out.println("Possible values: " + pValues);

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
                System.out.println("New target: " + target);
            }

            if (!complete) {
                System.out.println("Path found: " + path);
            } else {
                System.out.println("No path found: " + path);
            }

            System.out.println("Total time elapsed to find path (ms): " + (System.currentTimeMillis() - pathTime));
            System.out.println("Total time elapsed (ms): " + (System.currentTimeMillis() - beginTime));

        } else {
            throw new RuntimeException("Word not found in map and or dictionary");
        }
    }
}