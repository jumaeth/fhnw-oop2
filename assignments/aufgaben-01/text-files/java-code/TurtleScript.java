import java.util.Random;
import java.util.Scanner;

import static ch.trick17.turtle4j.TurtleGraphics.*;

// Example script: F 50 R 90 F 20 U F 10 D F 20 L 90 F 50
public class TurtleScript {

    public static void main(String[] args) {
        setSpeed(500);
        Scanner inputScanner = new Scanner(System.in);

        System.out.print("turtle> ");
        var script = inputScanner.nextLine();

        while (true) {
            if (script.isEmpty()) {
                Random random = new Random();
                int length = random.nextInt(50, 200);
                for (int i = 1; i <= length; i++) {
                    int op = random.nextInt(6);
                    if (op == 0) {
                        if (random.nextBoolean()) { // smaller probability for up
                            script += "U ";
                        }
                    } else if (op == 1) {
                        script += "D ";
                    } else if (op == 2) {
                        script += "F ";
                        script += random.nextInt(1, 6) * 20 + " ";
                    } else if (op == 3) {
                        script += "B ";
                        script += random.nextInt(1, 6) * 20 + " ";
                    } else if (op == 4) {
                        script += "L ";
                        script += random.nextInt(1, 7) * 30 + " ";
                    } else {
                        script += "R ";
                        script += random.nextInt(1, 7) * 30 + " ";
                    }
                }
                System.out.println("random script: " + script);
            }

            Scanner scriptScanner = new Scanner(script);
            while (scriptScanner.hasNext()) {
                String next = scriptScanner.next();
                if (next.equals("F")) {
                    int distance = scriptScanner.nextInt();
                    forward(distance);
                } else if (next.equals("B")) {
                    int distance = scriptScanner.nextInt();
                    back(distance);
                } else if (next.equals("L")) {
                    int angle = scriptScanner.nextInt();
                    left(angle);
                } else if (next.equals("R")) {
                    int angle = scriptScanner.nextInt();
                    right(angle);
                } else if (next.equals("U")) {
                    penUp();
                } else if (next.equals("D")) {
                    penDown();
                } else {
                    System.out.println("invalid command: " + next);
                }
            }

            System.out.print("turtle> ");
            script = inputScanner.nextLine();
        }
    }
}
