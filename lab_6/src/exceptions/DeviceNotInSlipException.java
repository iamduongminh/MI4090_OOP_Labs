package exceptions;

public class DeviceNotInSlipException extends BorrowingException {
    public DeviceNotInSlipException(String deviceId) {
        super("Yeu cau tra that bai. Thiet bi " + deviceId + " khong thuoc phieu muon nay.");
    }
}