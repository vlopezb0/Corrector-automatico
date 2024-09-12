import com.sun.media.jsdt.*;
import java.io.*;

public class Servidor
{
static String type="socket";
static Session sesion1,sesion2;
static Channel canal1,canal2,canal3,canal4,canal5;
static Cliente c1,c2,c3,c4,c5,clSes1,clSes2;

public static void main(String args[])throws IOException
{
System.out.println("Pulsa 1 para crear/parar un registro");
System.out.println("Pulsa 2 para crear un cliente, una sesion y un canal");
System.out.println("Pulsa 3 para listar las sesiones activas");
System.out.println("Pulsa 4 para salir");

BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
String opcion = in.readLine();
/*switch(opcion){
case "1" :
crearRegistro();
break;
case "2" :
crearCliente();
break;
case "3" : break;
}*/

/*
crearClientes();
crearSesiones();
crearCanales();
comunicarDatos();
desconectar();*/
}

public static void crearRegistro()
{
try {
 RegistryFactory.startRegistry(type);
 System.out.println("Registro iniciado");
if(RegistryFactory.registryExists(type))
System.out.println("Registro finalizado");
 } catch(NoRegistryException nre) {
 System.out.println("No puede iniciarse el registro");
 } catch(RegistryExistsException e){;}
}

public static void crearCliente()throws IOException{
BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
System.out.println("Introduzca el numero de puerto: ");
int puerto = Integer.parseInt(in.readLine());

System.out.println("Introduzca el nombre que le va a dar a la sesion: ");
String nombSes= in.readLine();

System.out.println("Introduzca el nombre del cliente: ");
String nombClient= in.readLine();

System.out.println("Introduzca el nombre del canal: ");
String nombCanal= in.readLine();

try{
URLString url1 = URLString.createSessionURL("localhost", puerto, type, nombSes);
Cliente c1=new Cliente(nombClient);
sesion1 = SessionFactory.createSession(clSes1, url1, true);
sesion1.join(c1);
canal1 = sesion1.createChannel(c1, nombCanal, true, true, true);
}catch(Exception e){
e.printStackTrace();
}
}

static void crearSesiones()
{
try {
// sesin 1
URLString url1 = URLString.createSessionURL("localhost", 1000, type, "sesion1");
clSes1=new Cliente("clSes1");
sesion1 = SessionFactory.createSession(clSes1, url1, true);
// sesin 2
URLString url2 = URLString.createSessionURL("localhost", 1000, type, "sesion2");
clSes2=new Cliente("clSes2");
sesion2 = SessionFactory.createSession(clSes2, url2, false);
} catch(Exception e) {
e.printStackTrace();
}
}

static void crearCanales()
{
try {
// canales sesin 1
sesion1.join(c1); // los clientes deben unirse primero a la sesin
sesion1.join(c2);
sesion1.join(c3);
canal1 = sesion1.createChannel(c1, "canal1", true, true, true); // con auto-join
canal1.addConsumer(c1,c1);
canal1.join(c2);
canal1.addConsumer(c2,c2);
canal2 = sesion1.createChannel(c1, "canal2", true, true, true);
canal2.addConsumer(c1,c1);
canal2.join(c3);
canal2.addConsumer(c3,c3);
canal3 = sesion1.createChannel(c2, "canal3", true, true, true);
canal3.addConsumer(c2,c2);
canal3.join(c3);
canal3.addConsumer(c3,c3);
// canales sesin 2
sesion2.join(c1);
sesion2.join(c2);
sesion2.join(c4);
sesion2.join(c5);
canal4 = sesion2.createChannel(c1, "canal4", true, true, true);
canal4.addConsumer(c1,c1);
canal4.join(c2);
canal4.addConsumer(c2,c2);
canal4.join(c4);
canal4.addConsumer(c4,c4);
canal4.join(c5);
canal4.addConsumer(c5,c5);
canal5 = sesion2.createChannel(c4, "canal5", true, true, true);
canal5.addConsumer(c4,c4);
canal5.join(c5);
canal5.addConsumer(c5,c5);
} catch(Exception e) {
e.printStackTrace();
}
}

static void crearClientes()
{
c1=new Cliente("c1");
c2=new Cliente("c2");
c3=new Cliente("c3");
c4=new Cliente("c4");
c5=new Cliente("c5");
}

static void comunicarDatos()
{
try {
// envo de datos por los cinco canales
Data data = new Data("hola");
canal1.sendToOthers(c1, data);
canal2.sendToOthers(c1, data);
canal3.sendToOthers(c2, data);
canal4.sendToOthers(c1, data);
canal5.sendToOthers(c4, data);
} catch(Exception e) {
e.printStackTrace();
}
}

static void desconectar()
{
try {
// clientes salen de canales
canal1.leave(c1);
canal1.leave(c2);
canal2.leave(c1);
canal2.leave(c3);
canal3.leave(c2);
canal3.leave(c3);
canal4.leave(c1);
canal4.leave(c2);
canal4.leave(c4);
canal4.leave(c5);
canal5.leave(c4);
canal5.leave(c5);
// clientes salen de sesiones
sesion1.leave(c1);
sesion1.leave(c2);
sesion1.leave(c3);
sesion2.leave(c1);
sesion2.leave(c2);
sesion2.leave(c4);
sesion2.leave(c5);
// cerrar canales, sesiones y registro
sesion1.close(true);
System.out.println("Sesion 1 cerrada");
sesion2.close(true);
System.out.println("Sesion 2 cerrada");
RegistryFactory.stopRegistry(type);
System.exit(0);
} catch(Exception e) {
System.out.println("No se pueden liberar los recursos");
e.printStackTrace();
}
}
}
