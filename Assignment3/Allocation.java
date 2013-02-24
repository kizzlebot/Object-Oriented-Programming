import java.util.ArrayList;

import java.util.Scanner; 
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader; 
import java.io.FileWriter;
import java.io.IOException;
public class Allocation {
	public static void main(String[] args){
		
		try{
			File file = new File("outfile.txt");
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter out = new BufferedWriter(fw);
			HomeworkQueue hq = processHomework(readIn("intfile.txt"),out);
			
			processRequests(readIn("TA_queries.txt"), hq,out);
			out.close();
		}catch(IOException ex){
			System.out.println("Fail");
		}
	}

	// This method takes an ArrayList containing the inputFile information.
	// For every line, it creates a Homework object and adds it to the homework Queue
	public static HomeworkQueue processHomework(ArrayList<String[]> linesArrayList,BufferedWriter out){
		HomeworkQueue hq = new HomeworkQueue ();
		int ID = 1 ; 
		try{
			for (String[] a : linesArrayList){
				Homework hw = new Homework(a,ID);
				String text = new String();
				if (hq.addHomework(hw)){
					text = "Homework "+hw.getHWid()+": "+hw.getFiles().toString()+" submitted by "+hw.getName().getFirstName()+" "+hw.getName().getLastName()+" ";
					if (hw.getSubmissionDate() > 0){
						text+="days late ";
					}else if( hw.getSubmissionDate() < 0){
						text+="days early ";
					}else{
						text+="on time ";
					}
					text+="for section "+hw.getSection()+" added to queue";
					out.write(text);
					out.newLine();
				}
				else{
					text = "Homework "+hw.getHWid()+": "+hw.getFiles().toString()+" submitted by "+hw.getName().getFirstName()+" "+hw.getName().getLastName()+" ";
					if (hw.getSubmissionDate() > 0){
						text+=hw.getSubmissionDate()+" day(s) late ";
					}else if( hw.getSubmissionDate() < 0){
						text+=Math.abs(hw.getSubmissionDate())+" day(s) early ";
					}else{
						text+="on time ";
					}
					text+="for section "+hw.getSection()+" already in queue. Not added.";
					out.write(text);
					out.newLine();
				}	
				
				ID++ ; 
			}
			out.write("Finished processing homeworks!\n");
		}catch(IOException ex){
			System.out.println("Exception");
		}
		return hq ; 
	}
	public static void processRequests(ArrayList<String[]> linesArrayList,HomeworkQueue hq,BufferedWriter out){
		try{
			out.newLine();
			// for every line in the TA request
			for ( String[] a : linesArrayList){
				String text = new String();
				// for the number of assignments requested
				for ( int i = 0 ; i < Integer.parseInt(a[2]) ; i++){
					text = "";
					// if it is the first assignment request and there is none in queue
					if ( i == 0 && hq.sectionIsEmpty(Integer.parseInt(a[1]))){
						
						text+="\nTA "+a[0]+" assigned no homework(s). Nothing in queue for section "+a[1];
						out.write(text);
						//out.newLine();
						System.out.println(text);
						break ;
					}
					//else if it is the not the last request but theres none in queue
					else if ( i > 0 && i < Integer.parseInt(a[2]) &&  hq.sectionIsEmpty(Integer.parseInt(a[1]) )){
						
						text+="\nTA "+a[0]+" assigned "+(Integer.toString(i))+"/"+a[2]+" requested homework(s) from section "+a[1];
						out.write(text);
						//out.newLine();
						System.out.printf(text);
						break;
					}
					 
					// else if it is the last request and there is someting in queue
					else if ( i == Integer.parseInt(a[2])-1 && !hq.sectionIsEmpty(Integer.parseInt(a[1]))){
						Homework hw = hq.getHomework(Integer.parseInt(a[1]));
						text+="\nTA "+a[0]+" assigned Homework "+hw.getHWid()+": "+hw.getFiles().toString()+" submitted by "+hw.getName().getFirstName()
								+" "+hw.getName().getLastName();
						if ( hw.getSubmissionDate() > 0){
							text+=" "+hw.getSubmissionDate()+" day(s) late";
						}else if ( hw.getSubmissionDate() < 0){
							text+=Math.abs(hw.getSubmissionDate())+" day(s) early";
						}
						else{
							text+=" on time";
						}
						text+=" for section "+Integer.parseInt(a[1])+"";
						
						text+="\nTA "+a[0]+" assigned all "+a[2]+" requested homework(s) from section "+a[1];
						out.write(text);
						//out.newLine();
						System.out.printf(text);
						break ;
					}
					else if(i < Integer.parseInt(a[2])-1 && !hq.sectionIsEmpty(Integer.parseInt(a[1]))) {
						Homework hw = hq.getHomework(Integer.parseInt(a[1]));
						text+="\nTA "+a[0]+" assigned Homework "+hw.getHWid()+": "+hw.getFiles().toString()+" submitted by "+hw.getName().getFirstName()
								+" "+hw.getName().getLastName();
						if ( hw.getSubmissionDate() > 0){
							text+=" "+hw.getSubmissionDate()+" day(s) late";
						}else if ( hw.getSubmissionDate() < 0){
							text+=" "+Math.abs(hw.getSubmissionDate())+" day(s) early";
						}
						else{
							text+=" on time";
						}
						text+=" for section "+Integer.parseInt(a[1]);
						out.write(text);
						//out.newLine();
						System.out.printf(text);
						
					}
					else{
						Homework hw = hq.getHomework(Integer.parseInt(a[1]));
						text+="\nTA "+a[0]+" assigned Homework "+hw.getHWid()+": "+hw.getFiles().toString()+" submitted by "+hw.getName().getFirstName()
								+" "+hw.getName().getLastName();
						if ( hw.getSubmissionDate() > 0){
							text+=" "+hw.getSubmissionDate()+" day(s) late";
						}else if ( hw.getSubmissionDate() < 0){
							text+=" "+Math.abs(hw.getSubmissionDate())+" day(s) early";
						}
						else{
							text+=" on time";
						}		
						text+=" for section "+Integer.parseInt(a[1]);
						
						out.write(text);
						//out.newLine();
						System.out.printf(text);
					}
					//Homework hw1 = hq.getHomework(5) ;
					//hw1.printInfo();
					
				}
				out.newLine();
			}
			out.write("\nFinished processing TA Queries! Exiting.");
			out.newLine();
		}catch(IOException ex){
			System.out.println("Error");
		}
	}
	
	// This method will read in a given fileName and return an arrayList containing String array of text in a line
	public static ArrayList<String[]> readIn(String fileName)  {
		ArrayList<String[]> linesArrayList = new ArrayList<String[]>();
		try{
			FileReader fileRead = new FileReader(fileName);
			Scanner scan = new Scanner(fileRead);
			
			// While Scanner has a nextLine
			while (scan.hasNextLine()){
				// Read the line then split into array using space delimiters and newline and give to aLineArray
				String[] aLineArray = scan.nextLine().split("[\\s\\n]");
				// add aLineArray to arrayList
				linesArrayList.add(aLineArray);
			}
			// Close Scanner
			scan.close();
			// Close FileReader
			fileRead.close();
			// Return the linesArrayList generated

		}
		catch(IOException ex){
			System.out.println("File Open Error");
		}
		
		return linesArrayList ;
		
	}
}
