import week2.LinkedStack;

public class Test {
    public static void main(String[] args) {
        LinkedStack<Integer> stack = new LinkedStack<>();
        for (int i = 0; i < 4; i++)
            stack.push(i);
        System.out.println(stack.size());
    }
}
