/**
 * Name: James Choi
 * Course Number: COP3330 Mondays/Wednesdays/Friday 1:30P.M
 * Description: Contains compare to method and fields of a single name.
 */
 
public class Name implements Comparable<Name> {
	/**
	 * Every name has the attributes of firstName and lastName
	 */
	private String firstName ; 
	private String lastName ; 
	/**
	 * Constructor
	 * @param The first name. First letter is capitalized by the constructor
	 * @param The last name. First letter is capitalized by the constructor
	 */
	public Name(String first, String last){
		/* Using java.lang.Character,*/
		// capitalize the first letter of the first name and 
		this.firstName = Character.toUpperCase(first.charAt(0)) + first.substring(1).toLowerCase();
		// capitalize the first letter of the last name
		this.lastName = Character.toUpperCase(last.charAt(0)) + last.substring(1).toLowerCase(); 
	}
	/**
	 * Determine whether two Names are identical letter by letter. 
	 * @param other 
	 * @return true if letter by letter comparison is same, else false
	 */
	public boolean equals(Name other){
		// using using string method equals 
		if (this.lastName.equals(other.lastName) && this.firstName.equals(other.firstName)){
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * This compareTo method defines the natural ordering of Names. 
	 * Name objects are naturally ordered by last name alphabetically, 
	 * and when two Names have identical last name, their order is determined 
	 * by first names, alphabetically.
	 * Furthermore, this compare to method essentially uses local method "equals" (which compares two Name object 
	 * using String method "equals" and returns boolean) to check if it's even worth computing integer diff. If "equals"
	 * reports 'not equal' then we String compareTo method lastname and store the result in diff. But if after this assignment
	 * diff is equal to zero then we take the diff between firstname instead and return that value
	 * @param Name object to be compare with this Name
	 */
	public int compareTo(Name other) {
		// Check if this name object differs and if it does
		if (!this.equals(other)){
			Integer diff = this.lastName.compareTo(other.lastName);  // get the integer diff but
			if (diff.equals(0)){ 									   // if the last names are the same
				diff = this.firstName.compareTo(other.firstName);    // get the diff between first names
			}
			// we're done
			return diff ; 
		}
		// the names are the same.
		else{
			return 0 ; // return zero for 'Same Name'
		}
	}
	/**The name in format [first name][space][last name]. 
	 * @return String of the name in format [first name][space][last name]. 
	 */
	public String toString(){
		return this.firstName+" "+this.lastName ; 
	}
}
