package models.device;
import enums.DeviceStatus;
import java.util.Set;

public class AirConditionerRemote extends TeachingDevice {
    private final Set<String> compatibleRooms;

    public AirConditionerRemote(String id, String name, String storageLocation, Set<String> compatibleRooms) {
        super(id, name, storageLocation);
        if (compatibleRooms == null || compatibleRooms.isEmpty()) {
            throw new IllegalArgumentException("Phai co it nhat mot phong tuong thich.");
        }
        this.compatibleRooms = compatibleRooms;
    }

    public boolean isCompatibleWith(String roomId) {
        return compatibleRooms.contains(roomId);
    }

    @Override
    public boolean isReadyForUse() {
        return this.status == DeviceStatus.Available;
    }

    @Override
    public double getDefaultPriorityScore() {
        return 100.0; 
    }
}