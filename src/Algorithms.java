import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

public class Algorithms
{
    public static int binarySearchStringArray(ArrayList<String> in, String target) {
        boolean complete = false;

        int out = -1;
        int start = 0;
        int end = in.size();

        for (int i = 0; !complete; i++) {
            int loc = (start + end) / 2;
            if (Objects.equals(in.get(loc), target)) {
                complete = true;
                out = loc;
            } else if (in.get(loc).compareTo(target) > 0) {
                end = loc;
            } else if (in.get(loc).compareTo(target) < 0) {
                start = loc;
            }
            if (start > end || start == end) {
                complete = true;
                out = -loc;
            }
        }
        return out;
    }

    public static int binarySearchFirstLength(LinkedList<String> in, String target, int smallBuffer) {
        boolean complete = false;

        int out = -1;
        int start = 0;
        int end = in.size();

        for (int i = 0; !complete; i++) {
            int loc = (start + end) / 2;
            if (in.get(loc).length() == target.length()) {
                complete = true;
                out = loc;
            } else if (in.get(loc).length() > target.length()) {
                end = loc;
            } else if (in.get(loc).length() < target.length()) {
                start = loc;
            }
            if (start > end || start == end) {
                complete = true;
                out = -loc;
            }
        }

        while(in.get(out).length() >= target.length() - smallBuffer) {
            if (out > 30) {
                out -= 30;
            } else {
                out--;
            }
        }
        for (int i = 0; in.get(out).length() != target.length() - smallBuffer; out++) {}

        return out;
    }



}
