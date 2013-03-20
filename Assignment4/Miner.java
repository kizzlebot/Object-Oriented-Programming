
public class Miner extends Character{
	private String desc;
	public Miner(Cave initloc){
		super(initloc);
		desc = new String();
	}

	/**
	 * Attempt to modify the cave at the given location. Fillers modify a cave by filling in a pit. 
	 * @param loc - The Cave to attempt to modify. 
	 * @return True if the cave was modified (i.e. it was a pit was filled in), false otherwise. 
	 */
	public boolean modifyCave(Cave loc){
		if ( loc.isBlocked() ){
			desc = "Miner Opened Cave";
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
		if ( (to.isOpen() || to.isBlocked()||to.isTeleport()||to.isPit()) && !to.isOccupied() ) {
			if (to.isTeleport()){
				this.desc +="\nSet teleport as marked";
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
		return "Miner";
	}

	@Override
	public String describeModification() {
		String temp = this.desc;
		this.desc = "" ; 
		return temp;
	}
	
}