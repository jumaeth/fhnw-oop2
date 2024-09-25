package stream;

import sort.Restaurant;

import java.util.List;

import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

public class Streams {

    public static void main(String[] args) {
        var palace = new Restaurant("Royal Palace", 4.9, 100,
                List.of("teuer", "gehoben", "modern"));
        var ochsen = new Restaurant("Zum Ochsen", 4.3, 70,
                List.of("gutbürgerlich", "traditionell", "teuer"));
        var bibis = new Restaurant("Bibis", 4.8, 45,
                List.of("vegetarisch", "gesund", "regional"));
        var mikes = new Restaurant("Mike's Steakhouse", 4.7, 50,
                List.of("teuer", "modern"));
        var sol = new Restaurant("Sol", 4.5, 20,
                List.of("vegan"));

        var restaurants = List.of(palace, ochsen, bibis, mikes, sol);

        System.out.println(restaurants.stream()
                .filter(r -> r.getStarRating() > 4.5)
                .map(r -> r.getName())
                .toList());

        System.out.println(restaurants.stream()
                .map(Restaurant::getName)
                .filter(name -> name.contains(" "))
                .map(name -> name.split(" ")[0])
                .map(String::length)
                .toList());

        System.out.println(restaurants.stream()
                .filter(r -> r.getCapacity() > 50)
                .flatMap(r -> r.getCategories().stream())
                .collect(toSet()));

        System.out.println(restaurants.stream()
                .filter(r -> r.getCategories().contains("teuer"))
                .collect(toMap(r -> r.getName(), r -> r.getStarRating())));

        System.out.println(restaurants.stream()
                .map(r -> r.getCategories().stream()
                        .filter(c -> !c.equals("teuer"))
                        .toList()
                        .get(0))
                .toList());
    }
}
