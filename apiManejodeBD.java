/*compilacion javac nombre.java
ejecucion java -cp ".driver.jar" nombre*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.swing.JOptionPane;

public class apiManejodeBD{
	private static Connection Conexion;
    private String userName = "postgres";
    private String password = "root";
    private Scanner in = new Scanner(System.in);

    public void ApiConnection(Sting nombreBD) {
        try {
            Class.forName("org.postgresql.Driver");
            Conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + nombreBD, userName, password);
            System.out.println("Se ha iniciado la conexión con el servidor de forma exitosa");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void closeConnection() {
        try {
            Conexion.close();
            System.out.println("Se ha finalizado la conexión con el servidor");
        } catch (SQLException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
	
	public void controlNull(String var){
		if (var=""){
			var=null;
		}
	}
	
	public void insertarPersona(){
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
			
			String query = "INSERT INTO CiudadDeLosNinios.Persona VALUES("
			+"\""+ dni +"\","
			+"\""+ nombre +"\","
			+"\""+ apellido +"\","
			+"\""+ direccion +"\","
			+"\""+ codpos +"\","
			+"\""+ email +"\","
			+"\""+ face +"\","
			+"\""+ telfijo +"\","
			+"\""+ telcel +"\","
			+"\""+ fnac +"\")";
			Statement st = Conexion.createStatement();
			st.executeUpdate(query);
		}
		catch (SQLException ex){
		
		}
	}
	
	public void insertarDonante(){
	}
	
	public void insertarPadrino(){
		try {
			String dnip;
			System.out.println("Ingrese dni del nuevo padrino: ");
			dnip = in.next();
			String query = "SELECT dni FROM CiudadDeLosNinios.Persona WHERE Persona.dni="+dnip+"\"";
			Statement st = Conexion.cretateStatement();
			ResultSet rs = st.executeQuery(query);
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
			System.out.println("Ingrese fecha alta: ");
			fa = in.next();
			controlNull(fa);
			System.out.println("Ingrese fecha baja: ");
			fb = in.next();
			controlNull(fb);
			System.out.println("Ingrese fecha rechazo: ");
			fr = in.next();
			controlNull(fr);
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
			String query = "INSERT INTO CiudadDeLosNinios.Contacto VALUES("
			+"\""+ dnip +"\","	
			+"\""+ fpc +"\","	
			+"\""+ fa +"\","	
			+"\""+ fb +"\","	
			+"\""+ fr +"\","	
			+"\""+ estado +"\","	
			+"\""+ rfdni +"\","
			+"\""+ rfrel +"\","	
			+"\""+ rfcom +"\")";		
		}
		catch{
			System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error en el almacenamiento de datos");
		}
	}
}
