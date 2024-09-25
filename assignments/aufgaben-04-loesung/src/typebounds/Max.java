package typebounds;

import java.util.List;

public class Max {

    public static void main(String[] args) {
        List<Integer> points = List.of(117, 89, 105, 109, 78, 92, 114, 67);
        int maxPoints = findMax(points);
        System.out.println(maxPoints);

        List<Double> grades = List.of(5.3, 4.6, 3.9, 2.8, 5.7, 5.6, 4.5, 5.1);
        double maxGrade = findMax(grades);
        System.out.println(maxGrade);
    }

    public static <E extends Number> E findMax(Iterable<E> points) {
        E max = null;
        for (E current : points) {
            if (max == null || current.doubleValue() > max.doubleValue()) {
                max = current;
            }
        }
        return max;
    }
}
