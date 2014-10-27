import java.util.Collections;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.Arrays;
import java.util.ArrayList;



class Course{
    public String courseName;
    public ArrayList<Student> enrolled ;
    public Student lastAdded ;

    public Course(String courseName){
        this.courseName = courseName;
        this.enrolled = new ArrayList<Student>();
    }
    public boolean addStudent(Student s){

        boolean rtn = this.enrolled.add(new Student(s.firstname, s.lastname, s.registerDate, s.timeSpent, s.videosWatched));
        return rtn ;
    }


    public ArrayList<Student> orderByRegistered(int ordering){
      Comparator<Student> registeredDate = new Comparator<Student>() {
        public int compare(Student one, Student two){
          return (ordering == 1) ? two.registerDate - one.registerDate:one.registerDate - two.registerDate ;
        }
    };
    Collections.sort(this.enrolled, registeredDate);
    return this.enrolled ;
    }
    public ArrayList<Student> orderByTimeSpent(int ordering){
      Comparator<Student> timeSpent = new Comparator<Student>() {
        public int compare(Student one, Student two){
          return (ordering == 1) ? two.timeSpent - one.timeSpent : one.timeSpent - two.timeSpent;
        }
    };
    Collections.sort(this.enrolled, timeSpent);
    return this.enrolled ;
    }
    public ArrayList<Student> orderByVideosWatched(int ordering){
      Comparator<Student> videosWatched = new Comparator<Student>() {
        public int compare(Student one, Student two){
          return (ordering == 1) ? one.videosWatched- two.videosWatched: two.videosWatched- one.videosWatched;
        }
    };
    Collections.sort(this.enrolled, videosWatched);
    return this.enrolled ;
    }



    public void printStudents(){
        for ( Student s : this.enrolled){
            s.printStudentInfo();
            System.out.println();
        }
    }
    public void printStudents(int i){
      int k = 0 ;
        for ( k= 0 ; k < i ; k++ ){
          if ( k < this.enrolled.size() ){
            this.enrolled.get(k).printStudentInfo();
          }
        }
    }
    public void printCourseName(){
      System.out.println("-----------------------------");
        System.out.println("Course Name: "+this.courseName);
        System.out.println("-----------------------------");
    }
    public void printCourseName(int i){
        System.out.println("\t"+i+".) "+this.courseName);
    }
}

