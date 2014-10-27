

class Student {
    public String firstname;
    public String lastname;
    public int registerDate;
    public int timeSpent;
    public int videosWatched;

    public Student(String name, String registerDate, String timeSpent, String videosWatched){
    	String[] n = name.split("\\s+");
        this.firstname = n[0];
        this.lastname = n[1];
        this.registerDate = processDate(registerDate);

        this.timeSpent = Integer.parseInt(timeSpent);
        this.videosWatched = Integer.parseInt(videosWatched);
    }    
    public Student(String firstname, String lastname, int registerDate, int timeSpent, int videosWatched){
        this.firstname = firstname;
        this.lastname = lastname;
        this.registerDate = registerDate;

        this.timeSpent = timeSpent;
        this.videosWatched = videosWatched;
    }
    public void printStudentInfo(){
        System.out.println("FirstName:      "+this.firstname);
        System.out.println("LastName:       "+this.lastname);
        System.out.println("Register Date:  "+formatDate(this.registerDate));
        System.out.println("Time Spent:     "+this.timeSpent);
        System.out.println("Videos Watched: "+this.videosWatched);
        System.out.println();
    }
    
    public String formatDate(int date){
    	Integer ddate = new Integer(date);
    	String datestr = ddate.toString();
    	return datestr.substring(4, 6)+"/"+datestr.substring(6, 8)+"/"+datestr.substring(0, 4);
    }
    
    public int processDate(String date){
        String[] dateSplit = date.split("/");
        int rtn = Integer.parseInt(dateSplit[2])*10000 + Integer.parseInt(dateSplit[0])*100 + Integer.parseInt(dateSplit[1]);
        return rtn ; 
    }



}
