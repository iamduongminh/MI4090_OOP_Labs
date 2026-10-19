package exceptions;

import enums.DeviceStatus;

public class DeviceUnavailableException extends BorrowingException {
    private String deviceId;
    private DeviceStatus currentStatus;

    public DeviceUnavailableException(String deviceId, DeviceStatus currentStatus) {
        super("Thiet bi " + deviceId + " khong san sang. Trang thai hien tai: " + currentStatus);
        this.deviceId = deviceId;
        this.currentStatus = currentStatus;
    }
}
