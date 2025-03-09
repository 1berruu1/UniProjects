package Repository;

import Domain.AbstractConverter;
import Domain.Entity;

import java.io.*;
import java.text.ParseException;

public class TextFileRepository<T extends Entity> extends AbstaractFileRepo<T> {
    private AbstractConverter<T> converter;

    public TextFileRepository(String filename, AbstractConverter<T> converter) {
        super(filename);
        this.converter = converter;
        try {
            loadFile();
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void saveFile() throws RepositoryException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.fileName))) {
            for (var item : this.items) {
                String asString = converter.toString((T) item);
                bw.write(asString);
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RepositoryException("Error writing file", e);
        }
    }


    @Override
    protected void loadFile() throws RepositoryException {
        try (BufferedReader br = new BufferedReader(new FileReader(this.fileName))) {
            String line = br.readLine();
            while (line != null) {
                items.add(converter.fromString(line));
                line = br.readLine();
            }
        } catch (IOException e) {
            throw new RepositoryException("Error reading file", e);
        } catch (ParseException e) {
            throw new RepositoryException("Error parsing data", e);
        }
    }

    public void save() throws RepositoryException {
        saveFile();
    }

    public void load() throws RepositoryException {
        loadFile();
    }

}
