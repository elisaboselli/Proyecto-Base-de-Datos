/*compilacion javac nombre.java
ejecucion java -cp ".:driver.jar" nombre*/
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class apiManejodeBD{

	private static Connection Conexion;
	private final String nombreBD = "CiudadDeLosNiños";
    private final String userName = "postgres";
    private final String password = "root";
    private Scanner in = new Scanner(System.in);
    SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");


    //Conexion a la base de datos.
    public void connect() {
        try {
            Class.forName("org.postgresql.Driver");
            Conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+nombreBD, userName, password);
            System.out.println("Se ha iniciado la conexión con el servidor de forma exitosa");
        } 
        catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            //Logger.getLogger(apiManejodeBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException ex) {
        	ex.printStackTrace();
        	System.err.println(ex.getClass().getName()+": "+ex.getMessage());
        }
    }

    //Desconeccion de la base de datos.
    public void disconnect() {
        try {
            Conexion.close();
            System.out.println("Se ha finalizado la conexión con el servidor");
        } catch (SQLException ex) {
            System.err.println(ex.getClass().getName()+": "+ex.getMessage());
        }
    }
	
	//Control para valores nulos.
	public void controlNull(String var){
		if (var==" "||var=="null"||var=="NULL"||var=="Null"){
			var=null;
		}
	}
	
	//Funcion para pedir datos.
	//Aun no esta usada ni probada.
	public void Peticion(String peticion, String destino){
		System.out.print(peticion);
		destino=in.next();
		controlNull(destino);
	}

	//Inserta una persona en la base de datos (Tabla: Persona).
	public void insertarPersona() throws ParseException{
		try {
			//FALTA CONTROL DE SI NO ESTABA.
			//Solicita los datos de la nueva persona.
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
			
			//Y la carga en la base de datos.
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

	//Inserta un nuevo padrino a la base de datos (Tabla Contacto).
	//PARA MI LO TENDRIA QUE INSERTAR EN DONANTE.
	public void insertarContacto() throws ParseException{
		try {
			//Solicita DNI del nuevo contacto.
			String dnip;
			System.out.println("Ingrese dni del nuevo padrino: ");
			dnip = in.next();

			//Busca en la tabla persona si ya existe una persona con ese DNI. 
			PreparedStatement st = Conexion.prepareStatement("SELECT dni FROM CiudadDeLosNinios.Persona WHERE Persona.dni= ?");
			st.setString(1, dnip);
			ResultSet rs = st.executeQuery();

			if (!(rs.next())){
				//Si no existe ninguna persona con ese DNI, llama a insertar persona y solicita sus datos.
				insertarPersona();
			}

			//Solicita los datos del nuevo padrino.
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
			
			//Y lo carga a la base de datos.
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
            System.out.println("Error en el almacenamiento de datos");
		}
	}

	//Elimina un donante de la case de datos, pero lo deja como contacto.
	public void eliminarDonante(){
		//ACA TAMBIEN FALTA EL CONTROL.
		//Solicita el DNI del donante a eliminar.
		System.out.println("Ingrese el DNI del donante a eliminar");
		String dni = in.next();
		try{
			//Y lo borra de la base de datos.
			PreparedStatement st = Conexion.prepareStatement("DELETE FROM CiudadDeLosNinios.Donante WHERE CiudadDeLosNinios.Donante.DNI =?");
			st.setString(1, dni);
			st.executeUpdate();
		}
		catch(SQLException ex){
		ex.printStackTrace();	
		}
	}

	//Muestra todos los padrinos, con los respectivos programas a los que aporta, junto con el monto y fecuencia del aporte.
	public void listarPadrinos(){

	}
	
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

	
	
}
