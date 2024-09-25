public class Fibonacci {

    public static void main(String[] args) {
        int zweitLetzte = 1;
        int letzte = 1;

        for (int i = 1; i <= 20; i++) {
            System.out.println(zweitLetzte);
            int neue = letzte + zweitLetzte;
            zweitLetzte = letzte;
            letzte = neue;
        }
    }
}
