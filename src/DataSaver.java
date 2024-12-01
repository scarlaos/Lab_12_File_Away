import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class DataSaver {
    public static void main(String[] args) {

        JFileChooser chooser = new JFileChooser();
        ArrayList<String> lines = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        boolean moreRecords = false;

        final int FIELDS_LENGTH = 5;
        String firstName, lastName, email, yearOfBirth, idNumber;

        while (!moreRecords) {
            System.out.print("Enter First Name: ");
            firstName = in.nextLine();

            System.out.print("Enter Last Name: ");
            lastName = in.nextLine();

            System.out.print("Enter ID Number (6 digits): ");
            idNumber = in.nextLine();
            idNumber = String.format("%06d", Integer.parseInt(idNumber));

            System.out.print("Enter Email: ");
            email = in.nextLine();

            System.out.print("Enter Year of Birth: ");
            yearOfBirth = in.nextLine();

            String record = firstName + ", " + lastName + ", " + idNumber + ", " + email + ", " + yearOfBirth;
            lines.add(record);

            System.out.print("Do you want to add another record? (y/n): ");
            String response = in.nextLine().toLowerCase();
            moreRecords = response.equals("n");
        }

        System.out.print("Enter the file name (with .csv extension): ");
        String fileName = in.nextLine();

        try {
            chooser.setDialogTitle("Select where to save the file");
            chooser.setSelectedFile(new File(fileName));
            int userSelection = chooser.showSaveDialog(null);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File selectedFile = chooser.getSelectedFile();
                Path filePath = selectedFile.toPath();

                try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
                    for (String record : lines) {
                        writer.write(record);
                        writer.newLine();
                    }
                    System.out.println("Data has been saved successfully to " + selectedFile.getAbsolutePath());
                }

            } else {
                System.out.println("File save operation canceled.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving the file.");
            e.printStackTrace();
        }
    }
}
