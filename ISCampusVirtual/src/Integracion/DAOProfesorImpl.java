package Integracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Negocio.Usuario.TransferAlumno;
import Negocio.Usuario.TransferProfesor;
import Negocio.Usuario.TransferUsuario;

public class DAOProfesorImpl implements DAOProfesor {
	
	static String bd = "campus_virtual";
	static String login = "root";
	static String password = "grupo7";
	static String url = "jdbc:mysql://localhost/" + bd;

	@Override
	public void eliminate(String id) {
		
	try {
		String s = "DELETE FROM profesores WHERE IdProfesor = ?";
		
		Connection connection = DriverManager.getConnection(url, login, password);
		PreparedStatement ps = connection.prepareStatement(s);
		
		ps.setString(1,id);
		ps.executeUpdate();
		
		connection.close();
		ps.close();
		
	}catch(Exception e) {
	}
	}

	@Override
	public TransferUsuario readById(String id) {
		
		TransferProfesor TP =null;
		try {
			
			String s = "SELECT * FROM usuarios WHERE correo_electronico = ?;";
			
			Connection connection = DriverManager.getConnection(url, login, password);
			PreparedStatement ps = connection.prepareStatement(s);
			ps.setString(1, id);
			
			ResultSet r = ps.executeQuery();
			
			if(r.next()) {
				TP = new TransferProfesor();
				TP.setNombre_Apellidos(r.getString("Nombre") + " " + r.getString("Apellidos"));
				TP.setCorreo_electronico(r.getString("Correo_electronico"));
				TP.setPassword(r.getString("Contrasenia"));
				TP.setNIF(r.getString("NIF"));
			}
			
			s = "SELECT * FROM profesores WHERE NIF = ?;";
			
			ps = connection.prepareStatement(s);
			
			ps.setString(1, TP.getNIF());
			r = ps.executeQuery();
			
			if(r.next()) {
				TP.setId(r.getString("IdProfesor"));
				TP.setAsignaturas(new ArrayList<String>());
			}
			
			connection.close();
			ps.close();
			r.close();
			
		}catch(Exception e) {
			
		}
		
		return TP;
	}

	@Override
	public void create(TransferProfesor aTNew) {

		try {
		String s = "INSERT INTO profesores (IdProfesor, NIF, IdAsignatura) VALUES (?,?,?)";

		Connection connection = DriverManager.getConnection(url, login, password);
		PreparedStatement ps = connection.prepareStatement(s);
		
		ps.setString(1, aTNew.getId());
		ps.setString(2, aTNew.getNIF());
		ps.setArray(3, null);
		ps.executeUpdate();
		
		connection.close();
		ps.close();
		
		}catch(Exception e) {
			
		}
		
	}

	@Override
	public void create(TransferUsuario aTNew) {
		// TODO Auto-generated method stub
		
	}

}