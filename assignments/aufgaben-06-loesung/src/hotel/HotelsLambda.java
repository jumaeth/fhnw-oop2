package hotel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HotelsLambda {

    public static void main(String[] args) {
        ArrayList<Hotel> hotels = new ArrayList<>(List.of(
                new Hotel("ibis Budget ", 1),
                new Hotel("Radisson Blu", 4),
                new Hotel("Hyperion", 4),
                new Hotel("Motel One", 3),
                new Hotel("Hotel Rochat", 3),
                new Hotel("Rheinfelderhof", 2),
                new Hotel("Les Trois Rois", 5)));

        // Statt einer Klasse, die Comparator implementiert, kann
        // Lambda-Ausdruck verwendet werden:
        Collections.sort(hotels, (first, second) -> {
            if (first.getStars() != second.getStars()) {
                return second.getStars() - first.getStars();
            } else {
                return first.getName().compareTo(second.getName());
            }
        });

        // Um nur nach Sterne zu sortieren, gibt es eine noch
        // kürzere Schreibweise:
        Collections.sort(hotels, (first, second) -> second.getStars() - first.getStars());

        for (Hotel h : hotels) {
            System.out.println(h);
        }
    }
}
