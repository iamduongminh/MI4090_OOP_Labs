import java.time.Year;

public abstract class Device {
    // Attributes:
    private String deviceId;
    private String deviceName;
    private int yearInUse;
    private long price;
    private DeviceStatus status;

    // Getters:
    public String getDeviceId() {
        return deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public int getYearInUse() {
        return yearInUse;
    }

    public long getPrice() {
        return price;
    }

    public DeviceStatus getDeviceStatus() {
        return status;
    }

    // Setters:

    // Mã thiết bị chỉ được thiết lập khi khởi tạo.

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public void setYearInUse(int yearInUse) {
        // Năm đưa vào sử dụng không được lớn hơn năm hiện tại.
        int currentYear = Year.now().getValue();
        if (yearInUse > currentYear) {
            throw new IllegalArgumentException("Nam dua vao su dung khong dc lon hon nam hien tai.");
        }

        this.yearInUse = yearInUse;
    }

    public void setPrice(long price) {
        // Giá mua phải lớn hơn 0.
        if (price <= 0) {
            throw new IllegalArgumentException("Gia mua phai lon hon 0.");
        }
        this.price = price;
    }

    public void setDeviceStatus(DeviceStatus status) {
        this.status = status;
    }

    // Contructors:
    public Device(String deviceId, String deviceName, int yearInUse, long price, DeviceStatus status) {
        // Mã thiết bị không được rỗng.
    }

    // Method:
    public abstract long calculateAnnualMaintenanceCost();
}