public class MyTime{
	/* Write program in layers */
	private int second ; 
	private int hour; 
	private int minute; 
	
	// Constructor w/ overload
	public void MyTime(){
		this(0,0,0) ; 	
	}
	public void MyTime(int h, int m ){
		this(h,m,0) ; 		
	}
	public void setTime (int h ,int m, int s ){
		hour = ( (h >= 0  && h < 24 ) ? h : 0); // this is an if else statement  
		minute = ( ( m >= 0 && m < 60 ) ? m : 0 ) ;  
		second = ( ( s >= 0 && s < 60 ) ? s : 0 ) ; 
	}
	
	public String universalTime(){
		return String.format("%02d:%02d:%02d",hour,minute,second); // String.format(formatSpecifier) ; 		
	}
	
	public String toString(){
		return String.format("%d:%02d:%02d %s",( (hour == 0 || hour == 12 ) ? 12 : hour%12),
												minute,second,
												((hour < 12 )?"AM":"PM") );
	}Sublime Text
}
