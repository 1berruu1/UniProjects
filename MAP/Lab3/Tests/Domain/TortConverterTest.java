package Domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TortConverterTest {

    @Test
    void testToString() {
        Tort t1 = new Tort(1, "fructe");
        TortConverter converter = new TortConverter();
        String expected = "1;fructe";
        assertEquals(expected, converter.toString(t1));
    }

    @Test
    void fromString() {
        String tortString = "1;fructe";
        TortConverter converter = new TortConverter();
        Tort t1 = converter.fromString(tortString);
        assertNotNull(t1);
        assertEquals(1, t1.getId());
        assertEquals("fructe", t1.getTip_tort());
    }
}