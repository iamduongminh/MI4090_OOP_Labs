public class Projector extends Device {
    // Attributes:
    private int brightness;
    private int lampHours;

    // Constructor:
    public Projector(String deviceId, String deviceName, int yearInUse, long price, DeviceStatus status,
            int brightness, int lampHours) {
        super(deviceId, deviceName, yearInUse, price, status);
        setBrightness(brightness);
        setLampHours(lampHours);
    }

    // Helper Methods:
    private void validateBrightness(int brightness) {
        if (brightness <= 0) {
            throw new IllegalArgumentException("Do sang phai lon hon 0 lumen.");
        }
    }

    private void validateLampHours(int lampHours) {
        if (lampHours < 0) {
            throw new IllegalArgumentException("So gio su dung bong den khong duoc am.");
        }
    }

    // Overrided Methods:
    @Override
    public long calculateAnnualMaintenanceCost() {
        long cost = (long) (this.getPrice() * 0.03);
        if (this.lampHours > 3000) {
            cost += 1_500_000;
        }
        return cost;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(
                ", %d lumen, bong den: %,d gio",
                brightness, lampHours);
    }

    // Getters:
    public int getBrightness() {
        return this.brightness;
    }

    public int getLampHours() {
        return this.lampHours;
    }

    // Setters:
    public void setBrightness(int brightness) {
        validateBrightness(brightness);
        this.brightness = brightness;
    }

    public void setLampHours(int lampHours) {
        validateLampHours(lampHours);
        this.lampHours = lampHours;
    }
}
