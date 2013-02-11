// Used with tree.java

public class human{

	public static void main(String[] args){
		// Create a tree object from tree.java
		tree treeObject = new tree("A TREE"); // This is a constructor. Constructor name is always same as the class name 
								      		  // treeObject is a reference to a tree object
											  // Needs to be initialized with name of tree 

		treeObject.printMessage() ;   // This is a method call
		treeObject.setName("A Name"); // Pass in String literal
		
		treeObject.sayName() ;  // Print the assigned value 
	}
}
