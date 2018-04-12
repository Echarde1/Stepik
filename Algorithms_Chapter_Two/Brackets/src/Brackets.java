import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class Brackets {
    public static void main(String args[]) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader("/home/danil/Documents/JavaProjects/Stepik/Algorithms_Chapter_Two/Brackets/input.txt.txt"));
    //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    Check check = new Check(reader.readLine());
    int a = check.makeCheck();

    if (a == 0) {
        System.out.println("Success");
    } else System.out.println(a);
}
}

class Check {
    private String input;
    private int lengthInput;

    public Check(String input) {
        this.input = input;
        this.lengthInput = input.length();
    }
    public int makeCheck() {
        Stack<Integer> max = new Stack<Integer>();
        Stack<Character> stack = new Stack<Character>();
        int i = 0;
        for ( ; i < lengthInput; i++) {
            char ch = input.charAt(i);
            switch(ch) {
                case '{' :
                case '[' :
                case '(' :
                    stack.push(ch);
                    max.push(i + 1);
                    break;
                case '}' :
                case ']' :
                case ')' :
                    if (!stack.isEmpty()) {
                        int chClosed = stack.pop();
                        max.pop();
                        if ((ch == '}' && chClosed != '{')
                                || (ch == ']' && chClosed != '[')
                                || (ch == ')' && chClosed != '('))
                            return i + 1;
                    } else                                                 //недостаток элементов в стеке
                        return i + 1;
                    break;

                default:    // для других символов действия не выполняются
                    break;
            }
        }
        if (!stack.isEmpty()) {
            if (stack.peek() == '{' || stack.peek() == '(' || stack.peek() == '[') {
                return max.peek();
            }
            return i + 1;
        }
        return 0;
    }
}