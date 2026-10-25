package strategy;
import java.util.List;
import java.util.function.Function;
import exceptions.NoSuitableItemException;

public class HighestPriorityStrategy<T> implements ISelectionStrategy<T> {
    private final Function<T, Double> scoreSelector;

    public HighestPriorityStrategy(Function<T, Double> scoreSelector) {
        this.scoreSelector = scoreSelector;
    }

    @Override
    public T select(List<T> candidates) throws NoSuitableItemException {
        if (candidates == null || candidates.isEmpty()) {
            throw new NoSuitableItemException();
        }

        T bestCandidate = candidates.get(0);
        double maxScore = scoreSelector.apply(bestCandidate);

        for (int i = 1; i < candidates.size(); i++) {
            T current = candidates.get(i);
            double currentScore = scoreSelector.apply(current);
            if (currentScore > maxScore) {
                bestCandidate = current;
                maxScore = currentScore;
            }
        }
        return bestCandidate;
    }
}