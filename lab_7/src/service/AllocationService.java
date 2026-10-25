package service;
import models.device.TeachingDevice;
import repository.IRepository;
import strategy.ISelectionStrategy;
import java.util.List;
import java.util.function.Predicate;
import exceptions.DeviceNotReadyException;
import exceptions.NoSuitableItemException;
import java.util.ArrayList;

public class AllocationService<T extends TeachingDevice> {
    private final IRepository<T> repository;
    private ISelectionStrategy<T> strategy;

    public AllocationService(IRepository<T> repository, ISelectionStrategy<T> strategy) {
        this.repository = repository;
        this.strategy = strategy;
    }

    public void changeStrategy(ISelectionStrategy<T> newStrategy) {
        this.strategy = newStrategy;
    }

    public T allocate(Predicate<T> additionalCondition) throws NoSuitableItemException, DeviceNotReadyException {
        List<T> allDevices = repository.getAll();
        
        List<T> readyDevices = new ArrayList<>();
        for (T device : allDevices) {
            if (device.isReadyForUse()) {
                if (additionalCondition == null || additionalCondition.test(device)) {
                    readyDevices.add(device);
                }
            }
        }

        T selectedDevice = strategy.select(readyDevices);
        selectedDevice.borrow();
        return selectedDevice;
    }
}