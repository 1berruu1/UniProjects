package Service;
import Domain.Tort;
import Repository.RepositoryException;
import Repository.Repository;

import java.util.List;

public class TortService {
    private Repository<Tort> tortRepository;

    public TortService(Repository<Tort> tortRepository){
        this.tortRepository = tortRepository;
    }
    public void addTort(int id, String tip_tort) throws RepositoryException {

        Tort tort = new Tort(id, tip_tort);
        tortRepository.add(tort);
    }

    public void deleteTort(int id) throws RepositoryException {
        tortRepository.delete(id);
    }
    public List<Tort> getAllTort(){
        List<Tort> torturi = tortRepository.getAll();
        return torturi;
    }

    public void UpdateTort(int id, String tip_tort) throws RepositoryException {
        Tort tort = new Tort(id, tip_tort);
        tortRepository.Update(tort);
    }

    public Tort findById(int tortId) {
        for (Tort tort : tortRepository.getAll()) {
            if (tort.getId() == tortId) {
                return tort;
            }
        }
        return null;
    }
}
