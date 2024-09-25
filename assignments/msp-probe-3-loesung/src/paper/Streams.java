package paper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;

public class Streams {
    public static void main(String[] args) {
        var aula = List.of(
                new Sensor(0, "Temperatur", SensorType.TEMPERATURE),
                new Sensor(1, "Lichtsensor Fenster", SensorType.LIGHT),
                new Sensor(2, "Lichtsensor Tür", SensorType.LIGHT),
                new Lamp(3, "Deckenlampe"),
                new Lamp(4, "Deckenlampe"));
        var bibliothek = List.of(
                new Lamp(5, "Kleine Lampe"),
                new Lamp(6, "Deckenlampe"));
        var mensa = List.of(
                new Sensor(7, "Lichtsensor", SensorType.LIGHT),
                new Sensor(8, "CO2-Sensor", SensorType.CO2),
                new Lamp(9, "Deckenlampe 1"),
                new Lamp(10, "Deckenlampe 2"));

        Object result = aula.stream()
                .filter(d -> d instanceof Lamp)
                .map(d -> d.getName())
                .collect(toSet());
        System.out.println(result);


        result = Stream.of(aula, bibliothek, mensa)
                .filter(x -> x.size() < 3)
                .flatMap(x -> x.stream())
                .map(x -> x.getId())
                .toList();
        System.out.println(result);


        result = aula.stream()
                .filter(d -> d instanceof Sensor)
                .map(d -> (Sensor) d)
                .collect(groupingBy(Sensor::getType));
        System.out.println(((Map) result).keySet());


        var all = new ArrayList<Device>();
        all.addAll(aula);
        all.addAll(bibliothek);
        all.addAll(mensa);
        result = all.stream()
                .sorted(comparing(Device::getName))
                .limit(4)
                .map(d -> d.getName())
                .map(n -> n.charAt(0))
                .toList();
        System.out.println(result);
    }
}
