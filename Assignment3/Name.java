import java.io.* ; 
import java.util.*;
public class Name implements Comparable<Name> {
	private String firstName ; 
	private String lastName ; 
	
	public Name(String first, String last){
		firstName = first  ;
		lastName = last ; 
	}
	public String getFirstName(){
		return firstName ; 
	}
	public String getLastName(){
		return lastName ; 
	}
	
	public int compareTo(Name o) {
		// assign result to val of compareTo of lastNames
		Integer result = new Integer(lastName.compareTo(o.lastName));
		// if lastNames are identical
		if (result.equals(0) ){
			// compare the firstNames
			result = firstName.compareTo(o.firstName);
			// if this.firstName if greater than o.firstName
			return result ;
		}
		// else (if lastNames are identical)
		else{
			 
			return result ; 
		}
	}
}
