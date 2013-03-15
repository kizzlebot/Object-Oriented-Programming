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
public abstract class Character extends Object implements CaveWorker {
	// The Cave that this character is occupying.	
	protected Cave location;
	
	// Construct a character at initLoc
	public Character(Cave initLoc){
		
	}
	
	/**
	 * Get the Cave this character is occupying. 
	 * @return
	 */
	public Cave getLocation(){
		
	}
	
	
	/**
	 * Move this character from his/her current location to the new location. Marks the old spot as unoccupied
	 * and the new spot as occupied. Assumes that new location can be moved to by this character. 
	 * @param to
	 * @return
	 */
	public boolean move (Cave to){
		
	}

	/**
	 * Abstract method to get the name of this character. Any non-abstract class must implement this method. 
	 * @return
	 */
	public abstract String getName();
	
	@Override
	public String describeModification() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean modifyCave(Cave loc){
		
	}
}
