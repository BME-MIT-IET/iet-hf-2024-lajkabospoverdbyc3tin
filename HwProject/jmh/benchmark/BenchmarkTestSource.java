package benchmark;

import HwProject.*;

public class BenchmarkTestSource extends HwProject.Source {
    private boolean pushWaterCalled = false;

    @Override
    protected void pushWater() {
        pushWaterCalled = true;
        super.pushWater();
    }

    public boolean isPushWaterCalled() {
        return pushWaterCalled;
    }

    public static void setInstanceNr(int i) {
        instanceNr = i;
    }
}
