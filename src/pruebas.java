import java.util.ArrayList;

public class pruebas {

	public static void main(String[] args) {
		GrafoProv g=new GrafoProv(9);
		GrafoProv a=new GrafoProv(9);
		
		Vertice v1=new Vertice("A");
		Vertice v2=new Vertice("B");
		Vertice v3=new Vertice("C");
		Vertice v4=new Vertice("D");
		Vertice v5=new Vertice("E");
		Vertice v6=new Vertice("F");
		Vertice v7=new Vertice("G");
		Vertice v8=new Vertice("H");
		Vertice v9=new Vertice("I");
		System.out.println(v1.getIndice() + "\n");
		System.out.println(v2.getIndice() + "\n");
		System.out.println(v3.getIndice() + "\n");
		System.out.println(v4.getIndice() + "\n");
		System.out.println(v5.getIndice() + "\n");
		System.out.println(v6.getIndice() + "\n");
		System.out.println(v7.getIndice() + "\n");
		System.out.println(v8.getIndice() + "\n");
		System.out.println(v9.getIndice() + "\n");
		
		
		
		g.agregarArista(v1, v2, 4);
		g.agregarArista(v1, v8, 8);
		g.agregarArista(v2, v8, 12);
		g.agregarArista(v2, v3, 8);
		g.agregarArista(v8, v7, 1);
		
		g.agregarArista(v8, v9, 6);
		g.agregarArista(v3, v9, 3);
		g.agregarArista(v7, v9, 5);
		g.agregarArista(v6, v7, 3);
		g.agregarArista(v3, v6, 4);
		g.agregarArista(v3, v4, 6);
		g.agregarArista(v4, v6, 13);
		g.agregarArista(v4, v5, 9);
		g.agregarArista(v5, v6, 10);
		
		System.out.println("------------------------------------");
		System.out.println("AGM");
		
		a=g.caminoMinimoKruskal(g.tamano());
		
		ArrayList<Arista>aristas=a.getListAristas();
		
		for(int i=0; i<aristas.size(); i++) {
			System.out.println("v1  " +aristas.get(i).getV1().getNombre() + "-  v2  "  + aristas.get(i).getV2().getNombre() + "  peso:" + aristas.get(i).getPesoArista());
		}
		
		
		System.out.println("------------------------------------");
		System.out.println("k REGIONES");
		a.eliminarAristasKMenos1(aristas, 4);
		
		for(int i=0; i<aristas.size(); i++) {
			System.out.println("v1  " +aristas.get(i).getV1().getNombre() + "-  v2  "  + aristas.get(i).getV2().getNombre() + "  peso:" + aristas.get(i).getPesoArista());
		}
		
		
	}

}
