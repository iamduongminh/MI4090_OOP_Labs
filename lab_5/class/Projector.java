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
