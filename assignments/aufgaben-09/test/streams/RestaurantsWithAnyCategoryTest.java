package streams;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static streams.StreamTasks2.restaurantsWithAnyCategory;

public class RestaurantsWithAnyCategoryTest {

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
    void single() {
        var restaurants = List.of(
                royalPalace, bibis, mikesSteakhouse, hirsch, hofgut, schluessel,
                sol, schlossMorgental, ochsen, kloster, badischerHof, schwanen);
        var result = restaurantsWithAnyCategory(restaurants, Set.of("modern"));
        assertEquals(4, result);

        result = restaurantsWithAnyCategory(restaurants, Set.of("traditionell"));
        assertEquals(3, result);

        result = restaurantsWithAnyCategory(restaurants, Set.of("gehoben"));
        assertEquals(2, result);
    }

    @Test
    void multiple() {
        var restaurants = List.of(
                royalPalace, bibis, mikesSteakhouse, hirsch, hofgut, schluessel,
                sol, schlossMorgental, ochsen, kloster, badischerHof, schwanen);
        var result = restaurantsWithAnyCategory(restaurants, Set.of("modern", "traditionell"));
        assertEquals(7, result);

        result = restaurantsWithAnyCategory(restaurants, Set.of("traditionell", "deftig"));
        assertEquals(4, result);

        result = restaurantsWithAnyCategory(restaurants, Set.of("gehoben", "teuer"));
        assertEquals(5, result);
    }

    @Test
    void noneMatch() {
        var restaurants = List.of(
                royalPalace, bibis, mikesSteakhouse, hirsch, hofgut, schluessel,
                sol, schlossMorgental, ochsen, kloster, badischerHof, schwanen);
        var result = restaurantsWithAnyCategory(restaurants, Set.of("billig", "chillig"));
        assertEquals(0, result);

        result = restaurantsWithAnyCategory(restaurants, Set.of("grillig"));
        assertEquals(0, result);
    }

    @Test
    void empty() {
        var result = restaurantsWithAnyCategory(emptyList(), Set.of("modern", "vegan"));
        assertEquals(0, result);

        result = restaurantsWithAnyCategory(emptyList(), Set.of("traditionell"));
        assertEquals(0, result);

        result = restaurantsWithAnyCategory(emptyList(), Set.of("gehoben"));
        assertEquals(0, result);
    }

    @Test
    void caseInsensitive() {
        Restaurant badischerHof = new Restaurant("Badischer Hof", 4.0, 25,
                List.of("Gutbürgerlich", "REGIONAL"));
        Restaurant schwanen = new Restaurant("Zum Schwanen", 3.6, 35,
                List.of("Deftig", "Regional"));

        var restaurants = List.of(
                royalPalace, bibis, mikesSteakhouse, hirsch, hofgut, schluessel,
                sol, schlossMorgental, ochsen, kloster, badischerHof, schwanen);

        var result = restaurantsWithAnyCategory(restaurants, Set.of("regional"));
        assertEquals(5, result);

        result = restaurantsWithAnyCategory(restaurants, Set.of("traditionell", "deftig"));
        assertEquals(4, result);
    }
}
