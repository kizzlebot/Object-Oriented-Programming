import java.io.File ; 
import java.io.FileNotFoundException; 
import java.util.Scanner; 



public class ReadFile {
	public ReadFile(Scanner scan,File file){
		String text ; 
        try {
            scan.useDelimiter("[<>\\s\\n]");
            
			while ( scan.hasNext()){
                text = scan.next();
				//text.replaceAll("\\s","");
				System.out.printf(text) ; 
            }
        }
        catch( final FileNotFoundException ex){
            System.out.println("\nEError");
        }
    }
}
