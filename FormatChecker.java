import java.io.*;
import java.util.*;

/** 
 * Description
 * 
 * @author Nathan Marquis
 */

public class FormatChecker {

    /**
     1) open file
     2) parse contents
     3) check if fits criteria (rows/columns and also doubles ONLY)
     * 
     * @param filename is a String used to open a file to read
     * 
     * @return returns a boolean of whether or not the file is properly formatted (correct header ,
     *  # of rows/columns , and data)
     * 
     * @throws FileNotFoundException if the file does not exist
     * @throws NumberFormatException if a number is assigned to the wrong type
     * @throws Exception if there is a formatting issue such as: Incorrect number of arguments in first line, 
     * Too few rows, Incorrect number of columns, or Too many rows. Will also catch all other exceptions
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
