import HwProject.*;

public class TestSource extends Source {
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
