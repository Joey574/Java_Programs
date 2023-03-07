import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static java.lang.Thread.sleep;

public class GS14_01_Helper
{

/*
Author: Joey Soroka
Problem:
Purpose:
Pseudocode:
Maintenance Log:
 */

    static int THREAD_NUM = 16;
    static int threadsComplete = 0;
    static long beginTime;
    static LinkedList<String> words = new LinkedList<>();
    static HashMap<String, List<String>> EditNeighbors = new HashMap<String, List<String>>();

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

                    System.out.println(threadName + target);

                    String x = words.get(target);

                    ArrayList<String> neighbors = new ArrayList<String>();
                    for (int t = 0; t < words.size(); t++) {
                        String temp = words.get(t);
                        if (Math.abs(temp.length() - x.length()) < 2) {
                            if (isEditDistance(temp, x)) {
                                neighbors.add(temp);
                            }
                        } else if (temp.length() > x.length() + 1){
                            break;
                        }
                    }
                    EditNeighbors.put(x, neighbors);
                    }
                System.out.println(threadName + " total time elapsed to create map (ms): " + (System.currentTimeMillis() - beginTime));
            } catch (Exception e) {
                System.out.println(threadName + " error");
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

        beginTime = System.currentTimeMillis();

        sync o = new sync();

        o.setMapTarget(0);
        System.out.println("Binary search time (ms): " + (System.currentTimeMillis() - beginTime));

        for (int i = 0; i < THREAD_NUM; i++) {
            String name = "Thread " + i + ": ";
            mapThread temp = new mapThread(name, o);
            temp.start();
        }
        while(threadsComplete != THREAD_NUM) {
            sleep(1);
        }

        fileName = "EditNeighbors.txt";

        FileWriter fw = new FileWriter(fileName);

        Set<String> keysS = EditNeighbors.keySet();

        ArrayList<String> keys = new ArrayList<>(keysS);

        for (int i = 0; i < keys.size(); i++) {
            fw.write(keys.get(i) + ": " + EditNeighbors.get(keys.get(i)) + "\n");
        }

        fw.close();

    }
}