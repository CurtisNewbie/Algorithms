public class Palindrome{
	public static void main(String[] args){
		if(args.length != 1){
			System.out.println("Enter a word to check whether it's palindrome");
			return;
		}
		
		String str = args[0];
		for(int i =0; i < str.length() / 2; i++){
			if(str.charAt(i) != str.charAt(str.length() - i - 1)){
				System.out.println("Not a palindrome");
				return;
			}
		}
		System.out.println("Is a palindrome");
	}
}
