package exceptions;

public class InvalidBorrowPeriodException extends BorrowingException {
    public InvalidBorrowPeriodException(String deviceId, int requestedDays, int maxDays) {
        super("Thoi gian muon thiet bi " + deviceId + ". Yeu cau: " + requestedDays + " ngay, Toi da: " + maxDays
                + " ngay.");
    }
}