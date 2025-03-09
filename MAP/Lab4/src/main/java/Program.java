import Domain.Comanda;
import Domain.ComandaConverter;
import Domain.Tort;
import Domain.TortConverter;
import Repository.BinaryFileRepo;
import Repository.Repository;
import Repository.SQLRepository;
import Repository.TextFileRepository;
import Service.ComandaService;
import Service.TortService;
import UI.Console;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Program {
    public static void main(String[] args) {
        Properties properties = new Properties();
        try {
            FileInputStream input = new FileInputStream("src/config.properties");
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        String repositoryType = properties.getProperty("repository.type");
        TortService tortService;
        ComandaService comandaService;

        TortConverter tortConverter = new TortConverter();
        ComandaConverter comandaConverter;

        if ("binary".equalsIgnoreCase(repositoryType)) {
            comandaConverter = new ComandaConverter(new TortService(new BinaryFileRepo<>("src/torturi.bin", tortConverter)));
            BinaryFileRepo<Tort> tortBinaryFileRepo = new BinaryFileRepo<>("src/torturi.bin", tortConverter);
            BinaryFileRepo<Comanda> comandaBinaryFileRepo = new BinaryFileRepo<>("src/comenzi.bin", comandaConverter);
            tortService = new TortService(tortBinaryFileRepo);
            comandaService = new ComandaService(comandaBinaryFileRepo);
        } else if ("txt".equalsIgnoreCase(repositoryType)) {
            comandaConverter = new ComandaConverter(new TortService(new TextFileRepository<>("src/torturi.txt", tortConverter)));
            TextFileRepository<Tort> tortTextFileRepository = new TextFileRepository<>("src/torturi.txt", tortConverter);
            TextFileRepository<Comanda> comandaTextFileRepository = new TextFileRepository<>("src/comenzi.txt", comandaConverter);
            tortService = new TortService(tortTextFileRepository);
            comandaService = new ComandaService(comandaTextFileRepository);

        } else if ("sql".equalsIgnoreCase(repositoryType)) {
            SQLRepository<Comanda> sqlRepositoryComanda = new SQLRepository<>(Comanda.class);
            SQLRepository<Tort> sqlRepositoryTort = new SQLRepository<>(Tort.class);
            tortService = new TortService(sqlRepositoryTort);
            comandaService = new ComandaService(sqlRepositoryComanda);

        } else{
            System.out.println("You are using the memory option, your objects will not save after exiting");
            Repository<Tort> tortRepositoryrepository = new Repository<Tort>();
            Repository<Comanda> comandaRepository = new Repository<Comanda>();
            tortService = new TortService(tortRepositoryrepository);
            comandaService = new ComandaService(comandaRepository);
        }


        Console console = new Console(comandaService, tortService);
        console.run();


    }
}