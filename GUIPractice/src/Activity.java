import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Activity {
    public List<String> handleRequest(Character c) {
        List<String> lines = loadFile(new File("./adjectives.txt"));
        if (c == '/') {
            return lines;
        }
        List<String> temp = new LinkedList<>();
        for (String s : lines) {
            if (s.charAt(0) == c) {
                temp.add(s);
            }
        }
        return temp;
    }

    public List<String> loadFile(File file) {
        List<String> fileLines = new LinkedList<>();
        try {
            Scanner scanner = new Scanner(file);
            while(scanner.hasNext()) {
                fileLines.add(scanner.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fileLines;
    }
}
