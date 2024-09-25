public class Gedicht {

    public static void main(String[] args) {
        verse1();
        verse2();
        verse3();
        verse4();
        verse5();
        verse6();
        verse7();
    }

    public static void verse1() {
        System.out.println("This is the house that Jack built.\n");
    }

    public static void text1() {
        System.out.println("That lay in the house that Jack built.\n");
    }

    public static void verse2() {
        System.out.println("This is the malt");
        text1();
    }

    public static void text2() {
        System.out.println("That ate the malt");
        text1();
    }

    public static void verse3() {
        System.out.println("This is the rat");
        text2();
    }

    public static void text3() {
        System.out.println("That killed the rat");
        text2();
    }

    public static void verse4() {
        System.out.println("This is the cat");
        text3();
    }

    public static void text4() {
        System.out.println("That worried the cat");
        text3();
    }

    public static void verse5() {
        System.out.println("This is the dog");
        text4();
    }

    public static void text5() {
        System.out.println("That tossed the dog");
        text4();
    }

    public static void verse6() {
        System.out.println("This is the cow with the crumpled horn");
        text5();
    }

    public static void text6() {
        System.out.println("That milked the cow with the crumpled horn");
        text5();
    }

    public static void verse7() {
        System.out.println("This is the maiden all forlorn");
        text6();
    }
}
