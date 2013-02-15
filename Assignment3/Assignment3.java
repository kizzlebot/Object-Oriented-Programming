
import java.util.Scanner ; 
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
	This program reads a line everytime it is called.
*/
public class Assignment3{
	
    final static String filename = "infile.txt";

    public static void main(String[] args) throws FileNotFoundException{

        FileReader reader = new FileReader( filename ); // Open file 
        //Scanner scan = new Scanner(reader); // tell scanner to read in from reader object
        String[] readline ;
        readline = ReadLine(reader) ;    // give scanner object and assign to readline 

    }

	public static String[]  ReadLine(FileReader reader){
        Scanner scan = new Scanner(reader) ; 

        
        int i = 0 ; 
        while (scan.hasNextLine()){
            String[] readLine ;
            readLine[i] = scan.nextLine() ; 
            i++ ; 
        }
        return readLine ; 
    }
}