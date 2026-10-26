package repository;
import interfaces.IIdentifiable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import exceptions.DuplicateIdException;

public class InMemoryRepository<T extends IIdentifiable> implements IRepository<T> {
    private final List<T> items = new ArrayList<>();

    @Override
    public void add(T item) throws DuplicateIdException {
        if (item == null) {
            throw new IllegalArgumentException("Khong the them phan tu null.");
        }
        if (findById(item.getId()) != null) {
            throw new DuplicateIdException(item.getId());
        }
        items.add(item);
    }

    @Override
    public boolean remove(String id) {
        return items.removeIf(item -> item.getId().equals(id));
    }

    @Override
    public T findById(String id) {
        for (T item : items) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }

    @Override
    public List<T> getAll() {
        return Collections.unmodifiableList(items);
    }

    @Override
    public List<T> findAll(Predicate<T> condition) {
        return items.stream().filter(condition).collect(Collectors.toList());
    }
}