import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

public class DataSaver {

    public static void main(String[] args) {
        Scanner pipe = new Scanner(System.in);

        ArrayList<String> records = new ArrayList<>();

        while (true) {
            String firstName = SafeInput.getNonZeroLenString(pipe, "Enter First Name");
            String lastName = SafeInput.getNonZeroLenString(pipe, "Enter Last Name");
            int idNumber = SafeInput.getInt(pipe, "Enter ID Number (6-digit, e.g., 000001)");
            String email = SafeInput.getNonZeroLenString(pipe, "Enter Email");
            int yearOfBirth = SafeInput.getInt(pipe, "Enter Year of Birth (e.g., 1978)");

            String idFormatted = String.format("%06d", idNumber);

            String csvRecord = String.format("%s, %s, %s, %s, %d", firstName, lastName, idFormatted, email, yearOfBirth);
            records.add(csvRecord);

            boolean anotherRecord = SafeInput.getYNConfirm(pipe, "Would you like to enter another record? (y/n)");
            if (!anotherRecord) {
                break;
            }
        }

        String fileName = SafeInput.getNonZeroLenString(pipe, "Enter the file name to save records (with .csv extension)");

        if (!fileName.endsWith(".csv")) {
            fileName += ".csv";
        }

        File file = new File("src/" + fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String record : records) {
                writer.write(record);
                writer.newLine();
            }
            System.out.println("Data has been saved to " + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }

        pipe.close();
    }
}