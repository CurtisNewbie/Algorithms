import java.util.*;
public class GCD{
	private static Scanner sc = new Scanner(System.in);
	public static void main(String[] args){
		System.out.println("Enter two numbers to calculate their greatest common divisor");	
		int a = sc.nextInt();
		int b = sc.nextInt();
		System.out.printf("gcd: %d", gcd(a,b));
	}	

	public static int gcd(int a, int b){
		if(b == 0){
			return a;
		}
		int r = a % b;
		return gcd(b, r);
	}
}

