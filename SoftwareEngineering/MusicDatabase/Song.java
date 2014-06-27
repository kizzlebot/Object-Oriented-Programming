import java.util.ArrayList;

public class Song implements Comparable<Song>{
	private String songName ;
	private String artistName ;
	private Date uploadDate ;
	private ArrayList<String> genre ;
	private Float popularity ;
	private Boolean isHired ;

	public Song(){
		this.genre = new ArrayList<String>();
		this.artistName = null ;
		this.songName = null ;
		this.uploadDate = null ;
		this.popularity = null;
		this.isHired = null;
	}

	/*
	 *  Setters
	 */
	public void setSongName( String songName ) {
		this.songName = songName;
	}
	public void setArtistName( String artistName ){
		this.artistName = artistName ;
	}
	public void setUploadDate( String uploadDate ) {
		this.uploadDate = new Date(uploadDate);
	}
	public void setGenre( String genre ) {
		for ( String str : genre.replaceAll("\\s+","").split(",") ) this.genre.add(str);
	}
	public void setPopularity( String popularity ) {
		this.popularity = Float.valueOf(popularity);
	}
	public void setHired( String strHired ) {
		this.isHired = (strHired.contains("true")) ? true : false ;
	}


	/*
	 * Getters
	 */
	public String getArtistName(){
		return this.artistName;
	}
	public String getSongName(){
		return this.songName ;
	}
	public Date getUploadDate(){
		return this.uploadDate ;
	}
	public String getUploadDate(Object obj){
		return this.uploadDate.toString();
	}
	public ArrayList<String> getGenre(){
		return this.genre ;
	}
	public String getGenre(Object obj){
		String rtn = new String();
		for ( int i = 0 ; i < this.genre.size()-1 ; i++ ) rtn+=this.genre.get(i)+", ";
		rtn += this.genre.get(this.genre.size()-1);
		return rtn ;
	}
	public Boolean hasGenre(String genre){
		return this.genre.contains(genre);
	}

	public String getPopularity(){
		return this.popularity.toString() ;
	}
	public float getPopularity(Object o){
		return this.popularity;
	}
	public Boolean getHired(){
		return this.isHired ;
	}
	public String getHired(Object o){
		return (this.isHired.equals( true )) ? "true" : "false";
	}
	public void printInfo(){
		System.out.println(" Artist Name:  "+this.getArtistName()+((isHired)?"*":""));
		System.out.println("   Song Name:    "+this.getSongName());
		System.out.println("   Release Date: "+this.getUploadDate(""));
		System.out.println("   Genre(s):     "+this.getGenre(""));
		System.out.println("   Popularity:   "+this.getPopularity()+"\n");
	}

	@Override
	public int compareTo(Song o) {
		return this.uploadDate.compareTo(o.getUploadDate()) ;
	}

}