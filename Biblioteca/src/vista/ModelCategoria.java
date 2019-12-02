package vista;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

import modelo.Categoria;

public class ModelCategoria extends AbstractTableModel {
	public String[] columnas = { "Nombre", "Descripciom" };

	public Class[] columnasTipos = { String.class, String.class };

	private List<Categoria> datos;

	public ModelCategoria() {
		super();
		datos = new ArrayList<Categoria>();
	}

	public int getColumnCount() {
		return columnas.length;
	}

	public int getRowCount() {
		return datos.size();
	}

	public void setValueAt(Object value, int row, int col) {
		Categoria dato = (Categoria) (datos.get(row));

		switch (col) {
		case 0:
			dato.setNombre((String) value);
			break;
		case 1:
			dato.setDescripcion((String) value);
			break;
		default:
			break;
		}
	}

	public String getColumnName(int col) {
		return columnas[col];
	}

	public Class getColumnClass(int col) {
		return columnasTipos[col];
	}

	public Object getValueAt(int row, int col) {
		Categoria dato = (Categoria) (datos.get(row));

		switch (col) {
		case 0:
			return dato.getNombre();
		case 1:
			return dato.getDescripcion();
		default:
			break;
		}
		return new String();
	}

}
