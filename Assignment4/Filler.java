/**
 *  Class to represent a Filler. The Filler has a special ability to walk into pits 
 *  and fill the pit with sand so that it is safe for other characters to walk into
 *  this cave in the future. 
 *  @author kizzle
 *
 */
public class Filler extends Character {
	
	public Filler(Cave initLoc) {
		super(initLoc);
	}
	 
    /**
     * Attempt to modify the cave at the given location. Fillers modify a cave by filling in a pit. 
     * @param loc - The Cave to attempt to modify. 
     * @return True if the cave was modified (i.e. it was a pit was filled in), false otherwise. 
     */
	public boolean modifyCave(Cave loc){
		if ( loc.isPit() ){
			loc.makeOpen();
			
			return true ; 
		}else{
			return false ; 
		}
	}
	
	/**
	 * Override of the move method from the Character class. 
	 * This method checks to see if the Filler can actually move to this new location.
	 * If so, it should make a call to the move method in the super class (which actually performs the move). 
	 * If not, simply return false.
	 */
    public boolean move(Cave to){
		if (( to.isOpen() || this.modifyCave(to) || to.isTeleport()) && !to.isOccupied()){
			if ( to.isTeleport()){
				to.setMarked(true);
			}
			super.move(to);
			return true; 
		}
		else 
			return false;
	}
	
	/**
	 * The Name of this filler
	 * @return Name of this filler
	 */
	public String getName(){
		return "Filler";
	}

}
