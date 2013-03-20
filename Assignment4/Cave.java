
/**
 *  Class to define a single cave location. Composed of the location of the cave on the grid, whether the cave
 *  is occupied by a character, whether it is marked (teleport spots can be marked), and what type of cave it is (see Cave.CaveType). 
 *  @author kizzle
 *
 */
public class Cave {
	public static enum CaveType {
		OPEN,BLOCKED,PIT,TELEPORT;
		private static int row ; 
		
	}
	
	private Integer row ; 
	private Integer column ;
	private boolean occupied ;
	
	private boolean teleport ; // A teleport can be marked or unmarked
	private boolean marked ; // 
	
	private boolean open ;
	private boolean blocked ;
	
	private boolean pit  ;
	
 
	// Constructor: Construct an open cave which is unoccupied and unmarked initially. 
	public Cave(int r, int c) {
		this.row = new Integer(r);
		this.column = new Integer(c);
		this.makeOpen();
		
		this.teleport = false;
		this.pit = false;
		this.open = false;
		this.marked = false;
		
		this.setOccupied(false);
		this.setMarked(false);
	}
	
	/**
	 *  
	 * @return integer representing Row
	 */
	public int getRow(){
		return this.row ; 
	}
	/**
	 * Get the column of this cave. 
	 * @return Integer representing Column
	 */
	public int getCol(){
		return this.column;
	}
	
	/**
	 * Returns whether this cave is occupied 
	 * @return Returns whether occupied or not
	 */
	public boolean isOccupied(){
		return occupied;
	}
	

	/**
	 * Get whether this cave is marked. 
	 * @return Boolean representing if cave is marked
	 */
	public boolean isMarked(){
		return this.marked;
	}	
	/**
	 * Get whether this cave is open. 
	 * @return returns boolean representing if cave is open or not
	 */
	public boolean isOpen(){
		return this.open ; 
	}
	/**
	 * Get whether this cave is a pit. 
	 * @return boolean representing if 
	 */
	public boolean isPit(){
		return this.pit;
	}
	// Get whether this cave is a teleport. 
	public boolean isTeleport(){
		return this.teleport ; 
	}	
	/**
	 * Get whether this cave is blocked. 
	 * @return returns whether cave is blocked
	 */
	public boolean isBlocked(){
		return this.blocked ; 
	}
	
	
	/** 
	 * Mark this cave as blocked. 
	 */
	public void makeBlocked(){
		//this.open = false;
		//this.teleport = false;
		//this.pit = false;
		this.blocked = true; 
	}
	
	/**
	 *  Mark this cave as a pit. 
	 */
	public void makePit(){
		this.pit = true;
		//this.teleport = false;
		//this.pit = true;
		//this.blocked = false;
	}
	/**
	 * Mark this cave as open.
	 * Marks this cave as open 
	 */
	public void makeOpen(){
		this.open = true;
		//this.teleport = false;
		//this.pit = false;
		//this.blocked = false;
	}
	// Mark this cave as a teleport. 
	public void makeTeleport(){
		this.teleport = true;
		//this.open = false;
		//this.pit = false;
		//this.blocked = false;
	}

	
	/**
	 * Set whether this cave is occupied. 
	 * @param set Set whether occupied or not
	 */
	public void setOccupied(boolean set){
		this.occupied = set ; 
	}
	/**
	 * Set whether this cave is marked. 
	 * @param set Sets current Cave Marked
	 */
	public void setMarked(boolean set){
		this.marked = set ; 
	}
}
