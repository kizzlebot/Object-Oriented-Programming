			//import java.io.util.Scanner ; 
public class MyTimeTester{
	// Tests MyTime.java
	public static void main( String[] args ){
		MyTime MyTimeObject = new MyTime();	
		MyTimeObject.setTime(25,45,13) ; 
		System.out.println(MyTimeObject.universalTime());
		System.out.println(MyTimeObject.toString());
	}
}
