import java.util.*;

public class DijkstraDoubleStack {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String cal = sc.nextLine();
        System.out.println("Calculating: " + cal);
        System.out.println("Result: " + evaluate(cal));
    }

    public static double evaluate(String calc) {
        Stack<String> oper = new Stack<>();
        Stack<Double> vals = new Stack<>();
        for (int i = 0; i < calc.length(); i++) {
            char c = calc.charAt(i);
            if (c == '+' || c == '-' || c == '*' || c == '/') {
                oper.push("" + c);
            } else if (c == '(' || c == ' ') {
                // do nothing
            } else if (c == ')') {
                double curr = vals.pop();
                char o = oper.pop().charAt(0);
                switch (o) {
                    case '+':
                        curr += vals.pop();
                        break;
                    case '-':
                        curr -= vals.pop();
                        break;
                    case '*':
                        curr *= vals.pop();
                        break;
                    case '/':
                        curr /= vals.pop();
                }
                vals.push(curr);
            } else {
                vals.push((double) c - '0');
            }
        }
        return vals.pop();
    }
}
