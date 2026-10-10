import java.time.Year;

public class Computer extends Device implements INetworkable {

    // Attributes:
    private int ramCapacity;
    private String cpuType;
    private boolean dedicatedGPU;

    private String ipAddress;
    private boolean isConnected;

    // Constructor:
    public Computer(String deviceId, String deviceName, int yearInUse, long price, DeviceStatus status,
            int ramCapacity, String cpuType, boolean dedicatedGPU) {
        super(deviceId, deviceName, yearInUse, price, status);
        this.ramCapacity = ramCapacity;
        this.cpuType = cpuType;
        this.dedicatedGPU = dedicatedGPU;
    }

    // Overrided Methods:

    @Override
    // Override Abstract Method ở class cha Device
    public long calculateAnnualMaintenanceCost() {
        double percentage = 0.05;
        if (this.hasDedicatedGPU() == true) {
            percentage += 0.02;
        }

        int currentYear = Year.now().getValue();

        if (currentYear - this.getYearInUse() > 5) {
            percentage += 0.01;
        }
        return (long) (this.getPrice() * percentage);
    }

    @Override
    public String toString() {
        return super.toString() + String.format("%d GB, %s, GPU: %b", ramCapacity, cpuType, dedicatedGPU);
    }

    // Interface Contract Methods:

    @Override
    public String ipAddress() {
        return ipAddress;
    }

    @Override
    public void connect(String ipAddress) {
        // Địa chỉ IP không được rỗng.
        if (ipAddress == null || ipAddress.trim().isEmpty()) {
            throw new IllegalArgumentException("IP address can not empty");
        }

        // Không cho phép kết nối lại khi thiết bị vẫn đang có kết nối.
        if (isConnected == true) {
            throw new IllegalStateException("Can't connect 2 IP addresses");
        }

        this.ipAddress = ipAddress;
        this.isConnected = true;
    }

    @Override
    public void disconnect() {
        this.ipAddress = null;
        this.isConnected = false;

        System.out.println("Disconnected");
    }

    @Override
    public boolean isConnected() {
        return isConnected;
    }

    // Getters:

    public int getRamCapacity() {
        return ramCapacity;
    }

    public String getCpuType() {
        return cpuType;
    }

    public boolean hasDedicatedGPU() {
        return dedicatedGPU;
    }

    // Setters:

    public void setRamCapacity(int ramCapacity) {
        this.ramCapacity = ramCapacity;
    }

    public void setCpuType(String cpuType) {
        this.cpuType = cpuType;
    }

    public void setDedicatedGPU(boolean dedicatedGPU) {
        this.dedicatedGPU = dedicatedGPU;
    }
}