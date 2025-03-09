package Service;

import Domain.Comanda;
import Domain.Tort;
import Repository.Repository;
import Repository.RepositoryException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ComandaServiceTest {

    private ComandaService comandaService;
    private Repository comandaRepository;

    @BeforeEach
    void setUp() {
        comandaRepository = new Repository();
        comandaService = new ComandaService(comandaRepository);
    }

    @Test
    void addComanda() throws RepositoryException {
        Tort t1 = new Tort(1, "cioco");
        Tort t2 = new Tort(2, "vanilie");
        Date date = new Date();
        comandaService.addComanda(1, "test", Arrays.asList(t1,t2), date);
        assertEquals(1, comandaRepository.getAll().size());
    }

    @Test
    void deleteComanda() throws RepositoryException{
        Tort t1 = new Tort(1, "cioco");
        Tort t2 = new Tort(2, "vanilie");
        Date date = new Date();
        comandaService.addComanda(1, "test", Arrays.asList(t1,t2), date);
        comandaService.deleteComanda(1);
        assertEquals(0, comandaRepository.getAll().size());
    }

    @Test
    void getAllComenzi() throws RepositoryException{
        Tort t1 = new Tort(1, "cioco");
        Tort t2 = new Tort(2, "vanilie");
        Date date = new Date();
        comandaService.addComanda(1, "test", Arrays.asList(t1,t2), date);
        comandaService.addComanda(2, "test2", Arrays.asList(t1,t2), date);
        assertEquals(2, comandaService.getAllComenzi().size());

    }

    @Test
    void getComandaById() throws RepositoryException {
        Tort t1 = new Tort(1, "cioco");
        Tort t2 = new Tort(2, "vanilie");
        Date date = new Date();
        comandaService.addComanda(1, "test", Arrays.asList(t1, t2), date);
        Comanda foundComanda = comandaService.getComandaById(1);
        assertNotNull(foundComanda);
        assertEquals(1, foundComanda.getId());
    }

    @Test
    void updateComanda() throws RepositoryException {
        Tort t1 = new Tort(1, "cioco");
        Tort t2 = new Tort(2, "vanilie");
        Date date = new Date();
        comandaService.addComanda(1, "test", Arrays.asList(t1, t2), date);
        comandaService.updateComanda(1, "test1", Arrays.asList(t1), date);
        Comanda updatedComanda = (Comanda) comandaRepository.getAll().get(0);

        assertEquals(t1,updatedComanda.getLista_torturi().getFirst());


    }
}