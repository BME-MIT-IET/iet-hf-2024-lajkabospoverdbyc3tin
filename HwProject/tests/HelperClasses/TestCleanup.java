package HwProject.tests.HelperClasses;

import tests.HelperClasses.TestPipe;

public class TestCleanup {

    public static void cleanup() {
        TestPump.setInstanceNr(0);
        TestPipe.setInstanceNr(0);
        TestSource.setInstanceNr(0);
        TestDrain.setInstanceNr(0);
    }
}
