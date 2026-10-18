package models.device;

import enums.DeviceStatus;
import java.time.Year;

public abstract class Device {
    // Attributes:
    private final String deviceId;
    private String deviceName;
    private int yearInUse;
    private long purchasePrice;
    private DeviceStatus status;

    // Contructor:
    public Device(String deviceId, String deviceName, int yearInUse, long purchasePrice, DeviceStatus status) {
        // Mã thiết bị không được rỗng.
        if (deviceId == null || deviceId.trim().isEmpty()) {
            throw new IllegalArgumentException("Ma thiet bi khong duoc rong.");
        }

        // Giá mua phải lớn hơn 0.
        if (purchasePrice <= 0) {
            throw new IllegalArgumentException("Gia mua phai lon hon 0.");
        }

        // Năm đưa vào sử dụng không được lớn hơn năm hiện tại.
        if (yearInUse > Year.now().getValue()) {
            throw new IllegalArgumentException("Nam dua vao su dung khong duoc lon hon nam hien tai.");
        }

        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.yearInUse = yearInUse;
        this.purchasePrice = purchasePrice;
        this.status = status;
    }

    // Abtract Method:
    public abstract long calculateAnnualMaintenanceCost();

    // Getters:
    public String getDeviceId() {
        return deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public int getYearInUse() {
        return yearInUse;
    }

    public long getPurchasePrice() {
        return purchasePrice;
    }

    public DeviceStatus getStatus() {
        return status;
    }

    // Setters:
    public void setStatus(DeviceStatus status) {
        this.status = status;
    }

    // Override Method:
    @Override
    public String toString() {
        return String.format("[%s] %s - Nam: %d - Gia: %,d - Trang thai: %s",
                deviceId, deviceName, yearInUse, purchasePrice, status);
    }
}