import java.time.Year;

public class Computer extends Device implements INetworkable {

    // Attributes:
    private int ramCapacity;
    private String cpuType;
    private boolean dedicatedGPU;

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

}
