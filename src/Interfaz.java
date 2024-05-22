import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class Interfaz {

	private JFrame frame;
	private JFrame frame2;
	private JTextField textFieldCantProv;
	private JLayeredPane layeredPane;
	private JTextField textFieldCargarProv;
	private GrafoProv grafo;
	private JTextField textFieldSimilaridad;
	private JTextField textFieldAgregarK;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz window = new Interfaz();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void cambiarPanel(JPanel panel) {
		panel.setVisible(true);
		this.layeredPane.removeAll();
		this.layeredPane.add(panel);
		this.layeredPane.repaint();
		this.layeredPane.revalidate();
	}

	public Interfaz() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		frame2 = new JFrame();
		frame2.setBounds(100, 100, 450, 300);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.getContentPane().setLayout(null);

		this.layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 436, 252);
		frame.getContentPane().add(layeredPane);
		layeredPane.setLayout(null);

		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBounds(0, 0, 436, 252);
		layeredPane.add(panelPrincipal);
		panelPrincipal.setLayout(null);

		JLabel Principal = new JLabel("Diseño de regiones");
		Principal.setFont(new Font("Arial", Font.PLAIN, 20));
		Principal.setBounds(137, 11, 169, 50);
		panelPrincipal.add(Principal);

		JLabel lblCantProvincias = new JLabel("Ingrese la cantidad de provincias");
		lblCantProvincias.setFont(new Font("Arial", Font.PLAIN, 14));
		lblCantProvincias.setBounds(117, 59, 210, 28);
		panelPrincipal.add(lblCantProvincias);

		textFieldCantProv = new JTextField();
		textFieldCantProv.setBounds(176, 104, 96, 20);
		textFieldCantProv.setColumns(10);
		panelPrincipal.add(textFieldCantProv);

		// ---------------------------------------------------------------

		JPanel panelRegistrarProv = new JPanel();
		panelRegistrarProv.setBounds(0, 0, 436, 252);
		layeredPane.add(panelRegistrarProv);
		panelRegistrarProv.setLayout(null);
		panelRegistrarProv.setVisible(false);

		JButton btnPrincipal = new JButton("OK");
		btnPrincipal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				grafo = new GrafoProv(Integer.parseInt(textFieldCantProv.getText()));
				cambiarPanel(panelRegistrarProv);
			}
		});
		btnPrincipal.setBounds(176, 135, 96, 23);
		panelPrincipal.add(btnPrincipal);

		// -----------------------------------------------------------

		JLabel lblCargarProv = new JLabel("Cargar provincia");
		lblCargarProv.setFont(new Font("Arial", Font.PLAIN, 20));
		lblCargarProv.setBounds(149, 30, 144, 39);
		panelRegistrarProv.add(lblCargarProv);

		textFieldCargarProv = new JTextField();
		textFieldCargarProv.setBounds(149, 80, 144, 32);
		panelRegistrarProv.add(textFieldCargarProv);
		textFieldCargarProv.setColumns(10);

		JPanel panelCargarArista = new JPanel();
		panelCargarArista.setBounds(0, 0, 436, 252);
		layeredPane.add(panelCargarArista);
		panelCargarArista.setLayout(null);
		panelCargarArista.setVisible(false);

		JButton btnFinCargarProv = new JButton("Listo!");
		btnFinCargarProv.setEnabled(false);
		btnFinCargarProv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiarPanel(panelCargarArista);
			}
		});

		btnFinCargarProv.setBounds(175, 183, 89, 23);
		panelRegistrarProv.add(btnFinCargarProv);

		JComboBox<String> comboBox1 = new JComboBox<>();
		comboBox1.setBounds(38, 89, 85, 22);
		panelCargarArista.add(comboBox1);

		JComboBox<String> comboBox2 = new JComboBox<>();
		comboBox2.setBounds(163, 89, 85, 22);
		panelCargarArista.add(comboBox2);

		JButton btnCargarProv = new JButton("Cargar");
		btnCargarProv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				grafo.registrarProv(textFieldCargarProv.getText());
				textFieldCargarProv.setText("");
				if (grafo.tamano() == grafo.getCantProvincias()) {
					for (Vertice prov : grafo.getVertices()) {
						comboBox1.addItem(prov.getNombre());
						comboBox2.addItem(prov.getNombre());
					}
					btnCargarProv.setEnabled(false);
					textFieldCargarProv.setEnabled(false);
					btnFinCargarProv.setEnabled(true);
				}
			}
		});
		btnCargarProv.setBounds(175, 129, 89, 23);
		panelRegistrarProv.add(btnCargarProv);

		JPanel panelIngresarK = new JPanel();
		panelIngresarK.setBounds(0, 0, 436, 252);
		layeredPane.add(panelIngresarK);
		panelIngresarK.setLayout(null);
		panelIngresarK.setVisible(false);

		JPanel panelVerGrafo = new JPanel();
		panelVerGrafo.setBounds(0, 0, 436, 252);
		layeredPane.add(panelVerGrafo);
		panelVerGrafo.setLayout(null);
		panelVerGrafo.setVisible(false);

		JPanel panelVerAGM = new JPanel();
		panelVerAGM.setBounds(0, 0, 416, 252);
		layeredPane.add(panelVerAGM);
		panelVerAGM.setLayout(null);
		panelVerAGM.setVisible(false);

		// -----------------------------------------------------------

		JLabel lblCargarArista = new JLabel("Cargar arista");
		lblCargarArista.setFont(new Font("Arial", Font.BOLD, 17));
		lblCargarArista.setBounds(163, 22, 103, 27);
		panelCargarArista.add(lblCargarArista);

		JLabel lblProv1 = new JLabel("Provincia 1");
		lblProv1.setFont(new Font("Arial", Font.PLAIN, 17));
		lblProv1.setBounds(38, 64, 85, 14);
		panelCargarArista.add(lblProv1);

		JLabel lblProv2 = new JLabel("Provincia 2");
		lblProv2.setFont(new Font("Arial", Font.PLAIN, 17));
		lblProv2.setBounds(163, 64, 85, 14);
		panelCargarArista.add(lblProv2);

		textFieldSimilaridad = new JTextField();
		textFieldSimilaridad.setBounds(294, 90, 85, 20);
		panelCargarArista.add(textFieldSimilaridad);
		textFieldSimilaridad.setColumns(10);

		JLabel lblPeso = new JLabel("Similaridad");
		lblPeso.setFont(new Font("Arial", Font.PLAIN, 17));
		lblPeso.setBounds(294, 53, 85, 36);
		panelCargarArista.add(lblPeso);

		JButton btnCargarArista = new JButton("Cargar");
		btnCargarArista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				grafo.agregarArista(grafo.hallarVPorNombre((String) comboBox1.getSelectedItem()),
						grafo.hallarVPorNombre((String) comboBox2.getSelectedItem()),
						Integer.parseInt(textFieldSimilaridad.getText()));
				textFieldSimilaridad.setText("");

				// TIRAR excepcion si elige la misma prov en ambos comboBox
			}
		});
		btnCargarArista.setBounds(294, 133, 85, 27);
		panelCargarArista.add(btnCargarArista);

		// Boton ver grafo
		JButton btnVerGrafo = new JButton("Visualizar grafo");
		btnVerGrafo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JLabel Principal = new JLabel("Grafo Principal");
				Principal.setFont(new Font("Arial", Font.PLAIN, 18));
				Principal.setBounds(160, 11, 205, 50);
				panelVerGrafo.add(Principal);

				// Crear un JTextArea para mostrar el grafo
				JTextArea txtAreaArbol = new JTextArea();
				txtAreaArbol.setEditable(false);

				// Obtener las aristas del grafo
				ArrayList<Arista> aristasAGM = grafo.getListAristas();

				// Construir la representación del grado
				StringBuilder sb = new StringBuilder();

				for (Arista arista : aristasAGM) {
					sb.append(arista.getV1().getNombre()).append(" - ")
							.append(arista.getV2().getNombre()).append(": ")
							.append(arista.getPesoArista()).append("\n");
				}

				// Agregar la información al área de texto
				txtAreaArbol.setText(sb.toString());

				JScrollPane scrollPane = new JScrollPane(txtAreaArbol);
				scrollPane.setBounds(137, 70, 169, 100);
				scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Mostrar siempre la
																								// barra vertical

				panelVerGrafo.add(scrollPane);

				cambiarPanel(panelVerGrafo);
			}
		});
		btnVerGrafo.setBounds(10, 205, 132, 36);
		panelCargarArista.add(btnVerGrafo);

		// Boton ejecutar AGM
		JButton btnEjecutarAGM = new JButton("Ejecutar AGM");
		btnEjecutarAGM.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				JLabel Principal = new JLabel("Visual AGM");
				Principal.setFont(new Font("Arial", Font.PLAIN, 18));
				Principal.setBounds(175, 11, 205, 50);
				panelVerAGM.add(Principal);

				// Llamar al método para generar el árbol generador mínimo
				GrafoProv arbolGenerador = grafo.generarAGM();

				// Crear un JTextArea para mostrar el árbol generador mínimo
				JTextArea txtAreaArbol = new JTextArea();
				txtAreaArbol.setEditable(false);

				// Obtener las aristas del árbol generador mínimo
				ArrayList<Arista> aristasAGM = arbolGenerador.getListAristas();

				// Construir la representación del árbol generador mínimo
				StringBuilder sb = new StringBuilder();

				for (Arista arista : aristasAGM) {
					sb.append(arista.getV1().getNombre()).append(" - ")
							.append(arista.getV2().getNombre()).append(": ")
							.append(arista.getPesoArista()).append("\n");
				}

				// Agregar la información al área de texto
				txtAreaArbol.setText(sb.toString());

				JScrollPane scrollPane = new JScrollPane(txtAreaArbol);
				scrollPane.setBounds(137, 70, 169, 100);
				scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Mostrar siempre la
																								// barra vertical

				// Agregar el JScrollPane al panel
				panelVerAGM.add(scrollPane);

				// Mostrar el árbol generador mínimo en una ventana emergente
				cambiarPanel(panelVerAGM);
			}
		});
		btnEjecutarAGM.setBounds(152, 205, 132, 36);
		panelCargarArista.add(btnEjecutarAGM);

		JButton btnsepararEnRegiones = new JButton("Separar en regiones");
		btnsepararEnRegiones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiarPanel(panelIngresarK);
			}
		});
		btnsepararEnRegiones.setBounds(294, 205, 132, 36);
		panelCargarArista.add(btnsepararEnRegiones);

		// Boton volver
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiarPanel(panelCargarArista);
			}
		});
		btnVolver.setBounds(168, 229, 89, 23);
		panelVerGrafo.add(btnVolver);
		JButton btnVolver2 = new JButton("Volver");
		btnVolver2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiarPanel(panelCargarArista);
			}
		});
		btnVolver2.setBounds(168, 229, 89, 23);
		panelVerAGM.add(btnVolver2);

		JLabel lblIngresarK = new JLabel("Ingrese la cantidad de regiones  ");
		lblIngresarK.setFont(new Font("Arial", Font.PLAIN, 17));
		lblIngresarK.setBounds(101, 11, 259, 74);
		panelIngresarK.add(lblIngresarK);

		JLabel lblIngresarK1 = new JLabel("en que quiere dividir el grafo");
		lblIngresarK1.setFont(new Font("Arial", Font.PLAIN, 17));
		lblIngresarK1.setBounds(111, 37, 223, 74);
		panelIngresarK.add(lblIngresarK1);

		textFieldAgregarK = new JTextField();
		textFieldAgregarK.setBounds(171, 117, 96, 26);
		panelIngresarK.add(textFieldAgregarK);
		textFieldAgregarK.setColumns(10);

		// Botón "Ver regiones"
		JButton btnVerRegiones = new JButton("Ver regiones");
		btnVerRegiones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Ejecutar algoritmo de Kruskal y eliminar aristas según el valor de K
				int k = Integer.parseInt(textFieldAgregarK.getText());
				grafo.caminoMinimoKruskal(grafo.tamano());
				grafo.eliminarAristasKMenos1(grafo.getListAristas(), k);

				// Crear el segundo frame para mostrar el resultado
				JFrame frame2 = new JFrame("Resultado del algoritmo");
				frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame2.setSize(450, 300);

				// Crear un JTextArea para mostrar las aristas
				JTextArea txtAreaResultado = new JTextArea();
				txtAreaResultado.setEditable(false);

				// Obtener las aristas del grafo después de aplicar Kruskal y eliminar aristas
				ArrayList<Arista> aristasFinales = grafo.getListAristas();

				// Construir la representación del resultado
				StringBuilder sb = new StringBuilder();
				sb.append("Aristas del árbol generador mínimo después de eliminar las ").append(k - 1)
						.append(" aristas de mayor peso:\n");
				for (Arista arista : aristasFinales) {
					sb.append(arista.getV1().getNombre()).append(" - ")
							.append(arista.getV2().getNombre()).append(": ")
							.append(arista.getPesoArista()).append("\n");
				}

				// Establecer el texto del JTextArea
				txtAreaResultado.setText(sb.toString());

				JScrollPane scrollPane = new JScrollPane(txtAreaResultado);
				scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Mostrar siempre la
																								// barra vertical
				frame2.getContentPane().add(scrollPane);

				// Mostrar el segundo frame
				frame2.setVisible(true);

				// Cerrar el primer frame
				frame.dispose(); //
			}
		});
		btnVerRegiones.setBounds(171, 194, 120, 23); // Ajusta las coordenadas y tamaño según tus necesidades
		panelIngresarK.add(btnVerRegiones); // Añade el botón al panel

	}
}
