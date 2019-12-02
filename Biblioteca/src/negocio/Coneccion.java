package negocio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Statement;


public class Coneccion {
	
Connection conect =null;

	public Connection conexion() {
		
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			//org.gjt.mm.mysql.Driver
			//com.mysql.jdbc.Driver
			conect = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca","root","");
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			//JOptionPane.showMessageDialog(null, "Error"+e);
		}
		return conect;

	}
	public void datos() throws SQLException {
		Statement s = (Statement) conect.createStatement();
		ResultSet rs = s.executeQuery ("select * from categoria");
		while (rs.next())
		{
		    System.out.println (rs.getInt (1) + " |" + rs.getString (2)+ "| " + rs.getString(3));
		}
	}
}
