import java.util.Scanner ; 

public class cipher{
	public static void main(String[] args){
		int shift ; 
		Scanner scan = new Scanner(System.in);
		int c; 
		String str ; 
		char ch ; 
		int probNum = 0 ; 
		while (scan.hasNext()){
			shift = scan.nextInt(); 
			str = scan.nextLine() ; 
			c = 0 ; 
			if (shift > 25){
				shift = shift-26 ; 
			}
			System.out.print("Case #:"+(probNum+1));
			probNum = probNum + 1  ; 
			for (  ; c < str.length() ; c++ ){
				if (str.charAt(c) != ' ' ){
					if ( ((int)str.charAt(c)+shift) > 90){
						ch =(char)(((int)str.charAt(c)+shift-90)+64);	
						System.out.print(ch);	
					}
					else if(((int)str.charAt(c)+shift) <= 90){
						System.out.print((char)((int)str.charAt(c)+(shift)));	

					}else{
						continue ; 
					}
				}else{
					System.out.print(' ');
				}
			}
			System.out.println() ; 	

		}
	}
}