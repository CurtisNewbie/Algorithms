import java.util.Stack;

/**
 * @author yongjie.zhuang
 */
public class DijkstraDoubleStack {

    public static void main(String[] args) {
        String expr = "( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )";
        System.out.printf("%s = %f", expr, interpret(expr));
    }


    public static double interpret(String expr) {
        String[] tokens = expr.split(" ");
        Stack<String> operator = new Stack<>();
        Stack<Double> values = new Stack<>();

        for (String token : tokens) {
            if (token.trim().isEmpty() || token.equals("("))
                continue;

            if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/"))
                operator.push(token);
            else if (token.equals(")")) {
                String oper = operator.pop();
                double operandLeft = values.pop();
                if (oper.equals("+"))
                    operandLeft += values.pop();
                else if (oper.equals("-"))
                    operandLeft -= values.pop();
                else if (oper.equals("*"))
                    operandLeft *= values.pop();
                else if (oper.equals("/"))
                    operandLeft /= values.pop();
                else
                    throw new IllegalStateException("Unknown operator: " + oper);
                values.push(operandLeft);
            } else {
                values.push(Double.parseDouble(token));
            }
        }
        return values.pop();
    }
}
