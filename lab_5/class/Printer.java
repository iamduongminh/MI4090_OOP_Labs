public class Printer extends Device implements INetworkable {

    // Attributes:
    private PrinterType printerType;
    private long pageCount;
    private boolean isColor;
    private boolean hasNetworkSupport;

    // INetworkable state attributes:
    private String ipAddress;
    private boolean isConnected;

    // Constructor:
    public Printer(String deviceId, String deviceName, int yearInUse, long price, DeviceStatus status,
            PrinterType printerType, long pageCount, boolean isColor, boolean hasNetworkSupport) {
        super(deviceId, deviceName, yearInUse, price, status);
        this.printerType = validatePrinterType(printerType);
        this.pageCount = validatePageCount(pageCount);
        this.isColor = isColor;
        this.hasNetworkSupport = hasNetworkSupport;
        this.isConnected = false;
        this.ipAddress = null;
    }

    // Helper Methods:
    private PrinterType validatePrinterType(PrinterType printerType) {
        if (printerType == null) {
            throw new IllegalArgumentException("Loai may in khong duoc de null.");
        }
        return printerType;
    }

    private long validatePageCount(long pageCount) {
        if (pageCount < 0) {
            throw new IllegalArgumentException("So trang in khong duoc am.");
        }
        return pageCount;
    }

    // Overrided Methods:

    @Override
    public long calculateAnnualMaintenanceCost() {
        long cost = (long) (this.getPrice() * 0.04);
        if (this.pageCount > 100000) {
            cost += 500000;
        }
        if (this.isColor) {
            cost += 300000;
        }
        return cost;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(
                ", %s, %,d trang, network: %s, color: %s",
                printerType,
                pageCount,
                hasNetworkSupport ? "true" : "false",
                isColor ? "true" : "false");
    }

    // Interface Contract Methods:

    @Override
    public String getIpAddress() {
        return this.ipAddress;
    }

    @Override
    public void connect(String ipAddress) {
        // Dia chi IP khong duoc rong.
        if (ipAddress == null || ipAddress.trim().isEmpty()) {
            throw new IllegalArgumentException("IP address khong duoc de trong.");
        }

        // Khong cho phep ket noi lai khi thiet bi dang ket noi.
        if (this.isConnected) {
            throw new IllegalStateException(
                    "Thiet bi dang ket noi toi " + this.ipAddress + ", khong the ket noi them.");
        }

        // May in phai ho tro mang moi cho ket noi.
        if (!this.hasNetworkSupport) {
            throw new IllegalStateException("May in nay khong ho tro ket noi mang.");
        }

        this.ipAddress = ipAddress;
        this.isConnected = true;
    }

    @Override
    public void disconnect() {
        // Khong the ngat ket noi neu chua ket noi.
        if (!this.isConnected) {
            throw new IllegalStateException("Thiet bi hien chua ket noi mang, khong the ngat ket noi.");
        }

        this.ipAddress = null;
        this.isConnected = false;
    }

    @Override
    public boolean isConnected() {
        return this.isConnected;
    }

    // Getters:
    public PrinterType getPrinterType() {
        return this.printerType;
    }

    public long getPageCount() {
        return this.pageCount;
    }

    public boolean isHasNetworkSupport() {
        return this.hasNetworkSupport;
    }

    public boolean isColor() {
        return this.isColor;
    }

    // Setters:
    public void setPrinterType(PrinterType printerType) {
        this.printerType = validatePrinterType(printerType);
    }

    public void setPageCount(long pageCount) {
        this.pageCount = validatePageCount(pageCount);
    }

    public void setHasNetworkSupport(boolean hasNetworkSupport) {
        this.hasNetworkSupport = hasNetworkSupport;
    }

    public void setColor(boolean isColor) {
        this.isColor = isColor;
    }
}
