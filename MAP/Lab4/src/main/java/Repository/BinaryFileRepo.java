package Repository;

import Domain.AbstractConverter;
import Domain.Entity;

import java.io.*;
import java.util.ArrayList;

public class BinaryFileRepo<T extends Entity> extends AbstaractFileRepo<T> {
    private AbstractConverter<T> converter;
    private String filePath;

    public BinaryFileRepo(String filePath, AbstractConverter<T> converter) {
        super(null);
        this.converter = converter;
        this.filePath = filePath;
        this.items = new ArrayList<>();
        try {
            loadFile();
        } catch (RepositoryException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }

    @Override
    protected void loadFile() throws RepositoryException {
        File file = new File(filePath);
        if (!file.exists() || file.length() == 0) {
            this.items = new ArrayList<>(); // Initialize an empty list if the file does not exist or is empty
            return;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            this.items = (ArrayList<T>) ois.readObject();
        } catch (FileNotFoundException exc) {
            System.out.println("File not found: " + exc.getMessage());
            exc.printStackTrace();
            throw new RepositoryException("File not found", exc);
        } catch (IOException | ClassNotFoundException exc) {
            System.out.println("Error reading file: " + exc.getMessage());
            exc.printStackTrace();
            throw new RepositoryException("Error reading file", exc);
        }
    }

    @Override
    protected void saveFile() throws RepositoryException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(this.filePath))) {
            out.writeObject(items);
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
            e.printStackTrace();
            throw new RepositoryException("Error writing file", e);
        }
    }

    public void save() throws RepositoryException {
        saveFile();
    }

    public void load() throws RepositoryException {
        loadFile();
    }
}

