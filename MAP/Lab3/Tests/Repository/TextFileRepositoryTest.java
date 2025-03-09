package Repository;

import Domain.Tort;
import Domain.TortConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextFileRepositoryTest {

    private TextFileRepository<Tort> tortTextFileRepository;
    private final String filePath = "src/testTextFileRepo.txt";

    @BeforeEach
    void setUp() {
        tortTextFileRepository = new TextFileRepository<>(filePath, new TortConverter());
    }

    @Test
    void saveFile() throws RepositoryException {
        tortTextFileRepository.getAll().clear();

        Tort t1 = new Tort(1, "ciocolata");
        tortTextFileRepository.add(t1);
        tortTextFileRepository.save();

        assertTrue(tortTextFileRepository.getAll().size() == 1);
    }

    @Test
    void loadFile() throws RepositoryException {
        tortTextFileRepository.getAll().clear();

        Tort t2 = new Tort(2, "vanilie");
        tortTextFileRepository.add(t2);
        tortTextFileRepository.save();

        TextFileRepository<Tort> newRepo = new TextFileRepository<>(filePath, new TortConverter());
        newRepo.load();

        assertEquals(2, newRepo.getAll().size());
        Tort loadedTort = newRepo.getAll().get(0);
        assertEquals(t2.getId(), loadedTort.getId());
        assertEquals(t2.getTip_tort(), loadedTort.getTip_tort());
    }
}