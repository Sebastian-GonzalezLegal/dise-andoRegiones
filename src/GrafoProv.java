import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;

public class GrafoProv {

	private boolean[][] A; // Representamos el grafo por su matriz de adyacencia
	private ArrayList<Arista> aristas = new ArrayList<>();
	private ArrayList<Vertice> vertices = new ArrayList<>();

	// La cantidad de vertices esta predeterminada desde el constructor
	public GrafoProv(int vertices) {
		A = new boolean[vertices][vertices];

	}

	public boolean[][] getMatrizDeAdyacencia() {
		return this.A;
	}

	public void registrarProv(String nom) {

		Vertice v = new Vertice(nom);
		this.vertices.add(v);
		System.out.println(this.vertices.get(0));
	}

	public int getCantProvincias() {
		return this.vertices.size();
	}

	public ArrayList<Vertice> getVertices() {
		return this.vertices;
	}

	public Vertice hallarVPorNombre(String n) {
		for (Vertice v : this.vertices) {
			if (v.getNombre().equals(n)) {
				return v;
			}
		}
		throw new IllegalArgumentException("La provincia no se encuentra en el registro de provincias");
	}

	public boolean[][] getMatrizA() {
		return this.A;
	}

	public ArrayList<Arista> getListAristas() {
		return this.aristas;
	}

	// Eliminacion de aristas
	public void eliminarArista(int i, int j) {
		verificarVertice(i);
		verificarVertice(j);

		System.out.println("eliminarArista");
		verificarDistintos(i, j);

		A[i][j] = false;
		A[j][i] = false;
	}

	// Cantidad de vertices
	public int tamano() {
		return A.length;
	}

	// agrega a las matrices pesos y adyacencia una nueva relacion
	public void agregarArista(Vertice v1, Vertice v2, int p) {

		verificarVertice(v1.getIndice());
		verificarVertice(v2.getIndice());
		System.out.println("agregarArista");
		System.out.println(v1.getNombre() + "  " + v2.getNombre() + "  " + p);

		verificarDistintos(v1.getIndice(), v2.getIndice());

		A[v1.getIndice()][v2.getIndice()] = true;
		A[v2.getIndice()][v1.getIndice()] = true;
		Arista a = new Arista(v1, v2, p);
		this.aristas.add(a);
	}

	// Verifica que sea un vertice valido
	private void verificarVertice(int i) {
		if (i < 0)
			throw new IllegalArgumentException("El vertice no puede ser negativo: " + i);

		if (i >= A.length)
			throw new IllegalArgumentException("Los vertices deben estar entre 0 y |V|-1: " + i);
	}

	// Verifica que i y j sean distintos
	private void verificarDistintos(int i, int j) {
		if (i == j)
			throw new IllegalArgumentException("No se permiten loops: (" + i + ", " + j + ")");
	}

	// ------------------------------------------------------------------------------------------
	public GrafoProv caminoMinimoKruskal(int cantVertices) {
		GrafoProv arbol = new GrafoProv(cantVertices); // Grafo nuevo, arbol generador minimo
		ArrayList<Arista> aux = ordenarAristasPorPeso(this.aristas); // Aristas de mi grafo original, ordenadas
		int i = 0;
		arbol.agregarArista(aux.get(0).getV1(), aux.get(0).getV2(), aux.get(0).getPesoArista());
		// Agrego la primer arista del original ordenado a la lista de aristas del grafo
		// arbol
		while (i < arbol.tamano() - 1) {
			for (int j = 0; j < aux.size(); j++) {
				if (!arbol.formaCircuito(aux.get(j).getV1().getIndice(), aux.get(j).getV2().getIndice(),
						arbol.getMatrizA(), cantVertices))
					arbol.agregarArista(aux.get(j).getV1(), aux.get(j).getV2(), aux.get(j).getPesoArista());

				i = i + 1;
			}
		}

		return arbol;

	}

	// ------------------------------------------------------------------------------------------
	// Método para saber si la arista forma parte de un circuito

