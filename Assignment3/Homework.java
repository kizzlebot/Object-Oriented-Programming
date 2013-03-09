
/**
 * Name: James Choi 
 * Course Number: COP3330 Mondays/Wednesdays/Friday 1:30P.M
 * Description: 
 * 				- A Homework object encapsulates information about a single homework assignment.
 * 				- It itself uses user-defined wrapper classes "Name", and "Files", as well as the 
 * 			 	  System-defined wrapper classes Integer to hold the fields of a single homework assignment.
 *  			- It implements the Comparable interface and defines the "compareTo" method override which allows
 *  			  each Homework object to compare itself to another Homework object. 
 *  			- Also has a couple of methods for debugging output to standard out
 */

 public class Homework implements Comparable<Homework>{
	
	/**
	 * Every Homework object is differentiated from another homework object by 
	 * 	- The Student's name
	 *  - The Student's section
	 *  - Assignment Submission Date
	 *  - Number of files submitted
	 *  - The files submitted
	 *  - As well as a Unique ID different from all other submissions
	 */
	private Name studentName;
	private Integer uniqueID ; 
	private Integer section ; 
	private Integer daysLate ;
	private Files filesSubmitted ;
	private static Integer nextAvailableUid = 0  ;
	
	
	/**
	 * To create a homework object, an array with each indice containing a word of a line must be passed, as well as an 
	 * Integer id to assign to Unique id
	 * @param aLineInArray A line that is read must be passed in as an array with each index containing a word
	 * @param id A unique ID number for each homework object created
	 */
	/******************************************************************************************************/
	// Constructors
	/******************************************************************************************************/
	
	/**
	 * Construct a new Homework assignment with the given initial values.
	 * @param id
	 * @param name
	 * @param section
	 * @param files
	 * @param dateSubmitted
	 */
	public Homework(int id, Name name, int section, Files files, int dateSubmitted){
		this.studentName = name ;
		this.section = section ; 
		this.daysLate = dateSubmitted - 15 ; 
		this.filesSubmitted = files ; 
		this.uniqueID = id ; 				
	}
	/**
	 * Construct a new Homework assignment with the given initial values and an empty list of Files.
	 * @param id
	 * @param name
	 * @param section
	 * @param dateSubmitted
	 */
	public Homework(int id, Name name, int section, int dateSubmitted){
		this.studentName = name ;
		this.section = section ; 
		this.daysLate = dateSubmitted - 15 ; 
		this.filesSubmitted = new Files(); // Create an empty list of files
		this.uniqueID = id ;
	}
	/**
	 * Construct a new Homework assignment with the given initial values and an empty list of Files.
	 * Self assigns the unprovided unique ID using static method
	 * @param first name
	 * @param 
	 * @param section
	 * @param dateSubmitted
	 */
	public Homework(String first, String last, int section, int dateSubmitted){
		this.studentName = new Name(first,last);
		this.section = section ; 
		this.daysLate = dateSubmitted - 15 ; 
		this.filesSubmitted = new Files(); // create a empty list of files.
		this.uniqueID = Homework.generateNextUniqueUid() ;
	}
	/******************************************************************************************************/
	// End Constructors
	/******************************************************************************************************/
	
	/**
	 * !!! The most important piece of code in Homework class.
	 * @param o A Homework object we're comparing primary Homework object against.
	 * @return an primitive int that is negative if Homework a (the object whose compareTo method we're using) comes before Homework b (the object we're comparing Homework a to).
	 */
	@Override
	public int compareTo(Homework o) {
		/** Precedence for Comparable interface implementation. 
		*  1.) Compare dates: earlier submission get higher precedence but if submitted on same date,
		*  2.) Compare files: Files get sorted on length of File object then lexicographically, but if same
		*  3.) Compare Name : Lexicographic comparison
		*  4.) Must be the same assignment.
		*/
		int returnVal = 0 ;
		// Compare dates, if the dates are not equal
		if ( !(this.daysLate.equals(o.daysLate)) ){
			returnVal = daysLate.compareTo(o.daysLate); // assign returnVal diff of dates
		}
		else {
			// if files submitted are different
			if (this.filesSubmitted.compareTo(o.filesSubmitted) != 0 ){
				// Compare filesSubmitted: Files class already has a compareTo Method defined
				returnVal =  this.filesSubmitted.compareTo(o.filesSubmitted) ; // assign returnVal diff of filesSubmitted
			}
			else{
				// Compare names: If names differ
				if (this.studentName.compareTo(o.studentName) != 0 ){
					returnVal = this.studentName.compareTo(o.studentName) ;  // assign returnVal diff of names
				}
			}
		}
		// return whatever was calculated
		return returnVal ; 
	}
	/**
	 * Generate the next unique ID for a homework assignment and increment the next available ID. 
	 * @return Returns the next available uniqueID
	 */
	static int generateNextUniqueUid(){
		nextAvailableUid++ ; 
		return nextAvailableUid ; 
	}
	/**
	 * Get the number of days late this homework assignment was submitted.
	 * @return Returns a negative value if early, positive value if late, 0 if on time
	 */
	public Integer getDaysLate(){
		return daysLate ; 
	}
	/**
	 * Get the files associated with homework assignment
	 * @return Files object associated with this homework object
	 */
	public Files getFiles(){
		return filesSubmitted;
	}
    /**
     * Get the unique ID associated with this homework.
     * @return Unique id of this 
     */
	public int getId(){
		return uniqueID;
	}
	/**
	 * 
	 * Gets the name of the student who submitted this homework assignment.
	 * @return Returns the Name object associated with this Homework object
	 */
	public Name getName(){
		return studentName;
	}
	/**
	 * Get the section number of this homework assignment.
	 * @return Returns section number associated with this Homework object
	 */
	public Integer getSection(){
		return this.section;
	}
	/**
	 * Get a String representation of this Homework. 
	 * @return String representation of this homework assignment in the form [id]: n file(s) [list of files] submitted by [name] {x days early | on time | x days late} for section [section]
	 */
	public String toString(){
		// Generates the string output of
		// 1: 1 files(s) [Introduction.java] submitted by John Doe 5 day(s) early for section 1 added to the queue.
		String str = this.uniqueID+": "+this.filesSubmitted.getNumberOfFile()+" files(s) "+this.filesSubmitted.toString()+" submitted by "+this.studentName.toString()+" ";
		if (this.daysLate > 0 ){
			str+=this.daysLate+" day(s) late for section "+this.section;
		}else if (this.daysLate < 0 ){
			str+=Math.abs(this.daysLate)+" day(s) early for section "+this.section;
		}else{
			str+="on time for section "+this.section;
		}
		return str;
	}
}
