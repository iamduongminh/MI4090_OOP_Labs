package models.borrow;

import models.device.Device;
import models.device.TeachingDevice;

import java.time.LocalDate;

import exceptions.DeviceAlreadyReturnedException;

public class BorrowItem {
    // Attributes:
    private TeachingDevice device;
    private LocalDate borrowedDate;
    private LocalDate dueDate;
    private LocalDate returnedDate;

    // Constructor:
    public BorrowItem(TeachingDevice device, LocalDate borrowedDate, LocalDate dueDate) {
        // Device không được null.
        if (device == null)
            throw new IllegalArgumentException("Device khong duoc null");

        // DueDate phải sau hoặc bằng BorrowedDate.
        if (dueDate.isBefore(borrowedDate))
            throw new IllegalArgumentException("Ngay den han phai sau ngay muon");

        this.device = device;
        this.borrowedDate = borrowedDate;
        this.dueDate = dueDate;
        this.returnedDate = null;
    }

    public void returnDevice(LocalDate returnedDate) {
        // ReturnedDate ban đầu là null.
        if (this.returnedDate != null) {
            throw new DeviceAlreadyReturnedException(device.getDeviceId());
        }
        // Ngày trả không được trước ngày mượn.
        if (returnedDate.isBefore(borrowedDate)) {
            throw new IllegalArgumentException("Ngay den han phai sau ngay muon");
        }
        this.returnedDate = returnedDate;
    }

    public TeachingDevice getDevice() {
        return device;
    }

    public LocalDate getBorrowedDate() {
        return borrowedDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getReturnedDate() {
        return returnedDate;
    }

    public boolean isReturned() {
        return returnedDate != null;
    }
}