	public boolean formaCircuito(int origen, int destino, boolean[][] matrizAdyacencia, int cantVertices) {
		boolean[] visitado = new boolean[cantVertices];
		Arrays.fill(visitado, false); // Inicializar todos los vértices como no visitados

		visitado[origen] = true;

		List<Integer> verticesPorVisitar = new ArrayList<>();
		verticesPorVisitar.add(origen);

		while (!verticesPorVisitar.isEmpty()) {
			int actual = verticesPorVisitar.remove(0);

			for (int vecino = 0; vecino < cantVertices; vecino++) {
				// Verificar si hay una arista entre el vértice actual y el vecino
				if (matrizAdyacencia[actual][vecino]) {
					if (vecino == destino) {
						return true; // Encontramos un ciclo
					}
					if (!visitado[vecino]) {
						verticesPorVisitar.add(vecino);
						visitado[vecino] = true;
					}
				}
			}
		}

		return false;
	}

	// ------------------------------------------------------------------------------------------
	// Método de ordenación de burbuja
	public ArrayList<Arista> ordenarAristasPorPeso(ArrayList<Arista> a) {
		int n = a.size();
		ArrayList<Arista> nuevo = new ArrayList<>(a); // la clono para no modificar el ArrayList de aristas original
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				if (nuevo.get(j).getPesoArista() > nuevo.get(j + 1).getPesoArista()) {
					// Intercambia los elementos si están en el orden incorrecto
					Arista temp = nuevo.get(j);
					nuevo.set(j, nuevo.get(j + 1));
					nuevo.set(j + 1, temp);

				}
			}
		}
		return nuevo;
	}

	// ---------------------------------------------------------------------------------------------

	public void eliminarAristasKMenos1(ArrayList<Arista> aristas, int k) {
		if (aristas.size() >= k) {
			// Ordena las aristas por peso en orden decreciente
			Collections.sort(aristas, Comparator.comparingInt(a -> -a.getPesoArista())); // Negamos el peso para ordenar
																							// en forma decreciente

			// Elimina las primeras k - 1 aristas (las de mayor peso)
			aristas.subList(0, k - 1).clear();
		}

	}

	// ---------------------------------------------------------------------------------------------
	public boolean estaAislado(Vertice v) {
		// Obtener la lista de adyacencia del vértice
		boolean[][] vecinos = this.getMatrizA();
		int ind = v.getIndice();

		// Iterar sobre toda la fila correspondiente al índice del vértice
		for (int j = 0; j < vecinos[ind].length; j++) {
			if (vecinos[ind][j]) {
				return false; // Si hay una conexión, el vértice no está aislado
			}
		}
		return true; // Si no hay conexiones, el vértice está aislado
	}

	// ---------------------------------------------------------------------------------------------
	public GrafoProv generarAGM() {
		int cantVertices = tamano();
		GrafoProv arbol = new GrafoProv(cantVertices); // Grafo nuevo, árbol generador mínimo
		ArrayList<Arista> aristasOrdenadas = ordenarAristasPorPeso(aristas); // Aristas del grafo original, ordenadas
																				// por peso
		int aristasAgregadas = 0;
		int indiceAristaActual = 0;

		// Iterar sobre las aristas ordenadas por peso
		while (aristasAgregadas < cantVertices - 1 && indiceAristaActual < aristasOrdenadas.size()) {
			Arista aristaActual = aristasOrdenadas.get(indiceAristaActual);
			Vertice v1 = aristaActual.getV1();
			Vertice v2 = aristaActual.getV2();

			// Verificar si agregar la arista forma un ciclo en el árbol actual
			if (!formaCircuito(v1.getIndice(), v2.getIndice(), arbol.getMatrizA(), cantVertices)) {
				// Si no forma un ciclo, agregar la arista al árbol
				arbol.agregarArista(v1, v2, aristaActual.getPesoArista());
				aristasAgregadas++;
			}

			indiceAristaActual++;
		}

		return arbol;
	}

	// ---------------------------------------------------------------------------------------------
	public List<Vertice> obtenerVerticesSinAristas() {
		List<Vertice> verticesSinAristas = new ArrayList<>();
		for (Vertice vertice : vertices) {
			boolean tieneAristas = false;
			for (int j = 0; j < tamano(); j++) {
				if (A[vertice.getIndice()][j]) {
					tieneAristas = true;
					break;
				}
			}
			if (!tieneAristas) {
				verticesSinAristas.add(vertice);
			}
		}
		return verticesSinAristas;
	}
}
