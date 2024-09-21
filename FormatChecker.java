import java.io.*;
import java.util.*;

/** 
 * Take in a list of filenames and print their name as well as any exceptions encountered and if they
 * are formatted as described by the formatCheck() method. Files should have 2 integers in the header
 * for the rows and columns expected. The following lines should have the exact number of double values
 * matching the header rows/columns. 
 * 
 * @author Nathan Marquis
 */

public class FormatChecker {

    /**
     * Serve 3 primary functions:
     * 1) Attempt to open given file
     * 2) Parse the contents of said file line by line
     * 3) Check for proper formatting: 
     *      a) That the first line has 2 integers for the number of rows and columns expected.
     *      b) That there are the exact number of rows and columns described in the first line
     *      c) That each value after the first line is a double type
     * 
     * @param filename a String with the name of the file to be opened
     * 
     * @return a true boolean if the file is properly formatted, else return false
     * 
     * @throws FileNotFoundException if the file does not exist
     * @throws NumberFormatException if a number is assigned to the wrong type
     * @throws Exception if there is a formatting issue; will also throw all other exceptions
     */
    private static boolean formatCheck(String filename) throws FileNotFoundException, NumberFormatException, Exception {
        boolean valid = false;
        File file = new File(filename);
        Scanner scnr = new Scanner(file);
        String[] header = scnr.nextLine().split("\\s+");

        if (header.length != 2) {
            scnr.close();
            throw new Exception("Incorrect number of arguments in first line");
        }

        int rows = Integer.parseInt(header[0]);
        int columns = Integer.parseInt(header[1]);
        double[][] matrix = new double[rows][columns];
        
        for (int row = 0; row < rows; row++){
            String line = scnr.nextLine();
            if (line == "") {
                scnr.close();
                throw new Exception("Too few rows");
            }
            String[] stringArr = line.split("\\s+");
            int length = stringArr.length;
            //Checks if correct number of columns in this row
            if (length != columns) {
                scnr.close();
                throw new Exception("Incorrect number of columns");
            }

            for (int i = 0; i < length; i++) {
                double numberDouble = Double.parseDouble(stringArr[i]);
                matrix[row][i] = numberDouble;
            }
            
        }

        if (scnr.hasNext()){
            scnr.close();
            throw new Exception("Too many rows");
        }

        scnr.close();
        valid = true;
        return valid;
    }
    
    /**
     * Take in a list of filenames, then print out each filename as well as a brief explanation of 
     * potential exceptions encountered and whether the file is VALID or INVALID according formatCheck().
     * 
     * Print a usage message if there are no arguments, then return
     * 
     * @param args a String list of filenames that will be checked using formatCheck() to find whether 
     * valid or not
     */
    public static void main(String[] args) {

        String usageMessage = "Usage: $ java FormatChecker file1 [file2...fileN]";
    
        //Check if there are no arguments, if so print a usage message and return
        if (args.length == 0) {
             System.out.println(usageMessage);
             return;
         }
        
         //Check each arg file for formatting, and if an exception is caused it will print an explanation
         //Print whether the file is valid or invalid, for ALL cases
        for(int i = 0; i < args.length; i++){
            boolean valid = false;
            String fileName = args[i];
            System.out.println(fileName);
            try {
                valid = formatCheck(fileName);
            }catch (FileNotFoundException e){
                System.out.println(e.toString());
            }catch (NumberFormatException e) {
                System.out.println(e.toString());
            }catch (InputMismatchException e) {
                System.out.println(e.toString());
            }catch (Exception e) {
                System.out.println(e.toString());
            }finally {
                if (valid == true) {
                    System.out.println("VALID");
                } else {
                    System.out.println("INVALID");
                };
                System.out.println();
            }
        }
    }
}
