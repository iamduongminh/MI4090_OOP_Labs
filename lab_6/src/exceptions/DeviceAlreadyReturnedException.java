package exceptions;

public class DeviceAlreadyReturnedException extends BorrowingException {
    public DeviceAlreadyReturnedException(String deviceId) {
        super("Thiet bi " + deviceId + " da duoc tra truoc do.");
    }
}
