// used with human.java 
 ( /* 5 + 3 */ - 9 + 6/*8*//2 ) //*+4*/ -10
// This class is an object definition
public class tree{
	// object variables can go here
	private String treeName ; 
	
	// The following statement defines the parameters needed in creating a tree object
	// Because of the following statement, in order to create a tree object, a string must be provided in initialization
	public tree(String name){
		treeName = name ; 
		System.out.printf("The Constructor is: %s",this);
	}

	// Method Declarations go here.
	public void printMessage(){
		System.out.println("This is a method");
	}
	public void setName(String name){
		treeName = name ;  // object variable gets assigned the parameters passed 
	}

	public String getName(){
		return treeName ;  // return the value of treeName 
	}

	public void sayName(){
		System.out.printf("This tree name is %s",getName());
		// instead of System.out.printf("This tree name is %s",treeName);
	}



	
}