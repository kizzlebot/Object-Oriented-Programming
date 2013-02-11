import java.util.Scanner ; 


public class FizzBuzz{
	public static void main(String[] args ) {
		
		Scanner scan = new Scanner(System.in);
		int in1, in2 ; 
		boolean b = false; 
		int n ;
		// while b not true
		while (b != true){
			// keep getting ints in1 and in2
			in1 = scan.nextInt();
			in2 = scan.nextInt();
			
			if (in1 == 0 && in2 == 0 ){
				b = true ; 
				break ; 
			}else {
				// if in1 is greater than in2, iterate backwards
				if ( in1 > in2 ){
					for ( n = in1 ; n > in2-1 ; n--){
						if ( n%3 == 0 && n%5 != 0 ){
							// print Fizz
							System.out.println("Fizz");
						}else if ( n%3 != 0 &&  n%5==0){
							// print Buzz
							System.out.println("Buzz");
						}else if (n%3 == 0 &&  n%5==0){
							System.out.println("FizzBuzz");
						}else{
							System.out.println(n);
						}
					}
				}else{
					for ( n = in1 ; n < in2+1 ; n++ ){
						if ( n%3 == 0 && n%5 != 0 ){
						// print Fizz
							System.out.println("Fizz");
						}else if ( n%3 != 0 &&  n%5==0){
							// print Buzz
							System.out.println("Buzz");
						}else if (n%3 == 0 &&  n%5==0){
							System.out.println("FizzBuzz");
						}else{
							System.out.println(n);
						}
					}				
				}
				System.out.println();
				if (!scan.hasNext()){
					b = true ;
					break ; 
				}
			
			}

		}
	}
}