package HwProject.tests.HelperClasses;
import src.*;
public class TestSaboteur extends Saboteur {

    public TestSaboteur(String ID, Field position) {
        super(ID, position);
    }

    public Field getPosition() {
        return position;
    }
}
