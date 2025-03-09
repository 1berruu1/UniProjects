package Domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ComandaTest {
    Tort t1 = new Tort(1, "fructe");
    Tort t2 = new Tort(2, "ciocolata");

    Comanda c1 = new Comanda();
    Comanda c2 = new Comanda(1,"test",Arrays.asList(t1,t2),new Date());
    @org.junit.jupiter.api.Test
    void getLista_torturi() {
        assertEquals(Arrays.asList(t1,t2),c2.getLista_torturi());
    }

    @org.junit.jupiter.api.Test
    void setLista_torturi() {
        c1.setLista_torturi(Arrays.asList(t1,t2));
        assertEquals(Arrays.asList(t1,t2),c1.getLista_torturi());
    }


    @Test
    void testToString() {
        Comanda c3 = new Comanda(2,"test",Arrays.asList(t1,t2),new Date());
        String expected = "Id comanda: 2 Data: " + c3.getData() + " Lista torturi: fructe, ciocolata, ";
        assertEquals(expected, c3.toString());
    }
    @org.junit.jupiter.api.Test
    void getData() {
        Comanda c4 = new Comanda(3,"test",Arrays.asList(t1,t2),new Date());
        assertEquals(new Date(),c4.getData());
    }

    @org.junit.jupiter.api.Test
    void setData() {
        Date d = new Date();
        c1.setData(d);
        assertEquals(d,c1.getData());
    }
}