import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;


public class PINSDB{

	private TreeSet<Song> database ;
	private TreeSet<String> genre ;

	public PINSDB(){
		this.database = new TreeSet<Song>();
		this.genre = new TreeSet<String>();
	}
	public void addEntry(Song s){
		database.add(s);
	}
    public void readIn(String fileName) {
    	Scanner scan ;
    	try {
    		scan = new Scanner( new File( fileName ) );
    	}catch ( FileNotFoundException ex ) {
    		System.out.println( "File Not Found" );
    		return ;
    	}

    	if ( scan.hasNextLine() ) scan.nextLine();
    	while ( scan.hasNextLine() ) {
    		scan.nextLine();
    		Song song = new Song();
    		for ( int i = 0 ; i < 6 && scan.hasNextLine() ; i++ ){
    			switch (i) {
					// artist
    				case 0:
	    				song.setArtistName(scan.nextLine());
	    				break;
					// song
    				case 1:
	    				song.setSongName(scan.nextLine());
	    				break;
					// date
    				case 2:
	    				song.setUploadDate(scan.nextLine());
	    				break;
					// Genre
    				case 3:
	                    // Add genre
	    				song.setGenre(scan.nextLine());
	    				for ( String g : song.getGenre() ) if (!genre.contains(g)) genre.add(g);
	    				break;
					// Popularity
    				case 4:
    					song.setPopularity(scan.nextLine());
    					break;
					// Hired
    				case 5:
    					song.setHired(scan.nextLine());
    					break;
    			}
    			if ( i == 5 ) this.addEntry(song);
    		}
    	}
		// Generate listing here

    	scan.close();
    }




    public void printMostPopular(Boolean signed){
    	ArrayList<Song> aList = new ArrayList<Song>(Arrays.asList(database.toArray(new Song[database.size()])));
    	int i = 0 ;

    	if ( !signed ){
    		Song[] top = new Song[3];
    		while (i < 3){
    			Song tmp = aList.get(0);
    			for (Song s : aList){
    				if ( s.getPopularity("") > tmp.getPopularity("") ){
    					tmp = s ;
    				}
    			}
    			System.out.printf((i+1)+".");
    			aList.remove(aList.indexOf(tmp)).printInfo();;
    			i++ ;
    		}

    	}
    	else{
    		Song[] top = new Song[3];
    		while (i < 3){
    			Song tmp = aList.get(0);
    			for (Song s : aList){
    				if ( s.getPopularity("") > tmp.getPopularity("") && s.getHired() ){
    					tmp = s ;
    				}
    			}
    			System.out.printf((i+1)+".");
    			aList.remove(aList.indexOf(tmp)).printInfo();;
    			i++ ;
    		}
    	}
    }

    /**
     * For category 1. A search on all genres.
     * @param category
     * @param subCategory
     */
    public void findPrint( String subCategory ){
    	// Search for hired and unhired
        int i = 0 ;
        if ( subCategory.equals("A")){
            for ( Song s : database ){
            		System.out.printf((i+1)+".");
                    s.printInfo();
                    i++ ;
                    if ( i == 3 ) break ;
            }
	        if ( i < 3 ) System.out.println("    There are no other releases in the database of artists' samples.");
	        else if ( i == 0 ) System.out.println("    There are no releases in the database of artists' samples.");
	        else return ;
        }
        // Search for only hired
        else{
	        for ( Song s : database ){
                if ( s.getHired() ){
            		System.out.printf((i+1)+".");
                	s.printInfo();
                	i++ ;
                }
                if ( i == 3 ) break ;
	        }
	        if ( i < 3 ) System.out.println("    There are no other releases in the database of hired artists' samples.");
	        else if ( i == 0 ) System.out.println("    There are no releases in the database of hired artists' samples.");
	        else return ;
        }
    }
    public void findPrint( String subCategory, String genre ){
    	// Search for hired and unhired
    	int i = 0 ;
    	System.out.println("\n                     ********************* "+genre+" *********************\n");
        if ( subCategory.equals("A")){
            for ( Song s : database ){
            	if ( s.hasGenre(genre)){
            		System.out.printf((i+1)+".");
            		s.printInfo();
            		i++ ;
            	}
                if ( i == 3 ) break ;
            }
            if ( i < 3 ) System.out.println("    There are no other releases in the database for this genre.");
            else if ( i == 0 ) System.out.println("    There are no releases in the database of artists' samples for this genre.");
            else return ;
        }
        // Search for only hired
        else{
	        for ( Song s : database ){
                if ( s.getHired() && s.hasGenre(genre)){
                	s.printInfo();
                	i++ ;
                }
                if ( i == 3 ) break ;
	        }
	        if ( i < 3 ) System.out.println("    There are no other releases in the database of hired artists' samples for this genre.");
	        else if ( i == 0 ) System.out.println("    There are no releases in the database of hired artists' samples for this genre.");
	        else return ;
        }
    }




