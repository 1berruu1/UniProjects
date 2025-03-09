package Repository;
import Domain.Entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Repository<T extends Entity> implements IntRepository<T> {
    protected List<T> items = new ArrayList<>();


    @Override
    public void add(T item) throws RepositoryException {
        if(findById(item.getId()) != null){
            throw new RepositoryException("Id-ul exista deja!");
        }
        items.add(item);
    }

    @Override
    public void delete(int id) throws RepositoryException {
        for (T item : items) {
            if (item.getId() == id) {
                items.remove(item);
                return;
            }
        }
    }

    public T findById(int id) {
        for (T item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    @Override
    public List<T> getAll() {
        return items;
    }

    @Override
    public void Update(T item) throws RepositoryException{
        boolean updated = false;
        for(int i =0; i< items.size(); i++){
            if(items.get(i).getId() == item.getId()){
                items.set(i, item);
                updated = true;
                break;
            }
        }
        if(!updated){
            throw new RepositoryException("Id-ul nu exista!");
        }
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
