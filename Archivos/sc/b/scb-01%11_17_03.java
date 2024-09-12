import com.sun.media.jsdt.*;

public class Servidor
{
static String type="socket"; //para decir que la implementacion est basada en socket
static Session sesion1,sesion2;
static Channel canal1,canal2,canal3,canal4,canal5;
static Cliente c1,c2,c3,c4,c5,clSes1,clSes2;

public static void main(String args[])
{
mostrarMenu();
try{
char opcion=System.in.read();
}catch (IOException ex) { }
System.ou.println("opcion seleccionada = "+opcion);
}//main

public void mostrarMenu(){
System.out.println("------------ MENU ------------");
System.out.println("1. Iniciar/parar registro");
System.out.println("2. Crear Cliente, sesion y canal");
System.out.println("3. Listar Sesiones Activas");
System.out.println("4. Salir");
System.out.print("\n\n Seleciona una de las opciones: ");
}
}//Class Servidor
