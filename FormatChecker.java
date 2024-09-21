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
     * @return 2d int array for obj read from a file
     * @throws FileNotFoundException
     */
    private static boolean formatCheck(String filename) throws FileNotFoundException, NumberFormatException, InputMismatchException, Exception {
        boolean valid = false;

        File file = new File(filename);
        Scanner scnr = new Scanner(file);
        //May throw NumberFormatException
        String[] header = scnr.nextLine().split("\\s+");
        if (header.length != 2) {
            scnr.close();
            throw new Exception("Too many arguments in first line");
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
                //System.out.println(numberDouble);
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
    
        if (args.length == 0) {
             System.out.println(usageMessage);
             return;
         }
        
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
                //print Validity
        }


    }
}
