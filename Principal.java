import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Principal{
public static void main(String[] args) throws ParseException{
		apiManejodeBD BaseCuidadDeLosNiños = new apiManejodeBD();
		BaseCuidadDeLosNiños.connect();
		//BaseCuidadDeLosNiños.insertarContacto();
		BaseCuidadDeLosNiños.eliminarDonante();
		BaseCuidadDeLosNiños.disconnect();

	}

}