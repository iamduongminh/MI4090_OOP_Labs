package models.user;

import interfaces.IBorrower;

public class TeachingAssistant implements IBorrower {
    // Attributes:
    private String borrowerId;
    private String fullName;

    // Constructor:
    public TeachingAssistant(String borrowerId, String fullName) {
        this.borrowerId = borrowerId;
        this.fullName = fullName;
    }

    // Overrided Methods:
    @Override
    public String getBorrowerId() {
        return borrowerId;
    }

    @Override
    public String getFullName() {
        return fullName;
    }

    @Override
    public int getMaximumDevices() {
        return 2;
    }
}