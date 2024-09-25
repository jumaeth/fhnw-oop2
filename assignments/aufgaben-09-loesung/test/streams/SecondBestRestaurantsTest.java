package streams;

import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SecondBestRestaurantsTest {

    Restaurant royalPalace = new Restaurant("Royal Palace", 4.9, 100,
            List.of("gehoben", "teuer", "modern"));
    Restaurant bibis = new Restaurant("Bibis", 4.8, 45,
            List.of("vegetarisch", "gesund", "regional"));
    Restaurant mikesSteakhouse = new Restaurant("Mike's Steakhouse", 4.7, 50,
            List.of("modern", "teuer"));
    Restaurant hirsch = new Restaurant("Zum Hirsch", 4.6, 40,
            List.of("modern", "gutbürgerlich"));
    Restaurant hofgut = new Restaurant("Hofgut", 4.6, 55,
            List.of("gutbürgerlich", "regional"));
    Restaurant schluessel = new Restaurant("Zum Schlüssel", 4.5, 30,
            List.of("traditionell", "deftig", "regional"));
    Restaurant sol = new Restaurant("Sol", 4.5, 20,
            List.of("vegan", "modern"));
    Restaurant schlossMorgental = new Restaurant("Schloss Morgental", 4.4, 80,
            List.of("gehoben", "traditionell"));
    Restaurant ochsen = new Restaurant("Zum Ochsen", 4.3, 70,
            List.of("gutbürgerlich", "traditionell", "teuer"));
    Restaurant kloster = new Restaurant("Kloster", 4.2, 60,
            List.of("gutbürgerlich", "teuer"));
    Restaurant badischerHof = new Restaurant("Badischer Hof", 4.0, 25,
            List.of("gutbürgerlich", "regional"));
    Restaurant schwanen = new Restaurant("Zum Schwanen", 3.6, 35,
            List.of("deftig", "regional"));

    @Test
    void moreThanTen() {
        var restaurants = List.of(
                schluessel, bibis, kloster, hirsch, mikesSteakhouse,
                royalPalace, schlossMorgental, ochsen, schwanen,
                badischerHof, sol, hofgut);
        var secondBest = StreamTasks2.secondBestRestaurants(restaurants);
        var expected = List.of(
                "Zum Schlüssel", "Sol", "Schloss Morgental", "Zum Ochsen", "Kloster");
        assertEquals(expected, secondBest);

        restaurants = List.of(
                schluessel, kloster, hirsch, mikesSteakhouse,
                royalPalace, schlossMorgental, ochsen, schwanen,
                badischerHof, sol, hofgut);
        secondBest = StreamTasks2.secondBestRestaurants(restaurants);
        expected = List.of(
                "Sol", "Schloss Morgental", "Zum Ochsen", "Kloster", "Badischer Hof");
        assertEquals(expected, secondBest);
    }

    @Test
    void tenOrLess() {
        var restaurants = List.of(
                schluessel, bibis, kloster, hirsch, mikesSteakhouse,
                schlossMorgental, ochsen, schwanen, badischerHof, sol);
        var secondBest = StreamTasks2.secondBestRestaurants(restaurants);
        var expected = List.of(
                "Schloss Morgental", "Zum Ochsen", "Kloster", "Badischer Hof", "Zum Schwanen");
        assertEquals(expected, secondBest);

        restaurants = List.of(
            schluessel, bibis, kloster, mikesSteakhouse, hirsch, hofgut,
            schlossMorgental, sol);
        secondBest = StreamTasks2.secondBestRestaurants(restaurants);
        assertEquals(List.of("Sol", "Schloss Morgental", "Kloster"), secondBest);

        restaurants = List.of(
                schluessel, mikesSteakhouse, hirsch, hofgut, schlossMorgental, sol);
        secondBest = StreamTasks2.secondBestRestaurants(restaurants);
        assertEquals(List.of("Schloss Morgental"), secondBest);
    }

    @Test
    void fiveOrLess() {
        var restaurants = List.of(
                schluessel, hirsch, hofgut, schlossMorgental, sol);
        var secondBest = StreamTasks2.secondBestRestaurants(restaurants);
        assertEquals(emptyList(), secondBest);

        restaurants = List.of(mikesSteakhouse, sol, hirsch, hofgut);
        secondBest = StreamTasks2.secondBestRestaurants(restaurants);
        assertEquals(emptyList(), secondBest);

        secondBest = StreamTasks2.secondBestRestaurants(List.of(sol, hofgut));
        assertEquals(emptyList(), secondBest);

        secondBest = StreamTasks2.secondBestRestaurants(List.of(sol));
        assertEquals(emptyList(), secondBest);

        secondBest = StreamTasks2.secondBestRestaurants(emptyList());
        assertEquals(emptyList(), secondBest);
    }
}
