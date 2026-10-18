package models.device;

import enums.DeviceStatus;

public abstract class TeachingDevice extends Device {
    // Attribute:
    private int maxBorrowDays;

    // Constructor:
    public TeachingDevice(String deviceId, String deviceName, int yearInUse, long purchasePrice, DeviceStatus status,
            int maxBorrowDays) {
        super(deviceId, deviceName, yearInUse, purchasePrice, status);
        if (maxBorrowDays <= 0) {
            throw new IllegalArgumentException("So ngay muon phai lon hon 0.");
        }
        this.maxBorrowDays = maxBorrowDays;
    }

    // Getter:
    public int getMaxBorrowDays() {
        return maxBorrowDays;
    }

    // Setters:
    public void markAsBorrowed() {
        setStatus(DeviceStatus.Borrowed);
    }

    public void markAsAvailable() {
        setStatus(DeviceStatus.Available);
    }
}