import static ch.trick17.turtle4j.TurtleGraphics.*;

public class TurtleFit {

    public static void main(String[] args) {
        int[] steps = {
                8921, 7578, 8211, 13641, 2786, 4980, 8002,
                7630, 5593, 9164, 6234, 9714, 10051, 7619};
        int goal = 7500;
        double plotWidth = 400;

        double[] percentages = convertToPercentages(steps, goal);
        plotPercentages(percentages, plotWidth);
    }

    public static double[] convertToPercentages(int[] steps, int goal) {
        double[] result = new double[steps.length];
        for (int i = 0; i < steps.length; i++) {
            result[i] = steps[i] * 100.0 / goal;
        }
        return result;
    }

    public static void plotPercentages(double[] data, double plotWidth) {
        setSpeed(1000);
        penUp();
        left(90);
        forward(plotWidth / 2);
        right(90);
        back(50);
        penDown();

        double gap = plotWidth / (data.length - 1);
        setPenWidth(gap * 0.6);
        for (double x : data) {
            if (x >= 100) {
                setPenColor("#38BE75");
            } else {
                setPenColor("#BAC2D0");
            }
            forward(x);
            back(x);

            penUp();
            right(90);
            forward(gap);
            left(90);
            penDown();
        }

        setPenWidth(1);
        setPenColor("black");
        left(90);
        forward(plotWidth + 2 * gap);
    }
}
