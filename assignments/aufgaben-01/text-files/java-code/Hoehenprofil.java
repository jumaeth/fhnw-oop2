import static ch.trick17.turtle4j.TurtleGraphics.*;

public class Hoehenprofil {

    public static void main(String[] args) {
        double pxProHm = 0.5;
        double pxProKm = 10;

        // Wanderroute von Basel nach Brugg
        double[] hoehen = {
                285.7, 276.2, 270.1, 269.4, 247.5, 250.0, 256.4, 256.2, 255.3, 256.3,
                260.2, 258.5, 256.0, 257.3, 258.1, 257.7, 256.2, 262.8, 265.5, 261.7,
                269.4, 273.6, 280.6, 295.5, 285.7, 279.5, 280.4, 280.7, 300.9, 300.5,
                326.8, 389.5, 385.2, 384.2, 427.7, 451.5, 447.6, 468.4, 493.0, 468.8,
                461.6, 406.9, 365.7, 355.8, 360.8, 352.7, 357.1, 365.1, 367.9, 373.1,
                379.5, 396.6, 404.3, 413.7, 425.1, 433.3, 463.4, 524.2, 617.5, 629.8,
                618.1, 555.7, 458.3, 357.1, 360.0, 345.3, 343.4, 381.3, 418.8, 441.2,
                455.1, 486.4, 473.7, 529.7, 552.8, 594.4, 613.2, 589.5, 579.7, 608.5,
                597.8, 543.5, 534.7, 545.5, 495.4, 547.8, 577.6, 524.7, 488.5, 497.7,
                462.3, 398.6, 415.7, 358.3, 350.2, 352.1};
        double[] distanzen = {
                0.24, 0.45, 0.48, 0.47, 0.71, 0.69, 0.67, 1.35, 1.54, 0.80,
                0.41, 0.32, 0.55, 0.45, 0.56, 0.82, 0.44, 0.54, 0.57, 0.45,
                0.46, 0.63, 0.40, 0.69, 0.64, 0.52, 0.61, 0.47, 0.68, 0.31,
                0.63, 0.46, 0.47, 0.36, 0.51, 0.41, 0.54, 0.44, 0.61, 0.62,
                0.23, 0.34, 0.23, 0.62, 0.28, 0.49, 0.46, 0.49, 0.45, 0.43,
                0.71, 0.71, 0.76, 0.71, 0.49, 0.59, 1.17, 0.64, 0.55, 0.52,
                0.80, 1.06, 1.03, 0.70, 0.42, 0.38, 0.45, 0.24, 0.11, 0.56,
                0.48, 0.78, 0.65, 0.40, 0.77, 0.87, 1.21, 1.16, 0.62, 0.37,
                0.42, 0.73, 0.43, 0.63, 0.54, 0.29, 0.75, 0.97, 0.96, 0.39,
                0.49, 0.99, 0.46, 0.33, 0.45}; // 1 Wert weniger, da *zwischen* den Höhenangaben

        setSpeed(1000);
        setPenWidth(2);
        setPenColor("#4285F4");
        penUp();
        back(100);
        right(90);
        back(250);
        left(90);
        int offset = min(hoehen);
        forward((hoehen[0] - offset) * pxProHm);
        penDown();
        right(90);

        for (int i = 0; i < hoehen.length - 1; i++) {
            forward(distanzen[i] * pxProKm);
            left(90);
            forward((hoehen[i + 1] - hoehen[i]) * pxProHm);
            right(90);
        }
    }

    public static int min(double[] array) {
        int min = Integer.MAX_VALUE;
        for (double h : array) {
            if (h < min) {
                min = (int) h;
            }
        }
        return min;
    }
}
