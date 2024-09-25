package sort;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;

public class Sort {
    public static void main(String[] args) {
        var restaurants = new ArrayList<>(List.of(
                new Restaurant("Zum Ochsen", 4.3, 70, emptyList()),
                new Restaurant("Sushi Mafushi", 4.5, 40, emptyList()),
                new Restaurant("Royal Palace", 4.9, 100, emptyList()),
                new Restaurant("Schlüssel", 4.3, 35, emptyList()),
                new Restaurant("Mike's Steakhouse", 4.7, 50, emptyList()),
                new Restaurant("Zum Löwen", 4.5, 60, emptyList()),
                new Restaurant("Lije's Diner", 4.9, 45, emptyList()),
                new Restaurant("Bibis", 4.8, 45, emptyList()),
                new Restaurant("Sol", 4.5, 45, emptyList())));

        restaurants.sort((r1, r2) -> {
            if (r1.getStarRating() != r2.getStarRating()) {
                return Double.compare(r2.getStarRating(), r1.getStarRating());
            } else {
                return Integer.compare(r2.getCapacity(), r1.getCapacity());
            }
        });

        restaurants.forEach(r -> System.out.println(r));
    }
}
