
public class Arista {
 private Vertice vert1, vert2;
 private int peso;

 public Arista(Vertice v1, Vertice v2, int p) {
	 this.vert1=v1;
	 this.vert2=v2;
	 this.peso=p;	 
 }
 
 public Vertice getV1() {
	 return this.vert1;
 }
 
 public Vertice getV2() {
	 return this.vert2;
 }
 
 public int getPesoArista() {
	 return this.peso;
 }
 
 
}
