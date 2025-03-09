package Service;

import Domain.Comanda;
import Domain.Tort;
import Repository.Repository;
import Repository.RepositoryException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ComandaService {
    private Repository<Comanda> comenziRepository;

    public ComandaService(Repository<Comanda> comenziRepository) {
        this.comenziRepository = comenziRepository;
    }


    public void addComanda(int id, String name, List<Tort> tortList, Date data) throws RepositoryException {
        if (tortList == null || tortList.isEmpty()) {
            throw new RepositoryException("Comanda trebuie sa aiba cel putin un tort");
        }
        Comanda comanda = new Comanda(id, name, tortList, data);
        comenziRepository.add(comanda);
    }

    public void deleteComanda(int id) throws RepositoryException {
        comenziRepository.delete(id);
    }


    public List<Comanda> getAllComenzi() {
        List<Comanda> comenzi = comenziRepository.getAll();
        return comenzi != null ? comenzi : new ArrayList<>();
    }

    public Comanda getComandaById(int id) {
        for (Comanda comanda : comenziRepository.getAll()) {
            if (comanda.getId() == id) {
                return comanda;
            }
        }
        return null;
    }

    public void updateComanda(int id, String name, List<Tort> tortList, Date data) throws RepositoryException {
        Comanda comanda = new Comanda(id, name, tortList, data);
        comenziRepository.Update(comanda);
    }
}
