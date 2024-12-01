import javax.swing.*;
import java.io.*;
import java.util.*;

public class FileInspector {
    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser();

        String projectPath = System.getProperty("user.dir") + File.separator + "src";
        fileChooser.setCurrentDirectory(new File(projectPath));

        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue != JFileChooser.APPROVE_OPTION) {
            System.out.println("No file selected, exiting program.");
            return;
        }

        File selectedFile = fileChooser.getSelectedFile();
        System.out.println("Selected file: " + selectedFile.getAbsolutePath());


        int lineCount = 0;
        int wordCount = 0;
        int charCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                lineCount++;
                charCount += line.length();

                String[] words = line.split("\\s+");
                wordCount += words.length;


                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }

        System.out.println("\nSummary Report:");
        System.out.println("File name: " + selectedFile.getName());
        System.out.println("Number of lines: " + lineCount);
        System.out.println("Number of words: " + wordCount);
        System.out.println("Number of characters: " + charCount);
    }
}