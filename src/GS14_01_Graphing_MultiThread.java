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
    static AtomicInteger currentThreads = new AtomicInteger();
    static String firstWord;
    static String secondWord;
    static String smallestWord;
    static String biggestWord;
    static long beginTime;
    static ArrayList<String> words = new ArrayList<>();
    static int [] wordLoc = new int[32];
    static ArrayList<MainThread> mainThreads = new ArrayList<>();
    static CoordinatorThread coordinatorThread;
    static List<String> mainPath = new ArrayList<>();
    static boolean threadsComplete;

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
        int diff = 0;
        int idx = 0;

        while (idx < i1.length() && idx < i2.length()) {
            if (i1.charAt(idx) != i2.charAt(idx)) {
                diff++;
            }
            idx++;
        }

        if (idx < i1.length()) {
            diff += i1.length() - idx;
        } else if (idx < i2.length()) {
            diff += i2.length() - idx;
        }

        return diff;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        String fileName = "dictionarySortedLength.txt";
        //String fileName = "dictionaryMonkeyBusiness.txt";

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

        mainThreads.add(new MainThread(firstWord, secondWord, "T1", 0, 1));
        mainThreads.add(new MainThread(secondWord, firstWord, "T2", 1, 0));
        coordinatorThread = new CoordinatorThread();

        currentThreads.set(3);
        mainThreads.get(0).start();
        mainThreads.get(1).start();
        coordinatorThread.start();

        coordinatorThread.join();
        System.out.println("Main: Coordinator thread joined");

        if (mainPath.size() > 0) {
            System.out.println("Path found: " + mainPath);
        } else {
            System.out.println("No path found");
        }

        System.out.println("Total time elapsed (ms): " + (System.currentTimeMillis() - beginTime));
        System.out.println("T1: Map Size - " + mainThreads.get(0).getMap().size());
        System.out.println("T2: Map Size - " + mainThreads.get(1).getMap().size());

    }

    public static class CoordinatorThread extends Thread
    {
        private LinkedList<String> pathOne = new LinkedList<>();
        private LinkedList<String> pathTwo = new LinkedList<>();
        private String threadName = "Coordinator";
        public void run() {
            System.out.println(threadName + ": Running");
            while(true) {
                if (threadsComplete) { // change to wait / notify methods later

                    pathOne = mainThreads.get(0).getPath();
                    pathTwo = mainThreads.get(1).getPath();

                    System.out.println(threadName + ": Path found");
                    System.out.println("PathOne: " + pathOne);
                    System.out.println("PathTwo: " + pathTwo);

                    if (pathOne.contains(firstWord) && pathOne.contains(secondWord)) {
                        mainPath.addAll(pathOne);
                        System.out.println(threadName + ": T2 did not merge");
                    } else if (pathTwo.contains(firstWord) && pathTwo.contains(secondWord)) {
                        mainPath.addAll(pathTwo);
                        System.out.println(threadName + ": T1 did not merge");
                    } else {
                        System.out.println(threadName + ": Successful merge");
                        mainPath.addAll(pathOne);
                        mainPath.removeIf((v) -> pathTwo.contains(v));
                        for (int x = pathTwo.size() - 1; x > -1; x--) {
                            mainPath.add(pathTwo.get(x));
                        }
                    }

                    System.out.println(threadName + ": Complete");
                    break;
                }
            }
        }
    }

    public static class MainThread extends Thread
    {
        private String firstWordT;
        private String secondWordT;
        private String threadName;
        private int loc;
        private int otherLoc;
        private int helperNum = 0;
        private ConcurrentHashMap<String, List<String>> EditNeighborsLoc = new ConcurrentHashMap<>();
        private List<String> path = new ArrayList<>();

        MainThread(String firstWord, String secondWord, String threadName, int loc, int otherLoc) {
            this.firstWordT = firstWord;
            this.secondWordT = secondWord;
            this.threadName = threadName;
            this.loc = loc;
            this.otherLoc = otherLoc;
        }

        public void run() {
            try {
                System.out.println(threadName + ": Running");
                boolean failed = false;
                String target = firstWordT;
                HashSet<String> examined = new HashSet<String>();
                while (!target.equals(secondWordT) && !failed && !threadsComplete) { // looping until either path found or nowhere left to go
                    findNeighbors(target);
                    ArrayList<String> values = new ArrayList<String>(EditNeighborsLoc.get(target));
                    ArrayList<String> pValues = new ArrayList<>();
                    ArrayList<Integer> difs;

                    if (examined.contains(target)) { // double failsafe
                        if (path.size() < 2) { // nowhere else to go
                            failed = true;
                            System.out.println(threadName + ": Failed");
                        } else {
                            path.remove(path.size() - 1);
                            target = path.get(path.size() - 1);
                        }
                    }

                    path.add(target);

                    for (int p = 0; p < values.size(); p++) { // checking for possible values we can use
                        if (!Algorithms.containsString(values.get(p), path) && !examined.contains(values.get(p))) {
                            pValues.add(values.get(p));
                            if (mainThreads.get(otherLoc).getPath().contains(values.get(p))) { // word already pathed by other thread
                                System.out.println(threadName + ": Word found in other path");
                                threadsComplete = true;
                                break;
                            }
                        }
                    }

                    if (pValues.size() > 0) { // haven't made it to end of path yet

                        difs = new ArrayList<>();

                        for (int x = 0; x < pValues.size(); x++) { // assigning letter differences to each possible value
                            difs.add(letterDifference(pValues.get(x), secondWordT));
                        }

                        int smallestDifLoc = 0;
                        int smallestDif = difs.get(0);

                        for (int i = 0; i < difs.size(); i++) { // looking for smallest dif value
                            if (difs.get(i) < smallestDif) {
                                smallestDif = difs.get(i);
                                smallestDifLoc = i;
                            }
                        }

                        target = pValues.get(smallestDifLoc);

                        if (pValues.size() > 1) { // branch in options
                            difs.set(smallestDifLoc, 999); // change smallest value so it isn't chosen again

                            for (int i = 0; i < difs.size(); i++) { // looking for second smallest dif value
                                if (difs.get(i) < smallestDif) {
                                    smallestDif = difs.get(i);
                                    smallestDifLoc = i;
                                }
                            }

                            createHelperThread(pValues.get(smallestDifLoc));

                        }

                        if (secondWordT.equals(target)) { // word found
                            path.add(target);
                        }
                    } else { // no possible values found
                        examined.add(target);
                        if (path.size() < 2) { // nowhere else to go
                            failed = true;
                            System.out.println(threadName + ": Failed");
                        } else {
                            path.remove(path.size() - 1);
                            target = path.get(path.size() - 1);
                        }
                    }
                }
                System.out.println(threadName + ": Complete");
            } catch (Exception e) {
                System.out.println(threadName + " error: " + e.getCause() + " :: " + e);
            }
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
            if (currentThreads.get() < MAX_THREADS) {
                System.out.println(threadName + ": Creating helper");
                currentThreads.incrementAndGet();
                HelperThread temp = new HelperThread(threadName + "-" + helperNum, target, mainThreads.get(loc));
                helperNum++;
                temp.start();
            }
        }

        public void addMapping(HashMap<String, List<String>> in) {
            EditNeighborsLoc.putAll(in);
        }

        public ConcurrentHashMap<String, List<String>> getMap() {
            return EditNeighborsLoc;
        }
        public List<String> getPath() {
            return path;
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
                System.out.println(threadName + ": Running");
                ArrayList<String> neighbors = new ArrayList<>();
                    for (int p = binarySearchFirstLength(target.substring(1)); words.get(p).length() < target.length() + 1; p++) {
                        String temp = words.get(p);
                    if (isEditDistance(temp, target)) {
                        neighbors.add(temp);
                    }
                }
                EditNeighborsLoc.put(target, neighbors);
                parentThread.addMapping(EditNeighborsLoc);
            } catch (Exception e) {
                System.out.println(threadName + " error: " + e.getCause() + " :: " + e);
            }
            currentThreads.getAndDecrement();
            System.out.println(threadName + ": Complete");
        }
    }
}
