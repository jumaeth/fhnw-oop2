package radiobuttontest;

import guilib.Application;
import guilib.Group;
import guilib.RadioButton;
import guilib.RadioGroup;

public class RadioButtonTester extends Application {

    public static void main(String[] args) {
            new RadioButtonTester().start("Radio Button Tester", 300, 70);
    }

    @Override
    protected Group createContent() {
        var chf = new RadioButton( 20, 20, "CHF");
        var eur = new RadioButton(120, 20, "EUR");
        var usd = new RadioButton(220, 20, "USD");

        var radioGroup = new RadioGroup();
        chf.setGroup(radioGroup);
        eur.setGroup(radioGroup);
        usd.setGroup(radioGroup);

        chf.setOnSelectedChanged(()-> {
            System.out.println("CHF " + (chf.isSelected() ? "selected" : "unselected"));
        });
        eur.setOnSelectedChanged(()-> {
            System.out.println("EUR " + (eur.isSelected() ? "selected" : "unselected"));
        });
        usd.setOnSelectedChanged(()-> {
            System.out.println("USD " + (usd.isSelected() ? "selected" : "unselected"));
        });

        return new Group(chf, eur, usd);
    }
}
