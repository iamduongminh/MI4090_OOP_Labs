package models.device;
import enums.DeviceStatus;

public class WirelessMicrophone extends TeachingDevice {
    private final int batteryLevel;
    private final int signalQuality;

    public WirelessMicrophone(String id, String name, String storageLocation, int batteryLevel, int signalQuality) {
        super(id, name, storageLocation);
        if (batteryLevel < 0 || batteryLevel > 100 || signalQuality < 0 || signalQuality > 100) {
            throw new IllegalArgumentException("Muc pin va chat luong tin hieu phai tu 0 den 100.");
        }
        this.batteryLevel = batteryLevel;
        this.signalQuality = signalQuality;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public int getSignalQuality() {
        return signalQuality;
    }

    @Override
    public boolean isReadyForUse() {
        return this.status == DeviceStatus.Available && this.batteryLevel >= 30 && this.signalQuality >= 40;
    }

    @Override
    public double getDefaultPriorityScore() {
        return (this.batteryLevel + this.signalQuality) / 2.0;
    }
}