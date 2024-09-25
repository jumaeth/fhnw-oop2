package hotel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HotelsClass {

    public static void main(String[] args) {
        ArrayList<Hotel> hotels = new ArrayList<>(List.of(
                new Hotel("ibis Budget ", 1),
                new Hotel("Radisson Blu", 4),
                new Hotel("Hyperion", 4),
                new Hotel("Motel One", 3),
                new Hotel("Hotel Rochat", 3),
                new Hotel("Rheinfelderhof", 2),
                new Hotel("Les Trois Rois", 5)));

        // Statt separat eine Klasse zu schreiben, die Comparator implementiert,
        // kann eine "anonyme" Klasse verwendet werden, die keinen Namen hat,
        // sondern direkt zum Erstellen eines Objekts verwendet wird:
        Comparator<Hotel> comp = new Comparator<Hotel>() {
            @Override
            public int compare(Hotel first, Hotel second) {
                if (first.getStars() != second.getStars()) {
                    return second.getStars() - first.getStars();
                } else {
                    return first.getName().compareTo(second.getName());
                }
            }
        };
        Collections.sort(hotels, comp);

        for (Hotel h : hotels) {
            System.out.println(h);
        }
    }
}
