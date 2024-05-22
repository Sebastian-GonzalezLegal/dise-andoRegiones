
public class Vertice {
 private static int ultIndiceAgregado=-1;
 private String nombre;
 private int indice;
 
 public Vertice(String n) {
	this.nombre=n;
	this.indice= ++ultIndiceAgregado;
 }
 
 public int getIndice() {
	 return indice;
 }
 
 
 public String getNombre() {
	 return this.nombre;
 }
 
 
 public static void resetUltIndiceAgregado() {
	    ultIndiceAgregado = -1;
	}

}
