/*
	James Choi
	Course number: COP3330 
	Section Number: Monday,Wednesday,Friday 1:30PM-2:20PM
	Assignment #2a
	This source is the class definition of HeatIndex and holds the private attributes temperature humidity that is used to calculate it's heat index.
*/
import java.util.Scanner ;
import java.lang.Math ; 
import java.util.InputMismatchException  ; 

public class HeatIndexCalculator{
	private double[] constants= { -42.379,2.04901523,10.14333127, -0.22475541 ,-6.83783*Math.pow(10,-3),-5.481717*Math.pow(10,-2) ,1.22874*Math.pow(10,-3), 8.5282*Math.pow(10,-4) , -1.99*Math.pow(10,-6) };
	private int temperature ; // variable for temperature  
	private double humidity ;  // variable for humidity 
	private double heatIndex ; // variable for value to calculate 
	
	/*
	 * Constructor HeatIndex
	 * Arguments: temperature humid 
	 * Return value: none
	 * Description: instantiates humidity and temp to a value that isn't viable
	 */
	public HeatIndexCalculator(){
		this.temperature = -1 ; 
		this.humidity = -1;	
	}


	// calculateHeatInex
	// Arguments: n/a
	// Description: Calculates the heatIndex using set values from getTemp and getH
	private void  calculateHeatIndex(){
		heatIndex = constants[0] + constants[1]*temperature+ constants[2]*humidity + constants[3]*temperature*humidity + constants[4]*Math.pow(temperature,2) + constants[5]*Math.pow(humidity,2) + constants[6]*Math.pow(temperature,2)*humidity+ constants[7]*Math.pow(humidity,2)*temperature + constants[8]*Math.pow(temperature,2)*Math.pow(humidity,2)  ;

	}
	// Description prints the calculated heat Index
	private void printHeatIndex(){
		System.out.printf("At a temperature of %dF and a humidity of %3.2f percent . . . ",this.temperature,this.humidity); 
		System.out.printf("%nIt actually feels like: %3.2fF\n ",this.heatIndex) ; 
	}


	// gets the private integer value temperature using input exception
	public void getTemp(Scanner scan){
		// Use a flag variable
		int temp = 0 ; 
		boolean continueInput = true ; 
		do{
			try{
				System.out.println("Please enter the current temperature in degree Fahrenheit: ") ;  // We expect an integer	
				temp = scan.nextInt();
				// Catch Exceptions 
				continueInput  = false ;
				this.temperature = temp ; 
			}
			catch(InputMismatchException ex){ // catch( TheExceptionToCatch ex) ; 
				// throw can be used when exception has actually occured
				// What will you say if this InputMismatchException ex is caught?
				System.out.println("Invalid Input Try Again!") ;
				scan.nextLine() ; // scan.nextLine() tells jvm to throw away previously entered values from stream. 
			}
		} while(continueInput);

	}
	/*
		Method:
		(type) Argument: (Scanner)  
		(type) Return Value: (void) 
		Description: Gets the private double value humidity using input
	*/
	public void getH(Scanner scan){
		// Use a flag variable
		double humid = 0 ; 
		boolean continueInput = true ; 
		do{
			try{
				System.out.println("Please enter the current humidity as a percentage: ") ;  // We expect an integer	
				humid = ((double)scan.nextInt());
				// Catch Exceptions 
				continueInput  = false ;
						this.humidity= humid  ; 
			}
			catch(InputMismatchException ex){ // catch( TheExceptionToCatch ex) ; 
				// throw can be used when exception has actually occured
				// What will you say if this InputMismatchException ex is caught?
				System.out.println("Try Again") ;
				scan.nextLine() ; // scan.nextLine() tells jvm to throw away previously entered values from stream. 
			}
		} while(continueInput);

	}
}
