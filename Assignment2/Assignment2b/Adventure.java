/*
    Name:           James Choi
	Course: 		COP3330
	Section Number: 13Spring 0001
	Description: 	This program runs an adventure game where a random map with pits and obstacles in random
					locations are generated and the user is asked to navigate left right up or down to get to
					the treasure located in the bottom right corner of the map.
*/
import java.util.Scanner ; 
import java.util.Random;
import java.lang.Character ; 


public class Adventure{
	// Make a function to generate a random game board	
	public static void main(String[] args){
		// user kb scanner
		Scanner kb = new Scanner(System.in);
		String tString ; // user this string to get a single character
		char userIn ; // character to specify left right up or down
		char[][] cave = new char[10][10];  // gameboard instance

		boolean gameover  = false ;  // signals gameover
		int x1 = 0 ; 
		int y1 = 0 ;
		fillCave(cave) ;  // call function to randomly generate a cave with P at 00 and T at 99
		//printCave(cave);
		

		// While not game over
		while ( !gameover ){
			// print game board
			printCave(cave) ; // print the cave
			// get userInput as a char
			System.out.printf("%nEnter Your Move <U/D/L/R>: "); // ask user for input
			tString = kb.next() ;  // take the input and assign to a string
			userIn = Character.toUpperCase(tString.charAt(0)) ;  // user tString, to get character at 0 index then convert to uppercase

			// if user moves up
			if (userIn == 'U'){
				if ( y1 > 0 ){ // if we aren't on the first line
					if (cave[y1-1][x1] == '.'){ // if the spot user specified is an empty
						movePlayer(cave,x1,y1,x1,y1-1);  // move to it
						System.out.printf("%nYou have Successfully moved one spot!%n");
						y1-- ; 
					}
					else if (cave[y1-1][x1] == '*'){ // if the spot user specified is a pit
						System.out.printf("%nThat wasn't very smart bro. You walked right into a pit!%nYou're dead :( Better luck next time%n");
						gameover = true; 
					}
					// if the spot user wants to move to is an obstacle
					else if (cave[y1-1][x1] == 'X'){ 
						System.out.printf("%nThat spot is blocked! No movement made.%n"); // don't even move.
					}
					// all other cases, just keep going.
					else{
						continue ; 
					}
				}
				// if y is on the first line and wants to move up, he must've walked into the wall
				else {
					System.out.printf("%nYou can't walk into the wall. No movement made.%n");
					continue ; 
				}

			}
				// else if userInput is 'l'
			else if (userIn == 'L'){
				if ( x1 > 0 ){
					// if the spot user wants to move to is an empty car
					if ( cave[y1][x1-1] == '.'){
						movePlayer(cave,x1,y1,x1-1,y1);
						System.out.printf("%nYou have Successfully moved one spot!%n");
						x1-=1 ; 
					}
					// else if the spot user wants to move to is a pit
					else if (cave[y1][x1-1] == '*'){
						System.out.printf("%nThat wasn't very smart bro. You walked right into a pit!%nYou're dead :( Better luck next time");
						gameover = true ; // Done,
					}
					// else if user wan'ts to move into an obstacle
					else if (cave[y1][x1-1] == 'X'){
						System.out.printf("%nThat spot is blocked! No movement made.%n");
					}
					// Does it every reach here?
					else{
						continue ; 
					}
				}
				// user must've walked into the wall if the user wants to move left and x is equal to zero
				else {
					System.out.printf("%nYou can't walk into the wall. No movement made.%n");
					continue ; 
				}
			}
			// if user wants' to move right
			else if ( userIn == 'R'){
				// if user isn't is the last x1
				if ( x1 < 9 ){ // if x1 is less than 9
					// if users input makes x1 == 9 and y1 is already 9
					if ( x1+1 == 9 && y1 == 9){
						// he won
						System.out.printf("%nYou have found the treasure! Congratulations!%nThanks for playing. Bye!%n");
						gameover = true ; 
					}
					// else
					else{
						// if empty space
						if ( cave[y1][x1+1] == '.'){
							movePlayer(cave,x1,y1,x1+1,y1); // move there
							System.out.printf("%nYou have Successfully moved one spot!%n");
							x1+=1 ; 
						}
						// if pit
						else if (cave[y1][x1+1] == '*'){
							System.out.printf("%nThat wasn't very smart bro. You walked right into a pit!%nYou're dead :( Better luck next time%n"); // game over
							gameover = true ; 
						}
						// if a obstacle
						else if (cave[y1][x1+1] == 'X'){
							System.out.printf("%nThat spot is blocked! No movement made.%n"); // just tell them and keep going
						}
						else{
							continue ; 
						}
					}
				}
				// the user must've walked into the wall if he wants to move right and x1 is 9
				else{
					System.out.printf("%nYou can't walk into the wall. No movement made.%n"); 
				}
			}
			// if user wants to move down
			else if ( userIn == 'D'){
				if ( y1 < 9 ){
					if ( y1+1 == 9 && x1 == 9 ){ // if the next spot is the treasure
						System.out.printf("%nYou have found the treasure! Congratulations!%nThanks for playing. Bye!%n");
						gameover = true ; 
					}else{
						if ( cave[y1+1][x1] == '.'){
							movePlayer(cave,x1,y1,x1,y1+1);
							System.out.printf("%nYou have Successfully moved one spot!%n");
							y1+=1 ; 
						}
						else if (cave[y1+1][x1] == '*'){
							System.out.printf("%nThat wasn't very smart bro. You walked right into a pit!%nYou're dead :( Better luck next time%n");
							gameover = true ; 
						}
						else if (cave[y1+1][x1] == 'X'){
							System.out.printf("%nThat spot is blocked! No movement made.%n");
						}
						else{
							continue ; 
						}
					}
				}
				else {
					System.out.printf("%nYou can't walk into the wall. No movement made.%n");
					continue ; 
				}
			}
			// Validate input
			else{
				System.out.println("%nYou have entered an invalid input! Try Again!");
			}
			

		}


	}

	public static void fillCave(char[][] cave){
		Random rand = new Random();
		int random ; 
		// for every row
		for ( int i = 0  ; i < 10 ; i++){
			// for every column
			for ( int j = 0 ; j < 10 ; j++){
				// generate a random number between 0 include and 100 exclusive.
				random = rand.nextInt(100);
				// five percent means 5/100 so if random is a number less than five percent
				if ( random < 5 ){ // generate a pit
					cave[i][j] = '*' ; 
					random = rand.nextInt(100);
				}
				// 10 percent is between 10/100, so if random is between ten digits
				else if( random >= 5 && random < 13 ){ // generate obstacle
					cave[i][j] = 'X' ; 
					random = rand.nextInt(100);
				}
				// else 
				else { // fill with empty
					cave[i][j] = '.' ;  // empty space
					random = rand.nextInt(100);
				}
			}
		}
		cave[0][0] = 'P'; // then fill initial player location
		cave[9][9] = 'T' ;  // then fill initial treasure location
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

