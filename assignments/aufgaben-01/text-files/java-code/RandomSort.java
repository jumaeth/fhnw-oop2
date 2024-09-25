import java.util.Arrays;
import java.util.Random;

public class RandomSort {

    public static void main(String[] args) {
        int[] array = { 1, 2, 6, 5, 3, 4, 7, 9, 8, 0 };
        System.out.println(Arrays.toString(array));

        int swaps = randomSort(array);
        System.out.println("Sortiert: " + Arrays.toString(array));
        System.out.println(swaps + " mal getauscht");
    }

    /**
     * Gibt true zurück, falls das gegebene Array aufsteigend
     * sortiert ist; andernfalls false.
     */
    public static boolean isSorted(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Sortiert das gegebene Array, indem wiederholt zufällig
     * Zahlenpaare im Array ausgewählt und in die richtige
     * Reihenfolge gebracht werden. Nach jedem Tausch wird
     * mittels isSorted geprüft, ob das Array bereits sortiert
     * ist. Gibt die Anzahl erfolgter Tauschoperationen zurück.
     */
    public static int randomSort(int[] array) {
        Random random = new Random();
        int swaps = 0;
        while (!isSorted(array)) {
            int i = random.nextInt(array.length);
            int j = random.nextInt(array.length);
            int lo = Math.min(i, j);
            int hi = Math.max(i, j);
            if (array[lo] > array[hi]) {
                int t = array[lo];
                array[lo] = array[hi];
                array[hi] = t;
                swaps++;
            }
        }
        return swaps;
    }
}
