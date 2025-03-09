package Repository;

import Domain.Tort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryTest {

    private Repository repository;
    @BeforeEach
    void setUp() {
        repository = new Repository();
    }

    @Test
    void add() throws RepositoryException {
        Tort t1 = new Tort(1, "fructe");
        repository.add(t1);
        assertEquals(1, repository.getAll().size());
        assertEquals(t1, repository.findById(1));
    }

    @Test
    void delete() throws RepositoryException {
        Tort t1 = new Tort(1, "fructe");
        repository.add(t1);
        repository.delete(1);
        assertNull(repository.findById(1));
        assertEquals(0, repository.getAll().size());
    }

    @Test
    void findById() throws RepositoryException {
        Tort t1 = new Tort(1, "fructe");
        repository.add(t1);
        Tort found = (Tort) repository.findById(1);
        assertNotNull(found);
        assertEquals(t1, found);
    }

    @Test
    void getAll() throws RepositoryException {
        Tort t1 = new Tort(1, "fructe");
        Tort t2 = new Tort(2, "ciocolata");
        repository.add(t1);
        repository.add(t2);
        List<Tort> all = repository.getAll();
        assertEquals(2, all.size());
        assertTrue(all.contains(t1));
        assertTrue(all.contains(t2));
    }

    @Test
    void update() throws RepositoryException {
        Tort t1 = new Tort(1, "fructe");
        repository.add(t1);
        Tort t2 = new Tort(1, "ciocolata");
        repository.Update(t2);
        assertEquals("ciocolata", ((Tort) repository.findById(1)).getTip_tort());
    }

    @Test
    void updateNonExistentIdThrowsException() {
        Tort t1 = new Tort(1, "fructe");
        assertThrows(RepositoryException.class, () -> repository.Update(t1));
    }

    @Test
    void iterator() {
        assertNull(repository.iterator());
    }
}