package models.device;

import enums.DeviceStatus;

public class AirConditionerRemote extends TeachingDevice {
    // Constructor:
    public AirConditionerRemote(String deviceId, String deviceName, int yearInUse, long purchasePrice,
            DeviceStatus status, int maxBorrowDays) {
        super(deviceId, deviceName, yearInUse, purchasePrice, status, maxBorrowDays);
    }

    // Overrided Method:
    @Override
    public long calculateAnnualMaintenanceCost() {
        return (long) (getPurchasePrice() * 0.05);
    }
}