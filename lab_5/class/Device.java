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

    // 4. Mã thiết bị chỉ được thiết lập khi khởi tạo.

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public void setYearInUse(int yearInUse) {
        // 3. Năm đưa vào sử dụng không được lớn hơn năm hiện tại.
        int currentYear = Year.now().getValue();
        if (yearInUse > currentYear) {
            throw new IllegalArgumentException("Nam dua vao su dung ko dc lon hon nam hien tai.");
        }

        this.yearInUse = yearInUse;
    }

    public void setPrice(long price) {
        // 2. Giá mua phải lớn hơn 0.
        if (price <= 0) {
            throw new IllegalArgumentException("Gia mua phai lon hon 0.");
        }
        this.price = price;
    }

    public void setDeviceStatus(DeviceStatus status) {
        this.status = status;
    }

    // Contructor:
    public Device(String deviceId, String deviceName, int yearInUse, long price, DeviceStatus status) {
        // 1. Mã thiết bị không được rỗng.
        if (deviceId == null || deviceId.trim().isEmpty()) {
            throw new IllegalArgumentException("Ma thiet bi ko dc rong.");
        }
        this.deviceId = deviceId;
        setDeviceName(deviceName);
        setYearInUse(yearInUse);
        setPrice(price);
        setDeviceStatus(status);
    }

    // Methods:
    // 5. Cung cấp phương thức trừu tượng tính chi phí bảo trì dự kiến trong một
    // năm.
    public abstract long calculateAnnualMaintenanceCost();

    // 6. Ghi đè phương thức biểu diễn thông tin của thiết bị dưới dạng chuỗi của
    // Object class.

    @Override

    public String toString() {
        return String.format("%s, %s, %d, %d VND, %s", deviceId, deviceName, yearInUse, price, status);
    }
}