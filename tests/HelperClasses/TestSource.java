package tests.HelperClasses;

import src.Source;

public class TestSource extends Source {
    private boolean pushWaterCalled = false;

    @Override
    protected void PushWater() {
        pushWaterCalled = true;
        super.PushWater();
    }

    public boolean isPushWaterCalled() {
        return pushWaterCalled;
    }

    public static void setInstanceNr(int i) {
        instanceNr = i;
    }
}
