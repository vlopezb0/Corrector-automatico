import com.sun.media.jsdt.*;
import java.io.*;
public class Problema2{
static BufferedReader leer=new BufferedReader(new InputStreamReader(System.in));

public static void main(String args[])throws Exception{
System.out.println("-----menu-----");
System.out.println("1-registrarse");
System.out.println("2-opcion2 del problema");
System.out.println("3-listar sesiones activas");
System.out.println("4-salir");

 boolean seguir=true;
while(seguir){
int opc=Integer.parseInt(leer.readLine());

if(opc==1){
registrarse();
}
if(opc==2){
opcion2();
}

if (opc==3){
SessionFactory.listSessions();
}
if(opc==4){
seguir = false;
}
}
}
public static void registrarse()throws Exception{
try {
RegistryFactory.startRegistry("socket");
System.out.println("Registro iniciado");
} catch(NoRegistryException nre) {
System.out.println("No puede iniciarse el registro");
} catch(RegistryExistsException ree) {
System.out.println("El registro ya esta iniciado");
}

}

public static void opcion2()throws Exception{
System.out.println("introduzca el nombre del cliente");
String nombre=leer.readLine();
Cliente c1=new Cliente(nombre);
System.out.println("introduzca el nombre de la sesion");
String nombreSesion=leer.readLine();
URLString url1 = URLString.createSessionURL("localhost", 1000, "socket", nombreSesion);
Session sesion1 = SessionFactory.createSession(c1, url1, true);
sesion1.join(c1);
Channel canal1 = sesion1.createChannel(c1, "canal1", true, true, true);
canal1.addConsumer(c1,c1);
System.out.println("introduzca los datos a enviar");
String in=leer.readLine();
Data data = new Data(in);
canal1.sendToOthers(c1, data);




}

}
class Cliente implements Client,ChannelConsumer
{
protected String name;

public Cliente(String name)
{
this.name=name;
}

public Object authenticate(AuthenticationInfo info)
{
return null;
}

public String getName()
{
return name;
}

public void dataReceived(Data data)
{
System.out.println("El cliente '"+getName()+"' recibe por el canal '"+
data.getChannel().getName()+"' el dato '"+data.getDataAsString()+"'");
}
}
