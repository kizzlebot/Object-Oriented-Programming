/**
 * Name: 	James Choi
 * Course:  COP3330
 * Time: 	Monday/Wednesday/Friday
 * 
 * Class to store information about a general game character. 
 * Implements the CaveWorker class which defines special operations on a cave, 
 * depending on the type of character. 
 * 
 * @author kizzle
 */

// When an abstract class is subclassed, the subclass provides implementation (definition) of all abstract methods from its 
// subclass. If it doesn't, the subclass must also be declared abstract too.  
// An abstract class can contain the definition for the methods it contains or just its prototype without method body.
public abstract class Character extends Object implements CaveWorker { 
	// Extends : class inheritance - inheritance of public methods and public fields from superclass. Mainly for code reuse  																   
	// Implements: Interface inheritance - classes that inherit interfaces must have definitions for the abstract methods in interface definition
	
	// The Cave that this character is occupying.	
	protected Cave location;
	
	// Construct a character at initLoc
	public Character(Cave initLoc){
		this.location = initLoc ; 
		this.location.makeOpen();
		this.location.setOccupied(true);
	}
	
	/**
	 * Get the Cave this character is occupying. 
	 * @return
	 */
	public Cave getLocation(){
		return this.location;
	}
	
	
	/**
	 * Move this character from his/her current location to the new location. Marks the old spot as unoccupied
	 * and the new spot as occupied. Assumes that new location can be moved to by this character. 
	 * @param to
	 * @return
	 */
	public boolean move (Cave to){
		// Set Current location to unoccupied
		this.location.setOccupied(false);
		
		// Set Next Location to Current location and mark occupied
		this.location = to ; 
		this.location.setOccupied(true);
		if (this.location.isTeleport()){
			//this.location.setMarked(true);
		}
		return true; 
	}

	/**
	 * Abstract method to get the name of this character. Any non-abstract class must implement this method. 
	 * @return Name 
	 */

	// When an abstract class is subclassed, the subclass provides implementation (definition) of all abstract methods from its 
	// subclass. If it doesn't, the subclass must also be declared abstract too.  
	public abstract String getName(); 
	
	
	
	
}
