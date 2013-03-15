import java.lang.Enum;
/**
 *  Class to define a single cave location. Composed of the location of the cave on the grid, whether the cave
 *  is occupied by a character, whether it is marked (teleport spots can be marked), and what type of cave it is (see Cave.CaveType). 
 *  @author kizzle
 *
 */
public class Cave {
	
	
	// Enum of Cavetypes
	public static enum CaveType  {
		BLOCKED,OPEN,PIT,TELEPORT;
		
		private CaveType (){
			
		}
	
		public static Cave.CaveType valueOf(String name){
			switch(name){
			
			}
		}
		
		public static Cave.CaveType[] values(){
			
		}
		
		@Override
		public int compareTo(){
			
		}
	}
	
	
	// Constructor: Construct an open cave which is unoccupied and unmarked initially. 
	public Cave(int r, int c) {}
	
	// Get the row of this cave.
	/**
	 * 
	 * @return integer representing row
	 */
	public int getRow(){
		
	}
	// Get the column of this cave. 
	public int getCol(){
		
	}
	// Set whether this cave is occupied. 
	public void setOccupied(boolean set){
		
	}
	// Returns whether this cave is occupied 
	public boolean isOccupied(){
		
	}
	
	// Set whether this cave is marked. 
	public void setMarked(boolean set){
		
	}

	// Get whether this cave is marked. 
	public boolean isMarked(){
	
	}
	// Mark this cave as open. 
	public void makeOpen(){
		
	}
	
	// Get whether this cave is open. 
	public boolean isOpen(){
		
	}
	
	// Mark this cave as blocked. 
	public void makeBlocked(){
		
	}
	
	// Get whether this cave is blocked. 
	public boolean isBlocked(){
		
	}
	
	// Mark this cave as a pit. 
	public void makePit(){
		
	}
	
	// Get whether this cave is a pit. 
	public boolean isPit(){
		
	}
	
	// Mark this cave as a teleport. 
	public void makeTeleport(){
		
	}
	
	// Get whether this cave is a teleport. 
	public boolean isTeleport(){
		
	}
	

}