    public int runPrompt(){
    	Scanner scan = new Scanner(System.in);
    	// get user input
    	printmenu();
    	System.out.println("Please enter your selection (or simply press 0 to exit the PINSDB suggestion system):");
    	String[] userIn = scan.nextLine().split("\\s+");
    	if (userIn[0]=="0") return 1 ;


    	// If user enters category 1 (all genre newest search)
    	if (userIn[0].equals("1")){
    		if ( userIn[1].equals("A")) System.out.println("\n\nHere are the newest releases from among all PINSDB artists:\n");
    		else System.out.println("\n\nHere are the most popular samples from among the hired PINSDB artists:\n");

            System.out.println("\n================================== NEW RELEASES ==================================");
            System.out.println("Note:  '*' indicates that an artist has been hired.\n");

    		// call findPrint with All/Signed artist arg to print 3 to output
    		findPrint(userIn[1]);
    	}
    	// If user enters category 2 (genre specific newest search)
    	else if (userIn[0].equals("2")){
    		printGenreMenu();

    		// Get user's list of genres
    		String[] stCat = scan.nextLine().split("\\s+");

    		System.out.println("\n\nHere are the suggestions for the genre(s) you selected:\n");
    		System.out.println("\n================================== SUGGESTIONS BY GENRE ==================================");
            System.out.println("Note:  '*' indicates that an artist has been hired.\n");


    		// For every genre user entered
    		for ( String s : stCat ){
    			// Convert to int
    			int cat = Integer.parseInt(s);
    			// If user enters a value greater than the number of genres listed
    			if ( cat > genre.size() ){
    				for ( int i = 0 ; i < genre.size() ; i++ ) findPrint(userIn[1], genre.toArray(new String[genre.size()])[i]);
    				return 0;
    			}
    			// Otherwise
    			else findPrint(userIn[1], genre.toArray(new String[genre.size()])[cat-1]);
    		}
    	}
    	else if (userIn[0].equals("3")){
    		if ( userIn[1].equals("A")) System.out.println("\n\nHere are the most popular samples from among all PINSDB artists:");
    		else System.out.println("\n\nHere are the most popular samples from among hired PINSDB artists:");
    		System.out.println("\n================================== MOST POPULAR ==================================");
    		System.out.println("Note:  '*' indicates that an artist has been hired.\n");


    		if ( userIn[1].equals("A") ) printMostPopular(false);
    		else printMostPopular(true);
    	}
    	else if (userIn[0].equals("0")) {
    		System.out.println("\nGoodbye");
    		return 1 ;
    	}
    	else return 0 ;
        System.out.println("==========================================================================================\n");

    	return 0 ;
    }


    public void printmenu(){
    	System.out.println("-------------- menu #1 --------------\t\t----------------- menu #2 -----------------" );
		System.out.println("   1. view new releases              \t\t   a. include samples added by any artist  ");
    	System.out.println("   2. view suggestions by genre      \t\t   b. limit results to hired artists only  ");
    	System.out.println("   3. view most popular samples      \t\t-------------------------------------------");
    	System.out.println("-------------------------------------");
    }
    public void printGenreMenu(){
    	int i = 0 ;
    	String[] str = genre.toArray(new String[genre.size()]);
    	for ( String g : genre ){
    		System.out.println("\t"+(i+1)+". "+g);
    		i++ ;
    	}
    }

	public static void main(String[] args) {
		PINSDB db = new PINSDB();
		db.readIn("input.txt");
//		db.printDB();
		int cont = 0 ;
		while ( cont == 0 ) cont = db.runPrompt();
	}

}
