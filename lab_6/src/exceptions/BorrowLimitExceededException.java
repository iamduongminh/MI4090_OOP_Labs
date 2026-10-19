package exceptions;

public class BorrowLimitExceededException extends BorrowingException {
    private String borrowerId;
    private int maximumDevices;

    public BorrowLimitExceededException(String borrowerId, int maximumDevices) {
        super("Nguoi muon " + borrowerId + " da dat gioi han toi da: " + maximumDevices + " thiet bi.");
        this.borrowerId = borrowerId;
        this.maximumDevices = maximumDevices;
    }
}
