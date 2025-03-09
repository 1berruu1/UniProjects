package Repository;

import Domain.Tort;
import Domain.TortConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class BinaryFileRepoTest {

    private BinaryFileRepo<Tort> binaryFileRepo;
    private final String filePath = "src/testBinaryFileRepo.bin";

    @BeforeEach
    void setUp() {
        binaryFileRepo = new BinaryFileRepo<>(filePath,new TortConverter());
    }

    @Test
    void loadFile() throws RepositoryException {
        binaryFileRepo.getAll().clear();

        Tort t2 = new Tort(2, "vanilie");
        binaryFileRepo.add(t2);
        binaryFileRepo.save();

        BinaryFileRepo<Tort> newRepo = new BinaryFileRepo<>(filePath, new TortConverter());
        newRepo.load();

        assertEquals(1, newRepo.getAll().size());
        Tort loadedTort = newRepo.getAll().get(0);
        assertEquals(t2.getId(), loadedTort.getId());
        assertEquals(t2.getTip_tort(), loadedTort.getTip_tort());
    }

    @Test
    void saveFile() throws RepositoryException {
        binaryFileRepo.getAll().clear();

        Tort t1 = new Tort(1, "ciocolata");
        binaryFileRepo.add(t1);
        binaryFileRepo.save();

        File file = new File(filePath);
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
    }
}