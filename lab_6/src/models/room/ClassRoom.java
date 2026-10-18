package models.room;

import models.device.Device;
import enums.DeviceStatus;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class ClassRoom {
    // Attributes:
    private String roomId;
    private String roomName;
    private int capacity;
    private List<Device> devices;

    // Constructor:
    public ClassRoom(String roomId, String roomName, int capacity) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.capacity = capacity;
        this.devices = new ArrayList<>();
    }

    // Methods:
    public void addDevice(Device device) {
        // Không chấp nhận đối tượng thiết bị null.
        if (device == null) {
            throw new IllegalArgumentException("Thiet bi khong duoc null.");
        }

        // Không thêm hai thiết bị có cùng mã vào một phòng.
        for (Device d : devices) {
            if (d.getDeviceId().equals(device.getDeviceId())) {
                throw new IllegalArgumentException(
                        "Thiet bi voi ma " + device.getDeviceId() + " da ton tai trong phong.");
            }
        }
        devices.add(device);
    }

    public boolean removeDevice(String deviceId) {
        return devices.removeIf(d -> d.getDeviceId().equals(deviceId));
    }

    public Device findDevice(String deviceId) {
        for (Device d : devices) {
            if (d.getDeviceId().equals(deviceId))
                return d;
        }
        return null;
    }

    public long calculateAnnualMaintenanceCost() {
        long total = 0;
        for (Device d : devices) {
            total += d.calculateAnnualMaintenanceCost();
        }
        return total;
    }

    public List<Device> getDevicesRequiringMaintenance() {
        List<Device> requiresMaintenance = new ArrayList<>();
        int currentYear = Year.now().getValue();
        for (Device d : devices) {
            if (d.getStatus() == DeviceStatus.UnderMaintenance ||
                    (currentYear - d.getYearInUse()) > 5) {
                requiresMaintenance.add(d);
            }
        }
        return requiresMaintenance;
    }
}