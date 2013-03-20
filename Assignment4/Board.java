import java.util.Random;
import java.util.TreeMap;


public class Board {
	
	private Cave[][] caveArray ;  // gameboard instance
	private int row ; 
	private int col ; 
	TreeMap<Integer,Cave> intToCave = new TreeMap<Integer,Cave>();
	TreeMap<Integer,TreeMap<Integer,Cave>> intToTree =  new TreeMap<Integer,TreeMap<Integer,Cave>>();
	public Board(int rows, int cols) {
				
		caveArray =  new Cave[rows][cols];
		this.intToCave = new TreeMap<Integer,Cave>();
		this.intToTree =  new TreeMap<Integer,TreeMap<Integer,Cave>>();
		this.row = rows ; 
		this.col = cols;
		
		Random rand = new Random();
		int random ; 
		// for every row
		for ( int i = 0  ; i < rows ; i++){
			// for every column
			for ( int j = 0 ; j < cols ; j++){
				// generate a random number between 0 include and 100 exclusive.
				Cave cave = new Cave(i,j);
				
				//System.out.println("Row: "+i+" Column: "+j);
				random = rand.nextInt(100);
				// five percent means 5/100 so if random is a number less than five percent
				if ( random < 10 ){ // generate a teleport T
					cave.makeTeleport() ;
					random = rand.nextInt(100);
				}
				// 20 percent blocked
				else if( random >= 10 && random <  30 ){ // generate obstacle 'X'					
					cave.makeBlocked();
					random = rand.nextInt(100);
				} 
				// 20 percent pit
				else if( random >= 30 && random <  50 ){ 
					//generate pit
					cave.makePit();
					random = rand.nextInt(100);
				} 
				// else 50 percent open
				else { // fill with empty
					cave.makeOpen();
					random = rand.nextInt(100);
				}
				// for first box and last box
				if ((i == 0 && j == 0) || (i==rows-1 && j == cols-1) ){
					cave.makeOpen();
					cave.setOccupied(true);
				}
				caveArray[i][j] = cave ; 
			}
		}
	}
	/**
	 * Get the cave at location (r,c). 
	 * 
	 * @param r
	 * @param c
	 * @return The Cave stored at this location, or null if this spot is not on the board.
	 */
	public Cave getCave(int r,int c){
		
		if (ok(r, c)){
			return caveArray[r][c] ; 
		}
		else {
			return null ; 
		}
	}
	/**
	 * Check if this location is inside the bounds of the board. 
	 * @param r
	 * @param c
	 * @return Returns true if this spot is within the bounds of the defined board, false otherwise.
	 */
	public boolean ok(int r,int c){
		if (r < row && c < col && r > -1 && c > -1){
			//System.out.println("Rows In ok: "+r+" Columns: "+c);
			return true; 
		}
		else{
			return false;
		}
	}
	/**
	 * Get a random unoccupied, open location from the current state of the game board.
	 * Guaranteed not to return the location of the treasure.
	 * @return
	 */
	public Cave getUnoccupiedOpenLocation(){
		 Random rand = new Random();
		 int r = rand.nextInt(this.row) ; 
		 int c = rand.nextInt(this.col) ;
		 do{ 
			 r = rand.nextInt(col) ; 
			 c = rand.nextInt(row) ;
			 if ( (r == 0 && c == 0 )||( c == this.col-1 && r == this.row -1)){
				 r = rand.nextInt(col) ; 
				 c = rand.nextInt(row) ;
			 }
			 
		 }while (!caveArray[r][c].isOpen() && !caveArray[r][c].isOccupied() );
		 return caveArray[r][c]; 
	}
}
