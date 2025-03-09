package Repository;

import Domain.Entity;

public abstract class AbstaractFileRepo<T extends Entity> extends Repository<T> {
    protected String fileName;

    public AbstaractFileRepo(String fileName) {
        this.fileName = fileName;
    }

    public void add(T item) throws RepositoryException {
        super.add(item);
        saveFile();
    }

    public void delete(int id) throws RepositoryException {
        super.delete(id);
        saveFile();
    }

    public void Update(T item) throws RepositoryException {
        super.Update(item);
        saveFile();
    }


    protected abstract void loadFile() throws RepositoryException;

    protected abstract void saveFile() throws RepositoryException;
}
