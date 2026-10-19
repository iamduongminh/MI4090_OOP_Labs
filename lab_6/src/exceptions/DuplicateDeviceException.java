package exceptions;

public class DuplicateDeviceException extends BorrowingException {
    public DuplicateDeviceException(String deviceId) {
        super("Thiet bi " + deviceId + " da ton tai trong phieu muon.");
    }
}
