import com.sun.media.jsdt.*;
import java.io.*;
import java.lang.Integer;

public class Servidor
{
static String type="socket"; //para decir que la implementacion est basada en socket
static int opcion=9;
//static Session sesion1,sesion2;
//static Channel canal1,canal2,canal3,canal4,canal5;
//static Cliente c1,c2,c3,c4,c5,clSes1,clSes2;

public static void main(String args[])
{
mostrarMenu();
try{
opcion = Integer.parseInt((String)System.in.read());
}catch (IOException ex) {
System.out.println("No se a podido leer la entrada");
}
while(opcion!=4){
switch(opcion){
case(1):
lanzarPararRegistro();
break;
case(2):
Crear();
break;
default:
System.out.println("Opcion no valida");
break;
 }
mostrarMenu();
}//while
}//main

public static void Crear()
{
}
public static void lanzarPararRegistro(){
 try {
 RegistryFactory.startRegistry(type);//lanzo el registro
 System.out.println("Registro iniciado");
 } catch(NoRegistryException nre) {
 System.out.println("No puede iniciarse el registro");
 } catch(RegistryExistsException ree) {
 //el registro ya est iniciado y lo paramos
try {
RegistryFactory.stopRegistry(type);
}
catch(Exception e) {
System.out.println("no se puede parar el registro");
}
 }
}

public static void mostrarMenu(){
System.out.println("------------ MENU ------------");
System.out.println("1. Iniciar/parar registro");
System.out.println("2. Crear Cliente, sesion y canal");
System.out.println("3. Listar Sesiones Activas");
System.out.println("4. Salir");
System.out.print("\n\n Seleciona una de las opciones: ");
try{
 Integer.parseInt((String)System.in.read());
 }catch (IOException ex) {
System.out.println("No se a podido leer la entrada");
 }
}
}//Class Servidor
