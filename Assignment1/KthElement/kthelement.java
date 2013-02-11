import java.util.Scanner ; 
public class kthelement{
	public static void main(String[] args){
		int n ; 

		// first line contains the number of elements to allocation for array
		int numElements ; 
		boolean kill = false ; 
		Scanner scan = new Scanner(System.in);
		int[] array = new int[100];
		int kth ;
		while ( kill == false ){
			// get the numberOfElements
			numElements = scan.nextInt() ; 

			// if number of elements isn't the exit condition
			if ( numElements != 0 ){	
				for ( n = 0 ; n < numElements ; n++){
					array[n] = scan.nextInt() ; 
					//System.out.print(" " + array[n]);
				}
				kth = scan.nextInt() ; 
				for ( n = 0 ; n < numElements ; n++){
					if ((n+1)%kth == 0){
						if ( (n+1)+kth > numElements){
							System.out.print(array[n]);
						}else{
							System.out.print(array[n]+", ");
						}
					}
				}
			}

			if ( !scan.hasNext() ){
				kill = true ;
				break ; 
			}
			System.out.println();
		}
	}
}