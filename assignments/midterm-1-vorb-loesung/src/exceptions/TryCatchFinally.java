package exceptions;

public class TryCatchFinally {

    public static void main(String[] args) {
        try {
            foo(null);
        } catch (RuntimeException e) {
            System.out.println("Caught runtime exception: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Caught exception: " + e.getMessage());
        } finally {
            System.out.println("Finally, this annoying exercise is over.");
        }
        System.out.println("Well, almost.");
    }

    public static void foo(String text) {
        try {
            System.out.println(text.toUpperCase());
        } catch (NullPointerException e) {
            System.out.println("Caught something, but now what?");
            throw new RuntimeException("Not sure what to do...");
        } finally {
            System.out.println("The final thing in foo()?");
        }
        System.out.println("foo() over and out!");
    }
}
