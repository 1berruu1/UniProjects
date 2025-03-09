package Repository;
import Domain.Entity;

import java.util.List;

public interface IntRepository <T extends Entity> extends Iterable{
    void add(T Item) throws RepositoryException;
    void delete(int id) throws RepositoryException;
    List<T> getAll();
    void Update(T item) throws RepositoryException;
}
