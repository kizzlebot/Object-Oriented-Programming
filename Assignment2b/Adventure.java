import java.util.Scanner ; 
import java.util.Random;
public class Adventure{
	// Make a function to generate a random game board	


	public static void main(String[] args){

		Scanner kb = new Scanner(System.in);
		String tString ;
		char userIn ; 
		char[][] cave = new char[10][10]; 
		cave[0][0] = 'P' ; 
		cave[9][9] = 'T' ; 
		boolean gameover  = false ; 
		int x1 = 0 ; 
		int y1 = 0 ;
		fillCave(cave) ; 
		//printCave(cave);
		int lr = 0 ; 
		int ud = 0 ; 
		while ( !gameover ){
			// print game board
			printCave(cave) ; 
			if ( x1 == 9 && y1 == 9 ){		
				System.out.printf("%nYou have found the treasure! Congratulations!%nThanks for playing. Bye!");
				break ; 
				
			}
			// get userInput as a char
			System.out.printf("%nEnter Your Move <U/D/L/R>: ");
			tString = kb.next() ; 
			userIn = tString.charAt(0) ; 
			// if userInput is 'u'
			if (userIn == 'U'){
				if ( y1 > 0 ){
					// move only if it is a empty
					if (cave[y1-1][x1] == '.'){
						movePlayer(cave,x1,y1,x1,y1-1);
						System.out.printf("%nYou have Successfully moved one spot!");
						y1-- ; 
					}else if (cave[y1-1][x1] == '*'){
						System.out.printf("%nThat wasn't very smart bro. You walked right into a pit!%nYou're dead :( Better luck next time");
						gameover = true; 
					}else{
						System.out.printf("%nNowhere To go!");
						continue ; 
					}
				}
			}
				// else if userInput is 'l'
			else if (userIn == 'L'){
				if ( x1 > 0 ){
					if ( cave[y1][x1-1] == '.'){
						movePlayer(cave,x1,y1,x1-1,y1);
						System.out.printf("%nYou have Successfully moved one spot!");
						x1-=1 ; 
					}
					else if (cave[y1][x1-1] == '*'){
						System.out.printf("%nThat wasn't very smart bro. You walked right into a pit!%nYou're dead :( Better luck next time");
						gameover = true ; 
					}
					else{
						continue ; 
					}
				}
			}
			// else if userInput is 'd'
			else if (userIn == 'D'){
				if ( y1 < 9 ){
					if ( x1 != 9 && y1 < 8){
						if ( cave[y1+1][x1] == '.'){
						// moveplayer ( cave, x1,y1,)
							movePlayer(cave,x1,y1,x1,y1+1);
							System.out.printf("%nYou have Successfully moved one spot!");
							y1++ ; 
						}
						else if (cave[y1+1][x1] == '*'){
							System.out.printf("%nThat wasn't very smart bro. You walked right into a pit!%nYou're dead :( Better luck next time");
							gameover = true  ;
						}else{
							continue ; 
						}
					}else if (x1 == 9 && y1 == 8 ){
						System.out.printf("%nYou have found the treasure! Congratulations!%nThanks for playing. Bye!");
						gameover = true ; 
					}
				}

			}
		
			// else if userInput is 'r'
			else if (userIn == 'R'){

				if ( x1 < 9 ){
					if ( y1 != 9 && x1 < 8){
						if ( cave[y1][x1+1] == '.'){
							movePlayer(cave,x1,y1,x1+1,y1);
							System.out.printf("%nYou have Successfully moved one spot!");
							x1+=1 ; 
						}
						else if (cave[y1][x1+1] == '*'){
							System.out.printf("%nThat wasn't very smart bro. You walked right into a pit!%nYou're dead :( Better luck next time");
							gameover = true ; 
						}
						else{
							continue ; 
						}
					}
					else if ( y1 == 9 && x1 == 8){
						System.out.printf("%nYou have found the treasure! Congratulations!%nThanks for playing. Bye!");
						gameover = true ; 
					}
					else{
						continue ; 
					}
				}
			}
			else{
				System.out.println("Invalid Input");
			}
		}


	}

	public static void fillCave(char[][] cave){
		Random rand = new Random();
		int random ; 

		for ( int i = 0  ; i < 10 ; i++){
			for ( int j = 0 ; j < 10 ; j++){
				random = rand.nextInt(100);
				if ( random < 5 ){ // generate a pit
					cave[i][j] = '*' ; 
				}else if( random >= 5 && random < 10 ){ // generate obstacle
					cave[i][j] = 'X' ; 
				}else { // fill with empty
					cave[i][j] = '.' ; 
				}
			}
		}
		cave[0][0] = 'P';
		cave[9][9] = 'T' ; 
	}

	public static void printCave(char[][] cave){

		System.out.printf("%nHere's the current game board:%n------------------------------%n");

		for ( int i = 0  ; i < 10 ; i++){
			for ( int j = 0 ; j < 10 ; j++){
				System.out.printf("%c",cave[i][j]); 
			}
			System.out.printf("%n"); 
		}	
	}
	public static void movePlayer(char[][] cave,int x1,int y1,int x2,int y2){
		cave[y2][x2] = cave[y1][x1] ;  
		cave[y1][x1] = '.' ; 
	}
}

