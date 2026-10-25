package strategy;
import java.util.List;
import exceptions.NoSuitableItemException;

public interface ISelectionStrategy<T> {
    T select(List<T> candidates) throws NoSuitableItemException;
}