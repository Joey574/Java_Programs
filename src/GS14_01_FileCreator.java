import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static java.lang.Thread.sleep;

public class GS14_01_FileCreator
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

    static int threadsComplete = 0;
    static long beginTime;
    static List<String> words = new ArrayList<>();
    static HashMap<String, List<String>> EditNeighbors = new HashMap<String, List<String>>();
    static int [] wordLoc = new int[31];

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

    static class mapThread extends Thread {
        private final String threadName;
        private final HashMap<String, List<String>> EditNeighborsLoc = new HashMap<String, List<String>>();
        private Thread t;
        private int threadID;
        final sync obj;

        mapThread(String name, int id, sync o) {
            threadName = name;
            threadID = id;
            obj = o;
        }

        public void run() {
            System.out.println("Running " + threadName);

            int target = 0;
            ArrayList<String> neighbors;
            try {
                for (int i = 0; i < words.size(); i++) {

                    synchronized (obj) {
                        target = obj.getTarget();
                    }

                    String x = words.get(target);

                    neighbors = new ArrayList<String>();

                    for (int p = binarySearchFirstLength(x, 1); p < words.size(); p++) {
                        String temp = words.get(p);
                        if (temp.length() > x.length() + 1) {
                            break;
                        } else if (Math.abs(temp.length() - x.length()) < 2) {
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
            threadsComplete++;
        }

        public HashMap<String, List<String>> getEditNeighborsLoc()
        {
            return EditNeighborsLoc;
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        String fileName = "dictionarySortedLength.txt";
        //String fileName = "dictionaryMonkeyBusiness.txt";

        Arrays.fill(wordLoc, -1);

        FileReader fr = new FileReader(fileName);
        Scanner lineScanner = new Scanner(fr);

        while (lineScanner.hasNextLine()) {
            words.add(lineScanner.nextLine());
        }

        fr.close();
        System.out.println("File closed");

        Scanner r = new Scanner(System.in);

        beginTime = System.currentTimeMillis();

        sync o = new sync();

        o.setMapTarget(0);

        System.out.println("Binary search time (ms): " + (System.currentTimeMillis() - beginTime));

        ArrayList<mapThread> threads = new ArrayList<>();

        for (int i = 0; i < THREAD_NUM; i++) {
            String name = "Thread " + i + ": ";
            mapThread temp = new mapThread(name, i, o);
            threads.add(temp);
            temp.start();
        }

        while (threadsComplete != THREAD_NUM) {
            sleep(1);
        }

        for (int i = 0; i < THREAD_NUM; i++) {
            EditNeighbors.putAll(threads.get(i).getEditNeighborsLoc());
        }

        System.out.println("Total time elapsed to create map (ms): " + (System.currentTimeMillis() - beginTime));

        System.out.println("Map size: " + EditNeighbors.size());

    }
}

