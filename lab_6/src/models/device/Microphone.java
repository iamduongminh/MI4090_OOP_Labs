package models.device;

import enums.DeviceStatus;

public class Microphone extends TeachingDevice {
    // Constructor:
    public Microphone(String deviceId, String deviceName, int yearInUse, long purchasePrice, DeviceStatus status,
            int maxBorrowDays) {
        super(deviceId, deviceName, yearInUse, purchasePrice, status, maxBorrowDays);
    }

    // Overrided Method:
    @Override
    public long calculateAnnualMaintenanceCost() {
        return (long) (getPurchasePrice() * 0.08);
    }
}