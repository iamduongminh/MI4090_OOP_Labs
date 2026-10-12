import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class LabRoom {

    // Attributes:
    private String roomId;
    private String roomName;
    private int capacity;
    private List<Device> devices;

    // Constructor:
    public LabRoom(String roomId, String roomName, int capacity) {
        if (roomId == null || roomId.trim().isEmpty()) {
            throw new IllegalArgumentException("Ma phong khong duoc rong.");
        }
        if (capacity <= 0) {
            throw new IllegalArgumentException("Suc chua phai lon hon 0.");
        }
        this.roomId = roomId;
        this.roomName = roomName;
        this.capacity = capacity;
        this.devices = new ArrayList<>();
    }

    // Methods:

    public void addDevice(Device device) {
        // Khong chap nhan null.
        if (device == null) {
            throw new IllegalArgumentException("Thiet bi khong duoc null.");
        }
        for (Device d : devices) {
            // Khong them 2 thiet bi cung ma
            if (d.getDeviceId().equals(device.getDeviceId())) {
                throw new IllegalArgumentException(
                        "Thiet bi voi ma '" + device.getDeviceId() + "' da ton tai trong phong.");
            }
        }
        devices.add(device);
    }

    public boolean removeDevice(String deviceId) {
        return devices.removeIf(d -> d.getDeviceId().equals(deviceId));
    }

    public Device findDevice(String deviceId) {
        for (Device d : devices) {
            if (d.getDeviceId().equals(deviceId)) {
                return d;
            }
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
        int currentYear = Year.now().getValue();
        List<Device> result = new ArrayList<>();
        for (Device d : devices) {
            boolean underMaintenance = d.getDeviceStatus() == DeviceStatus.UnderMaintenance;
            boolean oldDevice = (currentYear - d.getYearInUse()) > 5;
            if (underMaintenance || oldDevice) {
                result.add(d);
            }
        }
        return result;
    }

    // Getters:
    public String getRoomId() {
        return this.roomId;
    }

    public String getRoomName() {
        return this.roomName;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public List<Device> getDevices() {
        return this.devices;
    }

    // Setters:
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setCapacity(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Suc chua phai lon hon 0.");
        }
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return String.format("Phong %s, suc chua: %d, so thiet bi: %d",
                roomId, roomName, capacity, devices.size());
    }
}
