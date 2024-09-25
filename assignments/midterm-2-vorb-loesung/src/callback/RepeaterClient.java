package callback;

public class RepeaterClient {
    public static void main(String[] args) {
        var repeater1 = new Repeater1(i -> {
            System.out.println("Hello, " + i + "!");
        });
        repeater1.repeat(2);

        System.out.println();
        repeater1.repeat(5);

        /////////////////////
        System.out.println();
        /////////////////////

        var repeater2 = new Repeater2(i -> {
            System.out.println("Hello, " + i + "!");
        });
        repeater2.repeat(2);

        System.out.println();
        repeater2.repeat(5);
    }
}
