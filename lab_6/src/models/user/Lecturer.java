package models.user;

import interfaces.IBorrower;

public class Lecturer implements IBorrower {
    // Attributes:
    private String borrowerId;
    private String fullName;

    // Constructor:
    public Lecturer(String borrowerId, String fullName) {
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
        return 3;
    }
}