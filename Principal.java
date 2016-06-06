import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

public class Principal{
	private static Scanner in = new Scanner(System.in);

	public static boolean valida(Integer s){
		return (s==1||s==2||s==3||s==4);
	}

	public static Integer solicitarNueva(Integer s){
		
		while(!valida(s)){
			System.out.println("Ingrese la opcion deseada:");
			System.out.println("1) Agregar nuevo contacto");
			System.out.println("2) Eliminar donante");
			System.out.println("3) Listar padrinos");
			System.out.println("4) Salir de la Base de Datos");
			System.out.println("\n");
			s = in.nextInt();
			System.out.println("2º"+s);
		}
		
		return s;
	}

	public static void main(String[] args) throws ParseException{
		System.out.print("\033[H\033[2J");
		System.out.flush();
		Integer seleccion=0;
		apiManejodeBD BaseCuidadDeLosNiños = new apiManejodeBD();
		BaseCuidadDeLosNiños.connect();
		System.out.println("Bienvenido a la Base de Datos de Ciudad de los Niños");
		System.out.println("\n");
		System.out.println("\n");
		//do{
		while(seleccion!=4){
			seleccion=solicitarNueva(seleccion);
			System.out.print("\033[H\033[2J");
			System.out.flush();
			System.out.println("Seleccion: "+seleccion);
			switch(seleccion){
				case 1 :
					BaseCuidadDeLosNiños.insertarContacto();
					break;

				case 2:
					BaseCuidadDeLosNiños.eliminarDonante();
					break;

				case 3:
					BaseCuidadDeLosNiños.listarPadrinos();
					break;

				case 4:
					BaseCuidadDeLosNiños.disconnect();
					break;
			}
			if(seleccion!=4){
			//	Scanner wait = new Scanner(System.in);
				System.out.println ("Presiona cualquier tecla para continuar");
				Scanner cont = new Scanner(System.in);
				//wait.next();
				String a = cont.next();
				System.out.print("\033[H\033[2J");
				System.out.flush();
				System.out.println("Paso limpieza");
			}
			
		} //while(seleccion!=4);

	}

}
