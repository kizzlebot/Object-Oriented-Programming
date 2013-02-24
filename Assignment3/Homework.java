import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;


public class Homework implements Comparable<Homework>{
	
	private Name studentName;
	private Integer uniqueID ; 
	private Integer section ; 
	private Integer numFiles ;
	private Integer submissionDate ;
	private Files filesSubmitted ;
	
	private static TreeSet<Integer> staticSections = new TreeSet<Integer>();
	public Homework(String[] aLine, Integer id){
		// Since this infile is well-formed, we're going to assume that the first word is the last name
		// second word is the first name; followed by the section number, submission date, and number of files.
		this.studentName = new Name(aLine[1],aLine[0]); // Make a name object
		this.section = new Integer(Integer.parseInt(aLine[2]));
		staticSections.add(section);
		this.submissionDate = new Integer(Integer.parseInt(aLine[3])-15);
		this.uniqueID = new Integer(id) ;
		this.numFiles = new Integer(Integer.parseInt(aLine[4]));
	
		
		// Read in the rest only if the student has submitted anything
		if ( numFiles > 0 ){
			this.filesSubmitted = new Files();
			
			for ( int i = 0 ; i < aLine.length-5 ; i++){
				filesSubmitted.addFile(aLine[5+i])  ; 
			}
			
		}
		// else (the student submitted nothing)
		else{
			this.filesSubmitted = new Files() ;
		}
		
	}
	public Integer getHWid(){
		return uniqueID;
	}

	public Name getName(){
		return studentName;
	}
	public Integer getSection(){
		return this.section;
	}
	public Integer getSubmissionDate(){
		return submissionDate ; 
	}
	public Files getFiles(){
		return filesSubmitted;
	}
	public void printInfo(){
		System.out.println("First Name      : "+this.studentName.getFirstName());
		System.out.println("Last Name       : "+this.studentName.getLastName());
		System.out.println("Section         : "+this.section);
		System.out.println("Submission Date : "+this.submissionDate);
		System.out.println("Number of Files : "+this.numFiles );
		System.out.println("Submitted Files : "+this.filesSubmitted.toString());
		System.out.println("Unique ID       : "+this.uniqueID);
		System.out.println("\n");
		
	}
	
	@Override
	public int compareTo(Homework o) {
		// Compare dates: earlier submission get higher precedence
		int returnVal = 0 ;
		if ( !(this.submissionDate.equals(o.submissionDate)) ){
			returnVal = submissionDate.compareTo(o.submissionDate);
		}
		else {
			if (this.filesSubmitted.compareTo(o.filesSubmitted) != 0 ){
				// Compare filesSubmitted: Files class has compareTo Method
				returnVal =  this.filesSubmitted.compareTo(o.filesSubmitted) ;
			}
			else{
				if (this.studentName.compareTo(o.studentName) != 0 ){
					returnVal = this.studentName.compareTo(o.studentName) ;
				}
			}
		}
		
		// Compare Names
		return returnVal ; 
	}	
}
