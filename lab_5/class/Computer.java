import java.time.Year;

public class Computer extends Device implements INetworkable {

    // Attributes:
    private int ramCapacity;
    private String cpuType;
    private boolean dedicatedGPU;

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

        if (this.getYearInUse() - currentYear > 5) {
            percentage += 0.01;
        }
        return (long) (this.getPrice() * percentage);
    }

    @Override
    public String ipAddress() {

    }

    @Override
    public void connect(String ipAddress) {

    }

    @Override
    public void disconnect() {

    }

    @Override
    public boolean isConnected() {

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
