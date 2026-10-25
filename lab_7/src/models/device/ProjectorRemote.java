package models.device;
import enums.DeviceStatus;

public class ProjectorRemote extends TeachingDevice {
    private final int batteryLevel;

    public ProjectorRemote(String id, String name, String storageLocation, int batteryLevel) {
        super(id, name, storageLocation);
        if (batteryLevel < 0 || batteryLevel > 100) {
            throw new IllegalArgumentException("Muc pin phai tu 0 den 100.");
        }
        this.batteryLevel = batteryLevel;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    @Override
    public boolean isReadyForUse() {
        return this.status == DeviceStatus.Available && this.batteryLevel >= 20;
    }

    @Override
    public double getDefaultPriorityScore() {
        return this.batteryLevel;
    }
}