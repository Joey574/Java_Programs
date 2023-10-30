import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class groupProjectStuff {

    static ArrayList<Double> ints = new ArrayList<>();
    public static void main(String[] args) {

        long iterations = 1000000000;

        ints.add(0.0);
        ints.add(0.0);
        ints.add(0.0);
        ints.add(0.0);
        ints.add(0.0);
        ints.add(0.0);

        Random rn = new Random(0);

        for (long i = 0; i < iterations; i++) {
            int num = rn.nextInt(6) + 1;
            ints.set(num - 1, ints.get(num - 1) + 1);
        }

        System.out.println("Count: " + ints);
    }
}