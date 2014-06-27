/**
 * Created by kizzlebot on 6/25/14.
 */
public class Date implements Comparable<Date>{
	public int month;
	public int day;
	public String year;

	public Date(String date){
		String[] dateStr = date.split("/");
		for ( int i = 0 ; i < 3 ; i++ ){
			if ( i == 0 ) this.month = Integer.parseInt( dateStr[i] );
			else if ( i == 1 ) this.day = Integer.parseInt( dateStr[i] );
			else this.year = dateStr[i] ;
		}
	}
	public Date(int month, int day, String year){
		this.month = month ;
		this.day = day ;
		this.year = year ;
	}
	public void setMonth(int month){
		this.month = month ;
	}
	public void setDay(int day){
		this.day = day ;
	}
	public void setYear(String year){
		this.year = year ;
	}
	public int getMonth() {
		return month;
	}

	public int getDay() {
		return day;
	}

	public String getYear() {
		return year;
	}

	/**
	 * @return month/day/year
	 */
	@Override
	public String toString(){
		return month+"/"+day+"/"+year;
	}


	/**
	 * Returns 1 if "this" is "newer" then that
	 * Returns 0 if this is older that that
	 */
	public int compareTo( Date that ) {
		Integer thisYear = Integer.parseInt((Integer.parseInt(this.year) > 14 ) ? "19"+this.year : "20"+this.year);
		Integer thatYear = Integer.parseInt((Integer.parseInt(that.getYear()) > 14 ) ? "19"+that.getYear() : "20"+that.getYear());
		if (thisYear > thatYear) return -1 ;
		else if ( thisYear < thatYear ) return 1 ;
		else{
			if ( this.month > that.getMonth() ) return -1 ;
			else if ( this.month < that.getMonth() ) return 1 ;
			else{
				if ( this.day > that.getDay() ) return -1 ;
				else if ( this.day < that.day ) return 1 ;
				else return 0;
			}
		}
	}
}
