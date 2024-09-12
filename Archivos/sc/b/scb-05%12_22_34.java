import java.io.*;

import com.sun.media.jsdt.*;

public class Servidor{

static String type="socket";
static boolean lanzado = false;

public static void main (String [] args)throws IOException{

BufferedReader leer = new BufferedReader (new InputStreamReader (System.in));
int opcion;

//mostramos el menu
do{
System.out.println ("Seleccione la opcion que desee");
System.out.println ("1. Lanzar/Parar registro");
System.out.println ("2. Creacin de un cliente, una sesin y un canal, suministrando sus nombres y la direccin y puerto de la sesion");
System.out.println ("3. Listar sesiones activas");
System.out.println ("4. Salir");

opcion = Integer.parseInt (leer.readLine());
switch (opcion){
case 1:
if (!lanzado){
// lanzamos el registro
lanzarRegistro();
}
else{
pararRegistro ();
}
break;

default:
System.out.println ("Opcion no implementada");
}
}while (opcion != 4);










}

public static void lanzarRegistro(){

try{
if (!RegistryFactory.registryExists (type)){
RegistryFactory.startRegistry (type);
System.out.println ("Registro iniciado correctamente");
lanzado = true;
}
}//end try

catch (NoRegistryException nre){
System.out.println ("No se puede lanzar un registro de este tipo");
}

catch (RegistryExistsException ree){
System.out.println ("El registro ya ha sido lanzado");
}
}//end lanzarRegistro

public static void pararRegistro (){
try{
RegistryFactory.stopRegistry(type);
lanzado = false;
}
catch (Exception e){
System.out.println ("Error al parar el registro");
}
}

//metodo que crea la sesion, con un cliente, un canal asociandolo a una direccion y un puerto

public static Session crearTodo (String nombreCliente, String nombreSesion, String ip, int puerto, String nombre_canal){
boolean created = false;
Client client = new ExampleClient (nombreCliente);
Session session = null;
Channel channel = null;
URLString url = URLString.createSessionURL (ip, puerto, type, nombreSesion);
//creamos la sesion
try{
while (created == false){
if (!SessionFactory.sessionExists (url)){
session = SessionFactory.createSession (client, url, true);
created = true;
System.out.println ("La sesion "+session.getName()+ " ha sido creada");
}
else{
try{
Thread.sleep (5000);
}
catch (InterruptedException e){
System.out.println (e);
}
}//end else
}
}//end try

catch (JSDTException e){
System.out.println ("No se ha podido crear la sesion "+e);
}

//creamos el canal


try{
channel = session.createChannel (client, nombre_canal, true, true, true);
System.out.println ("El canal "+channel.getName()+ " ha sido creado");

}
catch (JSDTException e){
System.out.println ("No se ha podido crear el canal");
}

return session;

}

public static void listarSesionesActivas() {
try{
URLString [] listURL = RegistryFactory.list();
// mostramos las URL?
for (int i= 0; i<listURL.length; i++){
System.out.println(listURL[i].toString());
}
}//end try
catch (Exception e){
System.out.println ("Error al parar el registro");
}

}
}//end Servidor

//implementacion de la interfaz cliente


public class ExampleClient implements Client {


private String name;

public ExampleClient(String name) {
this.name=name;
}


public Object authenticate(AuthenticationInfo arg0) {
return null;
}


public String getName() {
return(name);
}

//metodo para unir un cliente a una sesion

public static void unirASesion(String nombreCliente,Session s){
Client client = new ExampleClient(nombreCliente);
Session session=s;

try{
session.join(client);
System.out.println("El cliente "+nombreCliente+" se ha conectado a la sesion "+s.getName());
}
catch(JSDTException e){
System.out.println("No se puede unir el usuario a la sesion "+e);
}

}//end UnirASesion

//metodo para unir a un cliente a un canal
public static void unirACanal (String nombreCliente, Channel c){

Client client = new ExampleClient(nombreCliente);
try{
c.join (client);
System.out.println("El cliente "+nombreCliente+" se ha unido al canal "+c.getName());
}
catch(JSDTException e){
System.out.println("No se puede unir el usuario al canal "+e);
}

}//end UnirASesion

}//end ExampleClient
