import java.io.FileReader;
import java.io.IOException;
import java.util.*;



class mapThread implements Runnable {
    private String threadName;
    private int threadNum;
    private Thread t;

    mapThread(String name, int num) {
        threadName = name;
        threadNum = num;
        System.out.println("Creating" + threadName);
    }

    public void run() {
        System.out.println("Running " + threadName);
        try {

        } catch (InterruptedException e) {
            System.out.println("Thread " + threadName + " error");
        }
        System.out.println("Thread " + threadName + " complete");
    }

    public void start() {
        System.out.println("Starting " + threadName);
        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }
}

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
        long mapTime = System.currentTimeMillis();
        System.out.println("Total time elapsed to create map (ms): " + (System.currentTimeMillis() - beginTime));

        if (EditNeighbors.containsKey(firstWord) && EditNeighbors.containsKey(secondWord)) {
            ArrayList<String> path = new ArrayList<String>();
            Set<String> examined = new HashSet<String>();
            String target = firstWord;
            boolean complete = false;
            int temp = 0;

            for (int i = 0; !target.equals(secondWord) && !complete; i++) {
                ArrayList<String> values = new ArrayList<String>(EditNeighbors.get(target));
                int smallestDif = letterDifference(target, secondWord);
                int smallestDifLoc = -1;
                temp = -1;
                path.add(target);

                for (String value : values) {
                    if (!Algorithms.containsString(value, path) && !examined.contains(value)) {
                        temp = 1;
                        break;
                    }
                }

                System.out.println("Examined: " + examined);
                System.out.println("Path: " + path);
                System.out.println("Target: " + target);

                if (temp == -1) {
                    examined.add(target);
                    if (path.size() < 2) {
                        complete = true;
                    } else {
                        String finalTarget = target;
                        path.removeIf(finalTarget::equals);
                        target = path.get(path.size() - 1);
                        continue;
                    }
                }

                for (int x = 0; x < values.size(); x++) {
                    if (letterDifference(values.get(x), secondWord) < smallestDif && !Algorithms.containsString(values.get(x), path) && !examined.contains(values.get(x))) {
                        smallestDif = letterDifference(values.get(x), secondWord);
                        smallestDifLoc = x;
                    }
                }

                if (smallestDifLoc == -1) {
                    for (int y = 0; y < values.size(); y++) {
                        if (!Algorithms.containsString(values.get(y), path) && !examined.contains(values.get(y))) {
                            smallestDif = letterDifference(values.get(y), secondWord);
                            smallestDifLoc = y;
                        }
                    }

                    if (smallestDifLoc == -1) {
                        examined.add(target);
                        if (path.size() < 2) {
                            complete = true;
                        } else {
                            target = path.get(path.size() - 2);
                            path.remove(path.size() - 1);
                        }
                    } else {
                        for (int q = 0; q < values.size(); q++) {
                            if (letterDifference(values.get(q), secondWord) < smallestDif && !Algorithms.containsString(values.get(q), path) && !examined.contains(values.get(q))) {
                                smallestDif = letterDifference(values.get(q), secondWord);
                                smallestDifLoc = q;
                            }
                        }
                    }
                }

                if (smallestDifLoc != -1) {
                    target = values.get(smallestDifLoc);
                    if (target.equals(secondWord)) {
                        path.add(target);
                    }
                }
            }

            if (!complete) {
                System.out.println("Path found: " + path);
            } else {
                System.out.println("No path found: " + path);
            }

            System.out.println("Total time elapsed to find path (ms): " + (System.currentTimeMillis() - mapTime));
            System.out.println("Total time elapsed (ms): " + (System.currentTimeMillis() - beginTime));

        } else {
            throw new RuntimeException("Word not found in map and or dictionary");
        }
    }
}
