public class Anhalteweg {
    public static void main(String[] args) {
        double geschwindigkeit = 120.0; // in km/h

        double reaktionsweg = 3 * (geschwindigkeit / 10);
        double bremsweg = (geschwindigkeit / 10) * (geschwindigkeit / 10);
        double anhalteweg = reaktionsweg + bremsweg;

        System.out.println(anhalteweg + " Meter");
    }
}
