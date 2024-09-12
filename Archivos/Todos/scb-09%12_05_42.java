import com.sun.media.jsdt.Channel;
import com.sun.media.jsdt.ChannelConsumer;
import com.sun.media.jsdt.Client;
import com.sun.media.jsdt.Data;
import com.sun.media.jsdt.JSDTException;
import com.sun.media.jsdt.NoRegistryException;
import com.sun.media.jsdt.RegistryExistsException;
import com.sun.media.jsdt.RegistryFactory;
import com.sun.media.jsdt.Session;
import com.sun.media.jsdt.SessionFactory;
import com.sun.media.jsdt.URLString;
import java.io.*;


public class Experimento2 {

public static void main(String []args) throws IOException{

BufferedReader leer;
int opcion = 0;
String s;

while (opcion!=5){
System.out.println("[1] Inicio del Registro de JSDT");
System.out.println("[2] Parada del Registro de JSDT");
System.out.println("[3] Creacin de un cliente una sesin y un canal");
System.out.println("[4] Listar las sesiones activas");
System.out.println("[5] Salir");

System.out.println("Selecciona una opcion:");

leer = new BufferedReader(new InputStreamReader(System.in));
s = leer.readLine();
System.out.println();

opcion = Integer.parseInt(s.toString());

if (opcion==1){
lanzarRegistro();
}
else if (opcion==2){
detenerRegistro();
}
else if (opcion==3){
System.out.println(">> Introduce el nombre del cliente: ");
String nombreCliente=leer.readLine();
System.out.println(">> Introduce el nombre de la sesin: ");
String nombreSesion=leer.readLine();
System.out.println(">> Introduce el nombre del canal: ");
String nombreCanal=leer.readLine();
System.out.println(">> Introduce el puerto: ");
int puerto=Integer.parseInt(leer.readLine());

crearClienteSesionCanal(nombreCliente,nombreSesion,true,nombreCanal,"localhost",puerto);
}
else if (opcion==4){

listarSesiones();

}
else if (opcion==5){
System.exit(0);
}
}



}

public static void lanzarRegistro(){
String type="socket";
try{
if(!RegistryFactory.registryExists(type)){
RegistryFactory.startRegistry(type);
System.out.println("Registro iniciado correctamente");
}
}
catch(NoRegistryException nre){
System.out.println("No se puede iniciar un Registry de este tipo.");
}
catch(RegistryExistsException ree){
System.out.println("El registro ya est iniciado");
}
}

public static void detenerRegistro(){
String type="socket";
try {
if(RegistryFactory.registryExists(type)){
RegistryFactory.stopRegistry(type);
System.out.println("Registro detenido correctamente");
}
} catch (NoRegistryException e) {
System.out.println("No se puede detener el Registry.");
}
}

public static Session crearSesion(String nombreCliente,String nombreSesion,boolean conAutoJoin, String direccion, int puerto){
boolean created=false;
Client client=new Cliente(nombreCliente);
Session session=null;
URLString url=URLString.createSessionURL(direccion,puerto,"socket",nombreSesion); //puerto: un nmero grande para que no coincida con otro de la mquina en uso
try{
while(created==false){
if(!SessionFactory.sessionExists(url)){
session=SessionFactory.createSession(client,url,conAutoJoin);
created=true;
if(conAutoJoin)
System.out.println("El cliente: "+nombreCliente+" ha creado y se ha unido a la sesin: "+session.getName());
else
System.out.println("El cliente: "+nombreCliente+" ha creado pero no se ha unido a la sesin: "+session.getName());
}
else{
try{
Thread.sleep(5000);
}
catch(InterruptedException e){
System.out.println(e);
}
}
}
}
catch (JSDTException e){
System.out.println("No se puede crear la sesin. "+e);
}
return session;
}

public static void unirClienteSesion(String nombreCliente,Session s){
Client client = new Cliente(nombreCliente);
Session session=s;

try{
session.join(client);
System.out.println("El cliente: "+nombreCliente+" se ha unido a la sesin: "+s.getName());
}
catch(JSDTException e){
System.out.println("No se puede unir el usuario a la sesin. "+e);
}
}





public static void crearClienteSesionCanal(String nombreCliente,String nombreSesion,boolean conAutoJoin,String nombreCanal,String direccion,int puerto){
Session s=crearSesion(nombreCliente,nombreSesion,conAutoJoin,direccion,puerto);
Cliente c=new Cliente(nombreCliente);
//unirClienteSesion(nombreCliente,s);
Channel canal=crearCanal(s,c,nombreCanal,conAutoJoin);
//unirClienteCanal(nombreCliente,canal);
crearConsumidor(new Cliente(nombreCliente),canal);
}






public static Channel crearCanal(Session s, Client c,String nombreCanal,boolean conAutoJoin){
Channel channel=null;
try{
channel=s.createChannel(c,nombreCanal,true,true,conAutoJoin);
if(conAutoJoin)
System.out.println("El cliente: "+c.getName()+" ha creado y se ha unido al canal: "+channel.getName());
else
System.out.println("El cliente: "+c.getName()+" ha creado pero no se ha unido al canal: "+channel.getName());
// channel.join(c);
}
catch(JSDTException e){
System.out.println("No se puede crear y unir al cliente: "+c.getName()+ " al canal: "+nombreCanal+". "+e);
}
return channel;
}

public static void unirClienteCanal(String nombreCliente,Channel c){
Client client = new Cliente(nombreCliente);
Channel channel=c;

try{
channel.join(client);
System.out.println("El cliente: "+nombreCliente+" se ha unido al canal: "+channel.getName());
}
catch(JSDTException e){
System.out.println("No se puede unir al usuario: "+nombreCliente+" al canal: "+channel.getName()+". "+e);
}
}

public static void abandonarCanal(String nombreCliente,Channel c){
Client client = new Cliente(nombreCliente);
Channel channel=c;

try{
channel.leave(client);
System.out.println("El cliente: "+nombreCliente+" ha abandonado el canal: "+channel.getName());
}
catch(JSDTException e){
System.out.println("El cliente: "+nombreCliente+" no puede abandonar el canal: "+channel.getName()+". "+e);
}
}

public static void abandonarSesion(String nombreCliente,Session s){
Client client = new Cliente(nombreCliente);
Session session=s;

try{
session.leave(client);
System.out.println("El cliente: "+nombreCliente+" ha abandonado la sesin: "+session.getName());
}
catch(JSDTException e){
System.out.println("El cliente: "+nombreCliente+" no puede abandonar la sesin: "+session.getName()+". "+e);
}
}

public static void cerrarSesion(Session s){
try{
s.close(true);
System.out.println("Se ha cerrado la sesin: "+s.getName());
}
catch(JSDTException e){
System.out.println("No se puede cerrar la sesin: "+s.getName()+". ");
}
}

public static void crearConsumidor(Cliente client,Channel channel){
ChannelConsumer consumer;

try{
consumer= new Consumidor();
channel.addConsumer(client,consumer);
System.out.println("El cliente: "+client.getName()+" ha sido registrado como consumidor en el canal: "+channel.getName());
}
catch(JSDTException e){
System.out.println("El cliente: "+client.getName()+" no ha podido ser registrado como consumidor en el canal: "+channel.getName());
}
}

public static void listarSesiones(){

URLString [] lista = null;
try {

//lista = list("localhost","socket");
lista = RegistryFactory.list();


if (lista.length == 0)
System.out.println("No hay ninguna sesion abierta");
else
{
for (int i=0; i < lista.length; i++)
System.out.println("Sesion: " + lista[i]);
}

} catch(Exception e){
System.out.println("Error." + e.toString());
}

}


}
