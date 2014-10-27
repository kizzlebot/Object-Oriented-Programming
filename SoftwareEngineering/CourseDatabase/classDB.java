import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class classDB{
    //public Student[] enrolled;

    public Course[] courses ;
    public ArrayList<Course> coursesA ;
    public ArrayList<String> lines ;
    public classDB(){
        this.coursesA = new ArrayList<Course>();
        this.lines = new ArrayList<String>();
    }
    public void readIn(String fileName) {
        Scanner scan ;

        try {
            scan = new Scanner( new File( fileName ) );
        }catch ( FileNotFoundException ex ) {
            System.out.println( "File Not Found" );
            return ;
        }
        scan.nextLine();
        while ( scan.hasNextLine() ){
          lines.add(scan.nextLine());
        }

        while ( lines.size() > 0) {
          // Read Three lines at a time
          String firstLine =  lines.remove(0);
          String secondLine = lines.remove(0);
          String thirdLine =  lines.remove(0);
          String fourthLine ;

          // If first element and third are blanks then course name
          if ( firstLine.trim().isEmpty() && thirdLine.trim().isEmpty() ){
            // create a course and addto courses
            Course c = new Course(secondLine);
            coursesA.add(c);
            //firstLine = scan.nextLine();
//            System.out.println("");
//            System.out.println("---------------------------");
//            c.printCourseName();
//            System.out.println("---------------------------");

          }

          // else student
          else{
            // read the fourth line
            if ( firstLine.trim().isEmpty() ){
              firstLine = secondLine ;
              secondLine = thirdLine;
              thirdLine = lines.remove(0);
              fourthLine = lines.remove(0);
            }
            else{
              fourthLine = lines.remove(0);
            }
            coursesA.get(coursesA.size()-1).addStudent(new Student(firstLine, secondLine, thirdLine, fourthLine));
            //System.out.println("");
            //coursesA.get(coursesA.size()-1).getLastStudent().printStudentInfo();
          }
        }
        // Generate listing here
        scan.close();
    }

    public void printClassDB(){
        for ( Course a : this.coursesA){
            a.printCourseName();
            a.printStudents();
        }
    }



    public int runMenu(){
      Scanner scanIn = new Scanner(System.in);
      int choice = 0 ;
      while ( choice < 1 || choice > 7 ){
        System.out.println("\n\n\n\n------------------------------------------------------");
        System.out.println("------------------------- Menu -----------------------");
        System.out.println("------------------------------------------------------");

        System.out.println("1. Print by register date first");
        System.out.println("2. Print by register date last");
        System.out.println("3. Time Spent (Shortest)");
        System.out.println("4. Time Spent (Longest)");
        System.out.println("5. Videos Watched (Greatest)");
        System.out.println("6. Videos Watched (Least)");
        System.out.println("7. Exit");
        System.out.print("\nEnter Choice: ");

        try{
          choice = Integer.parseInt(scanIn.nextLine());
          if ( choice > 0 && choice <= 6 ){
            int courseChoice = runMenuCourses();
            printBy(courseChoice, choice);
          }
          return choice;
        }
        catch(Exception e){
          System.out.println("\nInvalid Input\n");
          choice = 0 ;
        }

      }
      scanIn.close();
      return choice ;
    }
    public int runMenuCourses(){
      Scanner scanIn = new Scanner(System.in);
      int choice = 0 ;

      while ( choice < 1 || choice > this.coursesA.size() ){
        System.out.println();
        int i = 1 ;
        System.out.println("\t------------------------------------------------------");
        System.out.println("\t--------------------- Course Menu --------------------");
        System.out.println("\t------------------------------------------------------");
        for ( Course c : this.coursesA ){
          c.printCourseName(i++);
        }
        System.out.print("\n\tEnter Choice: ");
        try{
          choice = Integer.parseInt(scanIn.nextLine());
          return choice ;
        }
        catch(Exception e){
          System.out.println("Invalid Input");
          choice = 0 ;
        }
      }
      scanIn.close();
      return choice ;
    }
    public void printBy(int courseChoice, int choice){
      Course a = this.coursesA.get(courseChoice-1);
        System.out.println();
      if ( choice == 1 ) {
        a.orderByRegistered(0);
      }
      else if ( choice == 2 ) {
        a.orderByRegistered(1);
      }
      else if ( choice == 3 ){
        a.orderByTimeSpent(0);
      }
      else if ( choice == 4 ){
        a.orderByTimeSpent(1);
      }
      else if ( choice == 5 ){
        a.orderByVideosWatched(0);
      }
      else {
        a.orderByVideosWatched(1);
      }
      a.printCourseName();
    a.printStudents(3);
    }
    public static void main(String[] args){
        classDB c = new classDB();
        c.readIn("InputFile.txt");
        int choice = 0 ;

        for ( int i = 0 ; i < 10 ; i++ ){
          choice = c.runMenu();
          if ( choice == 7 ){
            System.out.println(choice);
            break;
          }
        }

    }

}
