package strategy;
import java.util.List;
import exceptions.NoSuitableItemException;

public class FirstAvailableStrategy<T> implements ISelectionStrategy<T> {
    @Override
    public T select(List<T> candidates) throws NoSuitableItemException {
        if (candidates == null || candidates.isEmpty()) {
            throw new NoSuitableItemException();
        }
        return candidates.get(0);
    }
}