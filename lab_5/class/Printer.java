public class Printer extends Device {

    // Attributes:
    private PrinterType printerType;
    private long pageCount;
    private boolean isColor;
    private boolean hasNetworkSupport;

    // Constructor:
    public Printer(String deviceId, String deviceName, int yearInUse, long price, DeviceStatus status,
            PrinterType printerType, long pageCount, boolean isColor, boolean hasNetworkSupport) {
        super(deviceId, deviceName, yearInUse, price, status);
        this.printerType = validatePrinterType(printerType);
        this.pageCount = validatePageCount(pageCount);
        this.isColor = isColor;
        this.hasNetworkSupport = hasNetworkSupport;
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
        this.printerType = printerType;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public void setHasNetworkSupport(boolean hasNetworkSupport) {
        this.hasNetworkSupport = hasNetworkSupport;
    }

    public void setColor(boolean isColor) {
        this.isColor = isColor;
    }

}