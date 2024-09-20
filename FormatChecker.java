import java.io.*;
import java.util.*;

/** 
 * Description
 * 
 * @author Nathan Marquis
 */

public class FormatChecker {

    /**
     * Parses through a given file to write its contents to a instance of ___, returns said array
     * 
     * @param filename is a String used to open a file to read
     * @return 2d int array for obj read from a file
     * @throws FileNotFoundException
     */
    private int[][] readMatrix(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner scnr = new Scanner(file);
        int size = scnr.nextInt();
        this.magicSquareArray = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.magicSquareArray[i][j] = scnr.nextInt();
            }
        }
        scnr.close();
        
        return this.magicSquareArray;
    }
    //
    
    public static void main(String[] args) {
        //parse `args` for the inputted filenames
        //if `args` has 0 length
            //print usage statement and explanation
            //exit
        //try 
            //open file
            //parse contents
            //check if fits criteria (rows/columns and also doubles ONLY)
        //catch FileNotFoundException
        //catch NumberFormatException
        //catch InputMismatchException
        //finally print Validity
        
        //for each `arg`
            //try to open file, if not THROW 


    }
}
