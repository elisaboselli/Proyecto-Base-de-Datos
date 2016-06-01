/*compilacion javac nombre.java
ejecucion java -cp ".driver.jar" nombre*/
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.swing.JOptionPane;

public class apiManejodeBD{

	private static Connection Conexion;
    private String userName = "usuario";
    private String password = "root";
    private Scanner in = new Scanner(System.in);
    SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");


    public void ApiConnection(String nombreBD) {
        try {
            Class.forName("org.postgresql.Driver");
              System.out.println(nombreBD +","+ userName+","+ password);
            Conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + nombreBD, userName, password);
            System.out.println("Se ha iniciado la conexión con el servidor de forma exitosa");
        } catch (ClassNotFoundException ex) {
            //Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);

            System.err.println(ex.getClass().getName()+": "+ex.getMessage());
        } catch (SQLException ex) {
        	ex.printStackTrace();
        	System.err.println(ex.getClass().getName()+": "+ex.getMessage());
            //Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void closeConnection() {
        try {
            Conexion.close();
            System.out.println("Se ha finalizado la conexión con el servidor");
        } catch (SQLException ex) {
            //Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex.getClass().getName()+": "+ex.getMessage());
        }
    }
	
	public void controlNull(String var){
		if (var==""){
			var=null;
		}
	}
	
	public void insertarPersona() throws ParseException{
		try {
			String dni;
			String nombre;
			String apellido;
			String direccion;
			String codpos;
			String email;
			String face;
			String telfijo;
			String telcel;
			String fnac;
			
			System.out.print("Ingrese dni: ");
			dni = in.next();
			controlNull(dni);
			System.out.print("Ingrese nombre: ");
			nombre = in.next();
			controlNull(nombre);
			System.out.print("Ingrese apellido: ");
			apellido = in.next();
			controlNull(apellido);
			System.out.print("Ingrese direccion: ");
			direccion = in.next();
			controlNull(direccion);
			System.out.print("Ingrese codigo postal: ");
			codpos = in.next();
			controlNull(codpos);
			System.out.print("Ingrese email: ");
			email = in.next();
			controlNull(email);
			System.out.print("Ingrese facebook: ");
			face = in.next();
			controlNull(face);
			System.out.print("Ingrese telefono fijo: ");
			telfijo = in.next();
			controlNull(telfijo);
			System.out.print("Ingrese telefono celular: ");
			telcel = in.next();
			controlNull(telcel);
			System.out.print("Ingrese fecha nacimiento: ");
			fnac = in.next();
			controlNull(fnac);
			Date parsed = format.parse(fnac);
			java.sql.Date fnacdate = new java.sql.Date(parsed.getTime());
			
			/*String query = "INSERT INTO CiudadDeLosNinios.Persona VALUES("
			+"\""+ dni +"\","
			+"\""+ nombre +"\","
			+"\""+ apellido +"\","
			+"\""+ direccion +"\","
			+"\""+ codpos +"\","
			+"\""+ email +"\","
			+"\""+ face +"\","
			+"\""+ telfijo +"\","
			+"\""+ telcel +"\","
			+"\""+ fnac +"\")";*/
			PreparedStatement st = Conexion.prepareStatement("INSERT INTO CiudadDeLosNinios.Persona VALUES(?,?,?,?,?,?,?,?,?,?)");
			st.setString(1, dni);
			st.setString(2, nombre);
			st.setString(3, apellido);
			st.setString(4, direccion);
			st.setString(5, codpos);
			st.setString(6, email);
			st.setString(7, face);
			st.setString(8, telfijo);
			st.setString(9, telcel);
			st.setDate(10, fnacdate);
			st.executeUpdate();
		}
		catch (SQLException ex){
		ex.printStackTrace();
		}
	}
	public void eliminarDonante(){
		System.out.println("Ingrese el DNI del donante a eliminar");
		String dni = in.next();
		try{
			//String query = "DELETE FROM CiudadDeLosNinios.Donante WHERE CiudadDeLosNinios.Donante.DNI = " +dni ;
			PreparedStatement st = Conexion.prepareStatement("DELETE FROM CiudadDeLosNinios.Donante WHERE CiudadDeLosNinios.Donante.DNI =?");
			st.setString(1, dni);
			st.executeUpdate();
		}
		catch(SQLException ex){
		ex.printStackTrace();	

		}


	}

