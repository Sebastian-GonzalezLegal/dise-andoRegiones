

import static org.junit.Assert.*;

import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Test;

public class VerticeTest {
	
	@Test
    public void testGetIndice() {
        Vertice vertice = new Vertice("A");
        assertEquals(0, vertice.getIndice());
    }

    @Test
    public void testGetNombre() {
        Vertice vertice = new Vertice("B");
        assertEquals("B", vertice.getNombre());
    }

}

