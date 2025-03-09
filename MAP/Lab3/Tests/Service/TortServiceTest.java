package Service;

import Domain.Tort;
import Repository.Repository;
import Repository.RepositoryException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TortServiceTest {


    private TortService tortService;
    private Repository tortRepository;

    @BeforeEach
    void setUp() {
        tortRepository = new Repository();
        tortService = new TortService(tortRepository);
    }

    @Test
    void addTort() throws RepositoryException {
        tortService.addTort(1,"cioco");
        assertEquals(1, tortRepository.getAll().size());
    }


    @Test
    void deleteTort() throws RepositoryException {
        tortService.addTort(1,"cioco");
        tortService.deleteTort(1);
        assertEquals(0, tortRepository.getAll().size());
    }

    @Test
    void getAllTort() throws RepositoryException {
        tortService.addTort(1,"cioco");
        tortService.addTort(2,"vanilie");
        assertEquals(2, tortService.getAllTort().size());
    }

    @Test
    void updateTort() throws RepositoryException {
        tortService.addTort(1, "cioco");
        tortService.UpdateTort(1, "vanilie");
        Tort updatedTort = (Tort) tortRepository.getAll().get(0);
        assertEquals("vanilie", updatedTort.getTip_tort());
    }

    @Test
    void findById() throws RepositoryException {
        tortService.addTort(1,"cioco");
        tortService.addTort(2,"vanilie");
        assertEquals(1, tortService.findById(1).getId());
    }
}