package negocio;

import java.util.ArrayList;
import java.util.List;

import modelo.Categoria;

public class GestionCategoria {
	private List<Categoria> categorias;

	public GestionCategoria() {
		categorias = new ArrayList<Categoria>();
	}

	public void agregarCategoria(int codigo,String nombre, String descripcion) {
		Categoria c = new Categoria();
		c.setCodigo(codigo);
		c.setNombre(nombre);
		c.setDescripcion(descripcion);
		categorias.add(c);
	}

	public List<Categoria> getCategorias() {

		return categorias;
	}
}
