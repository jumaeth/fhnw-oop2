package hotel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Hotels {

    public static void main(String[] args) {
        ArrayList<Hotel> hotels = new ArrayList<>(List.of(
                new Hotel("ibis Budget ", 1),
                new Hotel("Radisson Blu", 4),
                new Hotel("Hyperion", 4),
                new Hotel("Motel One", 3),
                new Hotel("Hotel Rochat", 3),
                new Hotel("Rheinfelderhof", 2),
                new Hotel("Les Trois Rois", 5)));

        // TODO: Hotels sortieren

        for (Hotel h : hotels) {
            System.out.println(h);
        }
    }
}
