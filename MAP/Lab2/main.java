
import Domain.Comanda;
import Domain.Tort;
import Repository.IntRepository;
import Repository.Repository;
import Service.ComandaService;
import Service.TortService;
import UI.Console;

public class Program {
    public static void main(String[] args) {


        Repository<Tort> tortRepository = new Repository<Tort>();
        Repository<Comanda> comandaRepository = new Repository<Comanda>();

        Tort t1 = new Tort(1,"Cicolata");
        Tort t2 = new Tort(2,"Vanilie");

        tortRepository.add(t1);
        tortRepository.add(t2);


        TortService tortService = new TortService(tortRepository);
        ComandaService comandaService = new ComandaService(comandaRepository);

        Console console = new Console(comandaService, tortService);
        console.run();
    }
}