	//public void listarPadrinos(){

	//}
	
	/*
	public void insertarDonante(){
		String DNI;
		String ocupacion;
		String CUIL_CUIT;
		try{	
			System.out.println("Ingrese el DNI del donante: ");
			DNI = in.next();
			controlNull(DNI);
			System.out.println("Ingrese la ocupacion del donante: ");
			ocupacion = in.next();
			controlNull(ocupacion);
			System.out.println("Ingrese el CUIL/CUIT del donante: ");
			CUIL_CUIT= in.next();
			controlNull(CUIL_CUIT);

			String query = "INSERT INTO CiudadDeLosNinios.Persona VALUES("
			+"\""+ DNI +"\","
			+"\""+ ocupacion +"\","
			+"\""+ CUIL_CUIT +"\")";
			Statement st = Conexion.createStatement();
			st.executeUpdate(query);
		}
		catch (SQLException ex){
		}
	}
	*/
	public void insertarPadrino() throws ParseException{

		try {
			String dnip;
			System.out.println("Ingrese dni del nuevo padrino: ");
			dnip = in.next();
			//String query = "SELECT dni FROM CiudadDeLosNinios.Persona WHERE Persona.dni="+dnip+"\"";
			PreparedStatement st = Conexion.prepareStatement("SELECT dni FROM CiudadDeLosNinios.Persona WHERE Persona.dni= ?");
			st.setString(1, dnip);
			ResultSet rs = st.executeQuery();
			if (!(rs.next())){
				insertarPersona();
			}
			String fpc;
			String fa;
			String fb;
			String fr;
			String estado;
			String rfdni;
			String rfrel;
			String rfcom;
			

			System.out.println("Ingrese fecha primer contacto: ");
			fpc = in.next();
			controlNull(fpc);
			Date parsed = format.parse(fpc);
			java.sql.Date fpcdate = new java.sql.Date(parsed.getTime());
			System.out.println("Ingrese fecha alta: ");
			fa = in.next();
			controlNull(fa);
			parsed = format.parse(fa);
			java.sql.Date fadate = new java.sql.Date(parsed.getTime());
			System.out.println("Ingrese fecha baja: ");
			fb = in.next();
			controlNull(fb);
			parsed = format.parse(fb);
			java.sql.Date fbdate = new java.sql.Date(parsed.getTime());
			System.out.println("Ingrese fecha rechazo: ");
			fr = in.next();
			controlNull(fr);
			parsed = format.parse(fr);
			java.sql.Date frdate = new java.sql.Date(parsed.getTime());
			System.out.println("Ingrese estado: ");
			estado = in.next();
			controlNull(estado);
			System.out.println("Ingrese dni de la referencia: ");
			rfdni = in.next();
			controlNull(rfdni);
			System.out.println("Ingrese relacion con el referente: ");
			rfrel = in.next();
			controlNull(rfrel);
			System.out.println("Ingrese cometario: ");
			rfcom = in.next();
			controlNull(rfcom);
			/*query = "INSERT INTO CiudadDeLosNinios.Contacto VALUES("
			+"\""+ dnip +"\","	
			+"\""+ fpc +"\","	
			+"\""+ fa +"\","	
			+"\""+ fb +"\","	
			+"\""+ fr +"\","	
			+"\""+ estado +"\","	
			+"\""+ rfdni +"\","
			+"\""+ rfrel +"\","	
			+"\""+ rfcom +"\")";*/
			PreparedStatement st2 = Conexion.prepareStatement("INSERT INTO CiudadDeLosNinios.Contacto VALUES(?,?,?,?,?,?,?,?,?)");
			st2.setString(1, dnip);
			st2.setDate(2, fpcdate);
			st2.setDate(3, fadate);
			st2.setDate(4, fbdate);
			st2.setDate(5, frdate);
			st2.setString(6, estado);
			st2.setString(7, rfdni);
			st2.setString(8, rfrel);
			st2.setString(9, rfcom);
			st2.executeUpdate();
		}
		catch(SQLException ex){
			ex.printStackTrace();
			System.out.println(ex.getMessage());
            //OptionPane.showMessageDialog(null, "Error en el almacenamiento de datos");
            System.out.println("Error en el almacenamiento de datos");
		}
	}
	
}
