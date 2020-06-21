public class StrSorted{
	public static void main(String[] args){
		if(args.length < 1){
			System.out.println("Enter strings to check if its' sorted");
		}
		if(args.length > 1){
			for(int i = 1; i < args.length; i++){
				if(args[i].compareTo(args[i-1]) < 0){
					System.out.println("Not Sorted");
					return;
				}
			}
		}
		System.out.println("Sorted");
	}
}
