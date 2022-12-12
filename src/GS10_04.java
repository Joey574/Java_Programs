import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GS10_04 {

/*
Author: Joey Soroka
Problem:
Purpose:
Pseudocode:
Maintenance Log:
 */

    static ArrayList<String> merge (ArrayList<String> left, ArrayList<String> right)
    {
        System.out.println("merging: " + left + " and " + right);

        ArrayList<String> out = new ArrayList<String>(left);
        out.addAll(right);

        out.sort(String.CASE_INSENSITIVE_ORDER);

        return out;
    }

    static ArrayList<String> mergeSortIgnoreCase(ArrayList<String> in)
    {
        if (in.size() > 1)
        {
            System.out.println("sorting: " + in);

            ArrayList<String> left = new ArrayList<String>(in.subList(0, in.size() / 2));
            ArrayList<String> right = new ArrayList<String>(in.subList(in.size() / 2, in.size()));

            left = mergeSortIgnoreCase(left);
            right = mergeSortIgnoreCase(right);

            in = merge(left, right);
        }

        return in;

    }

    public static void main(String[] args) throws IOException {
        String fileName = "GS10_04_File.txt";

        FileReader fr = new FileReader(fileName);
        Scanner lineScanner = new Scanner(fr);

        ArrayList<String> lines = new ArrayList<String>();

        while (lineScanner.hasNextLine())
        {
            lines.add(lineScanner.nextLine());
        }

        System.out.println("Input: " + lines);

        lines = mergeSortIgnoreCase(lines);

        System.out.print("Output: " + lines);

        fr.close();
    }
}
