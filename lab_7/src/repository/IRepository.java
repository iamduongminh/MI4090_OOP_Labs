package repository;
import interfaces.IIdentifiable;
import java.util.List;
import java.util.function.Predicate;
import exceptions.DuplicateIdException;

public interface IRepository<T extends IIdentifiable> {
    void add(T item) throws DuplicateIdException;
    boolean remove(String id);
    T findById(String id);
    List<T> getAll();
    List<T> findAll(Predicate<T> condition);
}