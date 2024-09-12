import java.io.*;
import com.sun.media.jsdt.*;

public class principal {

static String type="socket";
public static void main (String args []) throws IOException {
BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));
int opcion = 0;
String linea = "";
String ip;
int puerto;
do {
System.out.println ("Introduzca la opcin que desee");
System.out.println ("\t1 - Iniciar o parar el registro JSTDT");
System.out.println ("\t2 - Crear cliente, sesion y canal");
System.out.println ("\t3 - Listar sessiones activas");
System.out.println ("\t4 - Salir");
linea = leer.readLine();
opcion = Integer.parseInt (linea);

if (opcion == 1) {
System.out.println("\t\t1 - Iniciar el Registro.");
System.out.println("\t\t2 - Parar el Registro.");
System.out.println("\t\t3 - Salir");
linea = leer.readLine();
opcion = Integer.parseInt (linea);
if(opcion == 1){
iniciarRegistro();
}
else if(opcion == 2){
pararRegistro();
}
}
else if (opcion == 2) {
try {
System.out.println("Introduce nombre de la sesion: ");
linea=leer.readLine();
System.out.println("Introduce la direccin IP:");
ip=leer.readLine();
System.out.println("Introduce el puerto:");
puerto=Integer.parseInt(leer.readLine());
URLString url1 = URLString.createSessionURL(ip, puerto, type, linea);
System.out.println("Introduce el nombre del cliente:");
linea=leer.readLine();
 cliente c1=new cliente(linea);
 Session sesion1 = SessionFactory.createSession(c1, url1, true);
Channel canal1 = sesion1.createChannel(c1, "canal1", true, true, true); // con auto-join
canal1.addConsumer(c1,c1);
} catch (Exception e) {
System.out.println ("Problemas encontrados");
}
}
else if (opcion == 3) {
listarSesionesActivas();
}
} while (opcion != 4);
}

public static void iniciarRegistro(){
try {
RegistryFactory.startRegistry(type);
System.out.println("Registro iniciado");
} catch(NoRegistryException nre) {
System.out.println("No puede iniciarse el registro");
} catch(RegistryExistsException ree) {
System.out.println("El registro ya esta iniciado");
}
}

public static void pararRegistro(){
try {
RegistryFactory.stopRegistry(type);
System.out.println("Registro parado");
} catch(NoRegistryException nre) {
System.out.println("No puede pararse el registro");
} catch (Exception e) {
System.out.println ("No se pueden liberar los recursos");
}
}


public static void listarSesionesActivas(){
try{
URLString listaURL [] = RegistryFactory.list();
for(int i=0; i<listaURL.length; i++)
System.out.println(listaURL[i].toString());
}
catch(Exception e){
System.out.println ("Error al listar las sesiones");
}
}

}

public class cliente implements Client,ChannelConsumer
{
protected String name;

public cliente(String name) {
this.name=name;
}

public Object authenticate(AuthenticationInfo info){
return null;
}

public String getName(){
return name;
}

public void dataReceived(Data data){
System.out.println("El cliente '"+getName()+"' recibe por el canal '"+
data.getChannel().getName()+"' el dato '"+data.getDataAsString()+"'");
}
}