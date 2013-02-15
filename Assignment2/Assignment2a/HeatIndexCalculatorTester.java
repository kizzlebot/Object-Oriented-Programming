/*
 * James Choi
 * Course number: COP3330 
 * Section Number: Monday,Wednesday,Friday 1:30PM-2:20PM
 * Assignment #2a
 * This is the implementation of the class definition found in  HeatIndexCalculator.java 
 */
import java.util.Scanner ; 
public class HeatIndexCalculatorTester{
	public static void main(String[] args){		
		HeatIndexCalculator heat = new HeatIndexCalculator() ; 
		Scanner scan = new Scanner(System.in) ; // Scanner object would be more suitable to be in main and passed to getH and getTemp instead of inside class def
		method = heat.getClass().getDeclaredMethod(getTemp(scan)) ; // Access modifiers from HeatIndex Calculators had to be set public in order to get access to these method invocations. 
		method.setAccessible(true) ; 			// I am aware that UML from specifications requires for a private.
		Object r  = method.invoke(heat);



	}
}	
