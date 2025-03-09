package Domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TortTest {
    Tort t1 = new Tort(1, "fructe");
    Tort t2 = new Tort();
    @Test
    void getTip_tort() {
        assertEquals("fructe", t1.getTip_tort());
    }

    @Test
    void toString1() {
        assertEquals("Id Tort=1, Tip Tort= fructe ", t1.toString());
    }

    @Test
    void setTip_tort() {
        t1.setTip_tort("ciocolata");
        assertEquals("ciocolata", t1.getTip_tort());
    }
}