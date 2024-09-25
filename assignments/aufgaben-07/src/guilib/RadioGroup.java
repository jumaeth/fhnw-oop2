package guilib;

import java.util.ArrayList;
import java.util.List;

public class RadioGroup {

    private final List<RadioButton> group = new ArrayList<>();

    public void setFirstSelected() {
        if (!group.isEmpty()) {
            removeSelected();
            group.get(0).setSelected(true);
        }
    }

    public RadioButton getSelected() {
        for (RadioButton radioButton : group) {
            if (radioButton.isSelected()) return radioButton;
        }
        return null;
    }

    public List<RadioButton> getGroup() {
        return group;
    }

    boolean currentSelected(RadioButton current) {
        for (RadioButton button : group) {
            if (button.equals(current) && current.isSelected()) {
                return true;
            }
        }
        return false;
    }

    void removeSelected() {
        for (RadioButton radioButton : group) {
            radioButton.setSelected(false);
        }
    }

    void addRadioButton(RadioButton radioButton) {
        group.add(radioButton);
    }
}
