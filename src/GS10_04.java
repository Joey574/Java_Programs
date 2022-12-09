import java.io.FileNotFoundException;
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

    public static void main(String[] args) throws IOException {
        String fileName = "GS10_04_File.txt";

        FileReader fr = new FileReader(fileName);
        Scanner lineScanner = new Scanner(fr);

        ArrayList<String> temp = new ArrayList<String>();

        while (lineScanner.hasNextLine())
        {

        }

        fr.close();
    }
}
