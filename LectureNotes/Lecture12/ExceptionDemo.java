// 	Exception - "Special Case"
//  Whatever class inherits everything from object's class. 
//  	throwable
//  		- exception
//  			- ClassNotFoundException
//  			- IOException
//  			- AWTException
//  				- ArithmeticException
//  				- NullPointerException
//  				- IndexOutofBoundsException
//  				- IllegalArgumentException
//  				- ....
//  			- RuntimeException
//  			- etc...
//  		- error 
//  			- Linkage Errors
//  			- Virtual Machine Errors
//  			- AWTError
//  			- etc.. 

//  Demonstrates Exception Handling
// 
import java.util.Scanner ; 
import java.util.InputMismatchException  ; 
public class ExceptionDemo{
	// Instance Variables
			
	// Constructor / Main Methods
	public static void main(String[] args){
		int n = 0 ;
		// Get an Integer
		Scanner scan = new Scanner(System.in) ;
		// Use a flag variable
		boolean continueInput = true ; 
		do{
			try{
				System.out.println("Enter an Integer") ;  // We expect an integer	
				n = scan.nextInt();
				// Catch Exceptions
				System.out.println("The number you entered was "+n) ; 
				continueInput  = false ;
			}
			catch(InputMismatchException ex){ // catch( TheExceptionToCatch ex) ; 
				// throw can be used when exception has actually occured
				// What will you say if this InputMismatchException ex is caught?
				System.out.println("Try Again") ;
				scan.nextLine() ; // scan.nextLine() tells jvm to throw away previously entered values from stream. 
			}
			finally{ // This block is always excecuted
				// Final Statements
			}
		} while(continueInput);
	
	}
}
