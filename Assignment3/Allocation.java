/**
 * Name: James Choi
 * Course Number: COP3330 Mondays/Wednesdays/Friday 1:30P.M
 * This is where the main method resides for Assignment3
 * This class is in charge of reading in text and processing it into homework objects
 */
import java.util.Scanner; 
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader; 
import java.io.FileWriter;
import java.io.IOException;
public class Allocation {
	public Allocation(){
		// Empty Constructor
	}
	public static void main(String[] args){
		String infile = "HW_List.txt";
		String queries = "TA_Queries.txt";
		String outfile = "HW_Allocation.txt";
		// Create a HomeworkQueue object to put Homeworks into
		HomeworkQueue hq = new HomeworkQueue() ;
					
		try{	
//****************************************************************************************************************
//			Create/Initialize Write Objects			
//****************************************************************************************************************	
			File file = new File(outfile);
			// if Output File doesn't exist then create it
			if (!file.exists()){
				file.createNewFile() ; 
			}
			BufferedWriter out = new BufferedWriter( new FileWriter(file.getAbsoluteFile()));
			
//****************************************************************************************************************
//			Create Read Object
//****************************************************************************************************************	
			Scanner scan ;
//****************************************************************************************************************
//			Initialize Read Object
//****************************************************************************************************************
			scan = new Scanner(new FileReader(infile));
//****************************************************************************************************************
//			Read In HW_List.txt and Write Status to HW_Allocation.txt 
//****************************************************************************************************************			
			while (scan.hasNextLine()){ // While Scanner has a line to read
				String[] aLineArray = scan.nextLine().split("[\\s\\n]"); // read line, split by whitespace and newline. and assign to String array
				Name name = new Name(aLineArray[1],aLineArray[0]); // The first two elements will always be the name
				Files files = new Files(); // Make a Files object 
				for ( int i = 0 ; i < aLineArray.length-5 ; i++ ){ // Starting from the fifth element (beginning of files list), add to files
					files.addFile(aLineArray[5+i]);
				}
				// Create Homework Object with known parameters
				Homework hw = new Homework(Homework.generateNextUniqueUid(),name,Integer.parseInt(aLineArray[2]),files,Integer.parseInt(aLineArray[3]));
				
				// text to output to HW_Allocation. I know you can use StringBuilder instead of concatenation
				String text = new String();
				if (hq.addHomework(hw)){ // If HomeworkQueue reports homework added to queue
					text = "Homework " +hw.toString()+" added to the queue."; // text = "Homework " + "1: #files submitted by..." + " added to the queue" 
					out.write(text); // Write to HW_Allocation.txt
					out.newLine(); // Write a new line
				}
				else{ // else HomeworkQueue reports homework not added to queue
					text = "Homework "+hw.toString()+" already in queue. Not added."; // Build text output
					out.write(text); // Write to HW_Allocation.txt
					out.newLine(); // Write a new line
				}	
				System.out.println(text); // Debug 

			}
			out.write("Finished processing homeworks!\n"); // Confirm Finished Processing to HW_Allocation.txt
			
			scan.close();// Close Scanner


//****************************************************************************************************************
//		Process TA_Requests	
//****************************************************************************************************************			
//****************************************************************************************************************
//			Reinitialize Read Object
//****************************************************************************************************************
			scan = new Scanner(new FileReader(queries));
			// While Scanner has a nextLine
			while (scan.hasNextLine()){
				// Read the line then split into array using space delimiters and newline and give to aLineArray
				String[] a = scan.nextLine().split("[\\s\\n]");

				// For the number of assignments requested
				for ( int i = 0 ; i < Integer.parseInt(a[2]) ; i++){
					String text = "";
					//*************************************************************************************************************************************************
					// 		If theres nothing left in queue or req filled in middle of loop we have to break out of for loop bcus iteration should stop
					//*************************************************************************************************************************************************
					if ( i == 0 && hq.sectionIsEmpty(Integer.parseInt(a[1]))){			
						text+="\nTA "+a[0]+" assigned no homeworks. Nothing in the queue for section "+a[1];
						out.write(text);
						System.out.println(text);
						break ;
					}
					//else if it is the not the last request but theres none in queue
					else if ( i > 0 && i < Integer.parseInt(a[2]) &&  hq.sectionIsEmpty(Integer.parseInt(a[1]) )){						
						text+="\nTA "+a[0]+" assigned "+(Integer.toString(i))+"/"+a[2]+" homework(s) from section "+a[1]+".";
						out.write(text);

						System.out.printf(text);
						break;
					}
					// else if it is the last request and there is someting in queue, get hw and state that TA request was filled
					else if ( i == Integer.parseInt(a[2])-1 && !hq.sectionIsEmpty(Integer.parseInt(a[1]))){
						Homework hw = hq.getHomework(Integer.parseInt(a[1]));
						text+="\nTA "+a[0]+" gets assigned Homework "+hw.toString();
						text+="\nTA "+a[0]+" assigned all "+a[2]+" requested homework(s) from section "+a[1]+".";
						out.write(text);
						System.out.printf(text);
						break ;
					}
					else{
						Homework hw = hq.getHomework(Integer.parseInt(a[1]));
						text+="\nTA "+a[0]+" gets assigned Homework "+hw.toString();
						out.write(text);
						System.out.printf(text);
					}
				}
				out.newLine();
			}
			out.write("\nFinished processing TA Queries! Exiting.");
			out.newLine();
			out.close();

			scan.close();
		}catch(IOException ex){
			System.out.println("Fail");
		}
	}

}

