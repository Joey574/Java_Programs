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
    
    static int THREAD_NUM = 2;

    static int threadsComplete = 0;
    static int smallBuffer = 0;
    static int bigBuffer = 0;
    static long beginTime;
    static String firstWord;
    static String secondWord;
    static String smallestWord;
    static String biggestWord;
    static LinkedList<String> words = new LinkedList<>();
    static HashMap<String, List<String>> EditNeighbors = new HashMap<String, List<String>>();

    public static boolean isEditDistance (String in1, String in2) {
        return letterDifference(in1, in2) < 2 && !in1.equals(in2);
    }

    public static int letterDifference(String i1, String i2) {
        int x = 0;
        int y = 0;
        int out = 0;

        String bWord;
        String sWord;

        if (i1.length() > i2.length()) {
            bWord = i1;
            sWord = i2;
        } else {
            bWord = i2;
            sWord = i1;
        }

        for (int i = 0; i < sWord.length(); i++) {
            if (bWord.charAt(x) != sWord.charAt(y)) {
                out++;
                x++;
            } else {
                x++;
                y++;
            }
        }

        return out;
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
    private final int threadID;
    private Thread t;
    final sync obj;

    mapThread(String name, int num, sync o) {
        threadName = name;
        threadID = num;
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

                for (String temp : words) {
                    if (Math.abs(temp.length() - x.length()) < 2) {
                        if (isEditDistance(temp, x)) {
                            neighbors.add(temp);
                        }
                    } else if (temp.length() > x.length() + bigBuffer){
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
        //String fileName = "dictionarySortedLength.txt";
        String fileName = "dictionaryMonkeyBusiness.txt";

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
        
        int smallestTargetLengthLoc = Algorithms.binarySearchFirstLength(words, smallestWord, smallBuffer);
        o.setMapTarget(smallestTargetLengthLoc);
        System.out.println("Binary search time (ms): " + (System.currentTimeMillis() - beginTime));

        for (int i = 0; i < THREAD_NUM; i++) {
            String name = "Thread " + i + ": ";
            mapThread temp = new mapThread(name, i, o);
            temp.start();
        }
        while(threadsComplete != THREAD_NUM) {
            sleep(1);
        }

        EditNeighbors.forEach((k, v) -> { // remove elements from values that aren't mapped to avoid null pointer errors
            for (int i = 0; i < v.size(); i++) {
                v.removeIf((e) -> {
                    return !EditNeighbors.containsKey(e);
                });
                EditNeighbors.put(k, v);
            }
        });

        System.out.println("Map size: " + EditNeighbors.size());

        long pathTime = System.currentTimeMillis();
        
        if (EditNeighbors.containsKey(firstWord) && EditNeighbors.containsKey(secondWord)) { // if words are present in map
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
