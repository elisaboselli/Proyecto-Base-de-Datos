import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Principal{
public static void main(String[] args) throws ParseException{
		String dbname = "Base de datos 1";
		apiManejodeBD BaseCuidadDeLosNiños = new apiManejodeBD();
		BaseCuidadDeLosNiños.ApiConnection(dbname);
		//BaseCuidadDeLosNiños.insertarPadrino();
		BaseCuidadDeLosNiños.eliminarDonante();
		BaseCuidadDeLosNiños.closeConnection();

	}

}