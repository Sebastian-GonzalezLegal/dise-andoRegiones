import static org.junit.Assert.*;

import org.junit.Test;

public class AristaTest {
    @Test
    public void testAristasIguales() {
        Vertice v1 = new Vertice("A");
        Vertice v2 = new Vertice("B");
        Arista arista1 = new Arista(v1, v2, 10);
        Arista arista2 = new Arista(v1, v2, 10);
        assertEquals(arista1, arista2); // Deben ser iguales
    }

    @Test
    public void testAristasDiferentes() {
        Vertice v1 = new Vertice("A");
        Vertice v2 = new Vertice("B");
        Arista arista1 = new Arista(v1, v2, 10);
        Arista arista2 = new Arista(v1, v2, 20);
        assertNotEquals(arista1, arista2); // Deben ser diferentes
    }

}
