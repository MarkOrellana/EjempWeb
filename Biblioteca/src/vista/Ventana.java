package vista;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import negocio.Coneccion;
import negocio.GestionCategoria;
import modelo.Categoria;

public class Ventana extends JFrame implements ActionListener {
	private JTextField txtCodigo;
	private JTextField txtnombre;
	private JTextField txtDescripcion;
	private java.sql.Statement st;
	private java.sql.PreparedStatement psInsertar;
	private Connection con;
	private GestionCategoria gp;
	private JTable tablaCategorias;

	public Ventana() {
		super("PracticaBiblioteca");
		setSize(300, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new GridLayout(1, 1));

		JLabel etiqueta1 = new JLabel("Codigo: ");
		JLabel etiqueta2 = new JLabel("NombreCategoria: ");
		JLabel etiqueta3 = new JLabel("Descripcion: ");
		txtCodigo = new JTextField(20);
		txtnombre = new JTextField(20);
		txtDescripcion = new JTextField(20);
		// txtListado.setPreferredSize(new Dimension(250, 100));
		JButton boton1 = new JButton("Cargar");
		boton1.addActionListener(this);
		boton1.setActionCommand("btnCargar");

		JButton boton2 = new JButton("Vaciar");
		boton2.addActionListener(this);
		boton2.setActionCommand("btnVaciar");

		JButton boton3 = new JButton("Terminar");
		boton3.addActionListener(this);
		boton3.setActionCommand("btnTerminar");

		JPanel panel1 = new JPanel();
		panel1.setLayout(new FlowLayout());
		panel1.setBorder(BorderFactory.createTitledBorder("Datos Categoria"));
		getContentPane().add(panel1);
		panel1.add(etiqueta1);
		panel1.add(txtCodigo);
		panel1.add(etiqueta2);
		panel1.add(txtnombre);
		panel1.add(etiqueta3);
		panel1.add(txtDescripcion);
		panel1.add(boton1, BorderLayout.SOUTH);
		panel1.add(boton2, BorderLayout.SOUTH);
		panel1.add(boton3, BorderLayout.SOUTH);

		gp = new GestionCategoria();

	}

	public void actionPerformed(ActionEvent evt) {
		// System.out.println("evento boton");

		String comando = evt.getActionCommand();

		System.out.println("Button insertado: " + comando);

		switch (comando) {
		case "btnTerminar":
			terminar();
			break;
		case "btnCargar":
			cargar();
			break;
		case "btnVaciar":
			vaciar();
			break;
		default:
			break;
		}

	}

	public void cargar() {
		int codigo = Integer.parseInt(txtCodigo.getText());
		String nombre = txtnombre.getText();
		String descripcion = txtDescripcion.getText();
		gp.agregarCategoria(codigo, nombre, descripcion);
		JOptionPane.showMessageDialog(this, "Datos guardados", "Mensaje de información", JOptionPane.ERROR_MESSAGE);
		try {
			datosInsertar();
			System.out.println("CategoriaInsertada");
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void datosInsertar() throws SQLException {
		int codigo = Integer.parseInt(txtCodigo.getText());
		String nombre = txtnombre.getText();
		String descripcion = txtDescripcion.getText();
		String url = "jdbc:mysql://localhost:3306/biblioteca?user=root";
		con = DriverManager.getConnection(url);
		st = con.createStatement();
		psInsertar = con.prepareStatement("INSERT INTO CATEGORIA (codigo,nombre,descripcion)" + "VALUES(?,?,?)");
		psInsertar.setLong(1, codigo);
		psInsertar.setString(2, nombre);
		psInsertar.setString(3, descripcion);
		psInsertar.executeUpdate();

	}

	public void terminar() {
		int opcion = JOptionPane.showConfirmDialog(this, "Desea dar por terminado el programa ?", "Mensaje",
				JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
		if (opcion == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
		if (opcion == JOptionPane.NO_OPTION) {
			JOptionPane.showMessageDialog(this, "Siga trabajando", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void vaciar() {
		txtCodigo.setText("");
		txtnombre.setText("");
		txtDescripcion.setText("");
	}
}
