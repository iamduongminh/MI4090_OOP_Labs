package exceptions;
import enums.DeviceStatus;

public class DeviceNotReadyException extends Exception {
    private final String deviceId;
    private final DeviceStatus currentStatus;

    public DeviceNotReadyException(String deviceId, DeviceStatus currentStatus) {
        super("Thiet bi " + deviceId + " khong san sang. Trang thai hien tai: " + currentStatus);
        this.deviceId = deviceId;
        this.currentStatus = currentStatus;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public DeviceStatus getCurrentStatus() {
        return currentStatus;
    }
}