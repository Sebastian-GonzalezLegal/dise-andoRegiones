import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class GrafoProvTest {
    private GrafoProv grafo;

    @Before
    public void setUp() {
        Vertice.resetUltIndiceAgregado();
        grafo = new GrafoProv(5); // Inicializar un grafo nuevo antes de cada test
    }

    @Test
    public void testRegistrarProv() {
        grafo.registrarProv("A");
        grafo.registrarProv("B");
        grafo.registrarProv("C");

        assertEquals(3, grafo.getCantProvincias()); // Verifica la cantidad de provincias registradas
    }

    @Test
    public void testHallarVPorNombre() {
        grafo.registrarProv("A");
        grafo.registrarProv("B");
        grafo.registrarProv("C");

        Vertice verticeEncontrado = grafo.hallarVPorNombre("B");
        assertNotNull(verticeEncontrado); // Verifica que el vértice "B" se encuentre en el registro

        try {
            grafo.hallarVPorNombre("D"); // Intenta buscar un vértice inexistente
            fail("Se esperaba una excepción IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Excepción esperada
        }
    }

    @Test
    public void testGetMatrizA() {
        boolean[][] matrizA = grafo.getMatrizA();
        assertNotNull(matrizA); // Verifica que la matriz de adyacencia no sea nula
        assertEquals(5, matrizA.length); // Verifica que la matriz tenga el tamaño correcto
    }

    @Test
    public void testGetListAristas() {
        ArrayList<Arista> aristas = grafo.getListAristas();
        assertNotNull(aristas); // Verifica que la lista de aristas no sea nula
        assertEquals(0, aristas.size()); // Verifica que inicialmente no haya aristas
    }

    @Test
    public void testTamano() {
        assertEquals(5, grafo.tamano()); // Verifica el tamaño del grafo
    }

    @Test
    public void testAgregarArista() {
        Vertice v1 = new Vertice("A");
        Vertice v2 = new Vertice("B");

        grafo.registrarProv(v1.getNombre());
        grafo.registrarProv(v2.getNombre());

        grafo.agregarArista(v1, v2, 4);

        assertTrue(grafo.getMatrizA()[v1.getIndice()][v2.getIndice()]); // Verifica que la arista esté presente
        assertTrue(grafo.getMatrizA()[v2.getIndice()][v1.getIndice()]); // Verifica que la arista esté presente en ambas
                                                                        // direcciones
    }

    @Test
    public void testEliminarArista() {
        Vertice v1 = new Vertice("A");
        Vertice v2 = new Vertice("B");

        grafo.registrarProv(v1.getNombre());
        grafo.registrarProv(v2.getNombre());

        grafo.agregarArista(v1, v2, 4);
        assertTrue(grafo.getMatrizA()[v1.getIndice()][v2.getIndice()]); // Verifica que la arista esté presente

        grafo.eliminarArista(v1.getIndice(), v2.getIndice());
        assertFalse(grafo.getMatrizA()[v1.getIndice()][v2.getIndice()]); // Verifica que la arista haya sido eliminada
    }

    @Test
    public void testEliminarAristasKMenos1() {
        ArrayList<Arista> aristas = new ArrayList<>();
        aristas.add(new Arista(new Vertice("A"), new Vertice("B"), 3));
        aristas.add(new Arista(new Vertice("B"), new Vertice("C"), 5));
        aristas.add(new Arista(new Vertice("C"), new Vertice("D"), 2));
        aristas.add(new Arista(new Vertice("D"), new Vertice("E"), 4));

        grafo.eliminarAristasKMenos1(aristas, 2);

        assertEquals(3, aristas.size()); // Verifica que se hayan eliminado 2 aristas
        assertEquals(4, aristas.get(0).getPesoArista()); // Verifica que la arista restante sea la correcta
    }

    @Test
    public void testEstaAislado() {
        grafo.registrarProv("A");
        grafo.registrarProv("B");

        Vertice v1 = grafo.hallarVPorNombre("A");
        Vertice v2 = grafo.hallarVPorNombre("B");

        // Verificar que ambos vértices están inicialmente aislados
        assertTrue(grafo.estaAislado(v1));
        assertTrue(grafo.estaAislado(v2));

        // Agregar una arista entre A y B
        grafo.agregarArista(v1, v2, 1);

        // Ahora, ninguno de los dos vértices debería estar aislado
        assertFalse(grafo.estaAislado(v1));
        assertFalse(grafo.estaAislado(v2));
    }

    @Test
    public void testGenerarAGM() {
        // Registrar los vértices
        Vertice vA = new Vertice("A");
        Vertice vB = new Vertice("B");
        Vertice vC = new Vertice("C");
        Vertice vD = new Vertice("D");
        Vertice vE = new Vertice("E");

        grafo.registrarProv(vA.getNombre());
        grafo.registrarProv(vB.getNombre());
        grafo.registrarProv(vC.getNombre());
        grafo.registrarProv(vD.getNombre());
        grafo.registrarProv(vE.getNombre());

        // Agregar aristas usando los vértices existentes
        grafo.agregarArista(vA, vB, 3);
        grafo.agregarArista(vB, vC, 5);
        grafo.agregarArista(vC, vD, 2);
        grafo.agregarArista(vD, vE, 4);

        GrafoProv arbolGenerador = grafo.generarAGM();

        // Verifica que el árbol tenga la cantidad correcta de aristas
        assertEquals(4, arbolGenerador.getListAristas().size());

    }

}
