package models.borrow;

import interfaces.IBorrower;
import enums.DeviceStatus;
import exceptions.BorrowLimitExceededException;
import exceptions.DeviceNotInSlipException;
import exceptions.DeviceUnavailableException;
import exceptions.DuplicateDeviceException;
import exceptions.InvalidBorrowPeriodException;
import models.device.TeachingDevice;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BorrowingSlip {
    // Attributes:
    private String slipId;
    private IBorrower borrower;
    private String teachingRoom;
    private LocalDate borrowedDate;
    private List<BorrowItem> items;

    // Constructor:
    public BorrowingSlip(String slipId, IBorrower borrower, String teachingRoom, LocalDate borrowedDate) {
        // SlipId, Borrower và TeachingRoom phải hợp lệ.
        if (slipId == null || borrower == null || teachingRoom == null) {
            throw new IllegalArgumentException("Thong tin phieu muon khong hop le.");
        }
        this.slipId = slipId;
        this.borrower = borrower;
        this.teachingRoom = teachingRoom;
        this.borrowedDate = borrowedDate;
        this.items = new ArrayList<>();
    }

    // Helper Method:
    private void validateBorrowing(TeachingDevice device, int borrowDays) {
        // device không được null.
        if (device == null)
            throw new IllegalArgumentException("Thiet bi khong duoc null");

        // borrowDays phải lớn hơn 0.
        if (borrowDays <= 0)
            throw new InvalidBorrowPeriodException(device.getDeviceId(), borrowDays, device.getMaxBorrowDays());

        // Không được mượn lâu hơn thời gian quy định.
        if (borrowDays > device.getMaxBorrowDays())
            throw new InvalidBorrowPeriodException(device.getDeviceId(), borrowDays, device.getMaxBorrowDays());

        // Thiết bị đang Available.
        if (device.getStatus() != DeviceStatus.Available) {
            throw new DeviceUnavailableException(device.getDeviceId(), device.getStatus());
        }

        // Người mượn chưa đạt giới hạn thiết bị.
        if (items.size() >= borrower.getMaximumDevices()) {
            throw new BorrowLimitExceededException(borrower.getBorrowerId(), borrower.getMaximumDevices());
        }

        for (BorrowItem item : items) {
            if (item.getDevice().getDeviceId().equals(device.getDeviceId())) {
                throw new DuplicateDeviceException(device.getDeviceId());
            }
        }
    }

    // Methods:
    public void addDevice(TeachingDevice device, int borrowDays) {
        validateBorrowing(device, borrowDays);

        LocalDate dueDate = borrowedDate.plusDays(borrowDays);
        BorrowItem item = new BorrowItem(device, borrowedDate, dueDate);

        device.markAsBorrowed();
        items.add(item);
    }

    public long returnDevice(String deviceId, LocalDate returnedDate) {
        BorrowItem foundItem = null;
        for (BorrowItem item : items) {
            if (item.getDevice().getDeviceId().equals(deviceId)) {
                foundItem = item;
                break;
            }
        }

        if (foundItem == null)
            throw new DeviceNotInSlipException(deviceId);

        foundItem.returnDevice(returnedDate);
        foundItem.getDevice().markAsAvailable();

        long lateFee = 0;
        if (returnedDate.isAfter(foundItem.getDueDate())) {
            long daysLate = ChronoUnit.DAYS.between(foundItem.getDueDate(), returnedDate);
            lateFee = daysLate * 50000;
        }
        return lateFee;
    }

    public List<BorrowItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public IBorrower getBorrower() {
        return borrower;
    }
}