//implementacion de la interfaz cliente

import com.sun.media.jsdt.*;

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

import com.sun.media.jsdt.*;

public class Servidor{

public static void main (String [] args){

//primero lanzo el registro
lanzarRegistro();

//creamos sesion 1
Session s1 = crearSesion ("C1", "Sesion_1", true);

//unimos el resto de clientes a las sesiones y canales
ExampleClient.unirASesion ("C2", s1);
ExampleClient.unirASesion ("C3", s1);

//creamos los canales 1, 2 y 3
Channel canal1 = crearCanal("C1",s1,"Canal_1", true); 
Channel canal2 = crearCanal("C1",s1,"Canal_2", true);
Channel canal3 = crearCanal("C2",s1,"Canal_3", true);


ExampleClient.unirACanal ("C2", canal1);
ExampleClient.unirACanal ("C3", canal2);
ExampleClient.unirACanal ("C3", canal3);

//creamos sesion 2
Session s2 = crearSesion ("C1", "Sesion_2", true);

//unimos los clientes a las sesion 2

ExampleClient.unirASesion ("C2", s2);
ExampleClient.unirASesion ("C4", s2);
ExampleClient.unirASesion ("C5", s2);

//creamos los canales

Channel canal4 = crearCanal("C1",s2,"Canal_4", true);
Channel canal5 = crearCanal("C4",s2,"Canal_5", true);

//unimos al canal al resto de clientes

ExampleClient.unirACanal ("C2", canal4);
ExampleClient.unirACanal ("C4", canal4);
ExampleClient.unirACanal ("C5", canal4);
ExampleClient.unirACanal ("C5", canal5);

//cerramos las sesiones
cerrarSesion (s1);
cerrarSesion (s2);
//cerramos el registro
//RegistryFactory.stopRegistry("socket");

}

public static void lanzarRegistro(){

String type = "socket";
try{
if (!RegistryFactory.registryExists (type)){
RegistryFactory.startRegistry (type);
System.out.println ("Registro iniciado correctamente");
}
}//end try

catch (NoRegistryException nre){
System.out.println ("No se puede lanzar un registro de este tipo");
}

catch (RegistryExistsException ree){
System.out.println ("El registro ya ha sido lanzado");
}
}//end lanzarRegistro
public static Session crearSesion (String nombreCliente, String nombreSesion, boolean conAutoJoin){
boolean created = false;
Client client = new ExampleClient (nombreCliente);
Session session = null;
URLString url = URLString.createSessionURL ("localhost", 4461, "socket", nombreSesion);
try{
while (created == false){
if (!SessionFactory.sessionExists (url)){
session = SessionFactory.createSession (client, url, conAutoJoin);
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

return session;

}

public static void cerrarSesion (Session s){
try{

s.close(true);
System.out.println ("La sesion " +s.getName()+ " ha sido cerrada");
}
catch (JSDTException e){
System.out.println ("No se puede cerrar la sesion");
}

}//end cerrarSesion

public static Channel crearCanal (String nombreCliente, Session s, String nombreCanal, boolean conAutoJoin){

Client client = new ExampleClient (nombreCliente);
Channel channel = null;

try{
channel = s.createChannel (client, nombreCanal, true, true, conAutoJoin);
System.out.println ("El canal "+channel.getName()+ " ha sido creado");

}
catch (JSDTException e){
System.out.println ("No se ha podido crear el canal");
}

return channel;
}

}//end Servidor


