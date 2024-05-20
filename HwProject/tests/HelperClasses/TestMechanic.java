package HwProject.tests.HelperClasses;

import HwProject.src.*;
public class TestMechanic extends Mechanic{

    public TestMechanic(String ID, Field position) {
        super(ID, position);
    }

    public Field getPosition() {
        return position;
    }
}
