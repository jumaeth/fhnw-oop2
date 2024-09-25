package typebounds;

import java.util.List;

public class Max {

    public static void main(String[] args) {
        List<Integer> points = List.of(117, 89, 105, 109, 78, 92, 114, 67);
        int maxPoints = findMax(points);
        System.out.println(maxPoints);

        List<Double> grades = List.of(5.3, 4.6, 3.9, 2.8, 5.7, 5.6, 4.5, 5.1);
        double maxGrade = 0.0; // TODO: findMax(grades)???
        System.out.println(maxGrade);
    }

    public static Integer findMax(Iterable<Integer> points) {
        Integer max = null;
        for (int current : points) {
            if (max == null || current > max) {
                max = current;
            }
        }
        return max;
    }
}
