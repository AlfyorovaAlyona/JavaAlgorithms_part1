import week1.LinkedStackOfStrings;

public class Test {
    public static void main(String[] args) {
        LinkedStackOfStrings stack = new LinkedStackOfStrings();
        for (int i = 0; i < 5; i++) {
            stack.push("a");
        }
        System.out.println(stack.size());
    }
}
