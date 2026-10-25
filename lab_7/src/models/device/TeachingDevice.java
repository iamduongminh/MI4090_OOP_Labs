package models.device;
import interfaces.IIdentifiable;
import enums.DeviceStatus;
import exceptions.DeviceNotReadyException;

public abstract class TeachingDevice implements IIdentifiable {
    private final String id;
    private final String name;
    private final String storageLocation;
    protected DeviceStatus status;

    public TeachingDevice(String id, String name, String storageLocation) {
        if (id == null || id.trim().isEmpty() || name == null || name.trim().isEmpty() || storageLocation == null || storageLocation.trim().isEmpty()) {
            throw new IllegalArgumentException("Thong tin thiet bi khong hop le.");
        }
        this.id = id;
        this.name = name;
        this.storageLocation = storageLocation;
        this.status = DeviceStatus.Available;
    }

    @Override
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStorageLocation() {
        return storageLocation;
    }

    public DeviceStatus getStatus() {
        return status;
    }

    public abstract boolean isReadyForUse();
    public abstract double getDefaultPriorityScore();

    public void borrow() throws DeviceNotReadyException {
        if (!isReadyForUse()) {
            throw new DeviceNotReadyException(id, status);
        }
        this.status = DeviceStatus.Borrowed;
    }

    public void returnDevice() {
        if (this.status == DeviceStatus.Borrowed) {
            this.status = DeviceStatus.Available;
        } else {
            throw new IllegalStateException("Chi co the tra thiet bi dang duoc muon.");
        }
    }
    
    @Override
    public String toString() {
        return String.format("[%s] %s - Vi tri: %s - Trang thai: %s", id, name, storageLocation, status);
    }
}