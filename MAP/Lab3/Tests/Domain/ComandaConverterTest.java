package Domain;

import Repository.Repository;
import Repository.RepositoryException;
import Service.TortService;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ComandaConverterTest {

    @Test
    void testToString() throws ParseException, RepositoryException {
        Repository repository = new Repository();
        Tort t1 = new Tort(1, "fructe");
        Tort t2 = new Tort(2, "ciocolata");
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2023-10-10");
        Comanda c1 = new Comanda(1, "test", Arrays.asList(t1, t2), date);

        repository.add(c1);

        ComandaConverter converter = new ComandaConverter(new TortService(repository));
        String expected = "1;Tue Oct 10 00:00:00 EEST 2023;2023-10-10;1,2";
        assertEquals(expected, converter.toString(c1));
    }

    @Test
    void fromString() throws ParseException, RepositoryException {
        String comandaString = "1;test;2023-10-10;1,2";
        Repository repository = new Repository();
        TortService tortService = new TortService(repository);

        // Adding Tort objects to the repository
        Tort t1 = new Tort(1, "fructe");
        Tort t2 = new Tort(2, "ciocolata");
        repository.add(t1);
        repository.add(t2);

        ComandaConverter converter = new ComandaConverter(tortService);
        Comanda c1 = converter.fromString(comandaString);

        assertNotNull(c1);
        assertEquals(1, c1.getId());
        assertEquals("test", c1.getTip_tort());
        assertEquals(2, c1.getLista_torturi().size());
        assertEquals("fructe", c1.getLista_torturi().get(0).getTip_tort());
        assertEquals("ciocolata", c1.getLista_torturi().get(1).getTip_tort());
    }
}