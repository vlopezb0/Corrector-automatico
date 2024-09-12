import com.sun.media.jsdt.*;
import java.io.*;

public class servidor
{
static String type="socket";

public static void main(String args[]) throws Exception
{

BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

//iniciarRegistro();



//crear("ejemplo","ejemplo","ejemplo");



while(true){
System.out.println("Introduzca instruccion");
String palabra=br.readLine();

if(palabra.equals("arrancar"))
iniciarRegistro();

if(palabra.equals("parar"))
pararRegistro();

if(palabra.equals("insertar")){

String sesion=br.readLine();
String canal=br.readLine();
String cliente=br.readLine();

crear(sesion,canal,cliente);

}
if(palabra.equals("listar")){


}

}//Fin del while


}


static void iniciarRegistro ()
{
 try {
 RegistryFactory.startRegistry(type);
 System.out.println("Registro iniciado");
 } catch(NoRegistryException nre) {
 System.out.println("No puede iniciarse el registro "+nre);
 } catch(RegistryExistsException ree) {
 System.out.println("El registro ya esta iniciado"+ree);
}
}


static void pararRegistro ()
{
try {
 RegistryFactory.stopRegistry(type);
 System.out.println("Registro parado");
 } catch(Exception e) {
 System.out.println("El registro ya esta parado");
}
}



static void crear(String nombreSesion,String nombreCanal, String nombreCliente)
{
try {
System.out.println("Insertando cliente");
URLString url1 = URLString.createSessionURL("localhost", 1000, type, nombreSesion);
Cliente c1=new Cliente(nombreCliente);
Session sesion1 = SessionFactory.createSession(c1, url1, false);
Channel canal1 = sesion1.createChannel(c1, nombreCanal, true, true, true); // con auto-join
canal1.join(c1);
canal1.addConsumer(c1,c1);

} catch(Exception e) {
e.printStackTrace();
}
}

//public static URLString[] listar() throws ConnectionException

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

