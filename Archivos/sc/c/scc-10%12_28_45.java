import com.sun.media.jsdt.*;
import com.sun.media.jsdt.ChannelConsumer;
import com.sun.media.jsdt.Data;
import com.sun.media.jsdt.AuthenticationInfo;
import com.sun.media.jsdt.Client;

public class aplicacion3{

private static void registrar(){

String type="socket";

try{
if(RegistryFactory.registryExists(type)==false){
RegistryFactory.startRegistry(type);
System.out.println(">> REGISTRO INICIADO.");
}
else{
System.out.println(">> El Registro ya estaba iniciado");
}
}
catch(NoRegistryException nre){
System.out.println("Couldn't start a Registry of this type.");
}
catch(RegistryExistsException ree){
System.out.println("The Registry is already runnig.");

}
}

private static Session crearSesion(Cliente cliente,URLString url){
boolean created=false;
Session sesion=null;

try{
while(!created){
if(!SessionFactory.sessionExists(url)){
sesion=SessionFactory.createSession(cliente,url,true);
created=true;
System.out.println(">> La Sesin <<"+sesion.getName()+">> ha sido creada por el cliente --> "+cliente.toString());
}
else{
try{
Thread.sleep(5000);
}catch(InterruptedException e){
System.out.println(e.getMessage());
}
}
}
}
catch(JSDTException e){
System.out.println("Couldn't create the Session.");
}
return sesion;
}

private static void cerrarSesion(Session sesion){
try {
sesion.close(true);
System.out.println(">> Sesin Terminada");
} catch (ConnectionException e) {
e.printStackTrace();
} catch (NoSuchSessionException e) {
e.printStackTrace();
}
}

private static void unirClienteSesion(Client client,Session session){
try{
session.join(client);
String [] clientesUnidos=session.listClientNames();
System.out.println("Unidos a la session: "+session.getName());
for(int i=0; i<clientesUnidos.length; i++){
System.out.print("\t"+clientesUnidos[i]+"\n");
}
}catch(JSDTException jsdte){
System.out.println("Couldn't create the Session.");
}
}

private static Channel crearCanal(Session sesion,Client cliente){
Channel canal = null;

try{
canal=sesion.createChannel(cliente,"\"Chat ESDLA\"",true,true,true);
}
catch(JSDTException jsdte){
System.out.println("Couldn't create the channel");
}
return canal;
}

private static void unirClienteCanal(Channel canal,Client cliente){
try{
canal.join(cliente);
String [] clientesConectados=canal.listClientNames();
System.out.println("Conectados al canal "+canal.toString()+": ");
for(int i=0; i<clientesConectados.length; i++){
System.out.print("\t"+clientesConectados[i]+"\n");
}
}
catch(JSDTException jsdte){
System.out.println("Couldn't join the Client to the channel");
}

}

private static void unirConsumidor(Channel canal,Cliente cliente,Consumidor consumidor){
try{
canal.addConsumer(cliente,consumidor);
System.out.println("Se ha unido "+consumidor);
}catch(Exception e){
System.out.println("Couldn't not add channel consumer");
}
}

public static void main(String [] args){

// (a)
registrar();
// (b)
Cliente cl1=new Cliente("cl1");
// Crear Sesion (ses)
Session ses=crearSesion(cl1,URLString.createSessionURL("127.0.0.1",10000,"socket","ses"));
// Crear clientes (cl2 y cl3) y unirlo
Cliente cl2=new Cliente("cl2");
unirClienteSesion(cl2,ses);
 Cliente cl3=new Cliente("cl3");
unirClienteSesion(cl3,ses);
// Crear Canales (ch1 y ch2)
// cl1 -> ch1
Channel ch1=crearCanal(ses,cl1);
// cl2 -> ch2
Channel ch2=crearCanal(ses,cl2);
// unimos los otros clientes
unirClienteCanal(ch1,cl2);
unirClienteCanal(ch2,cl3);
//unirConsumidor(ch1,cl1,Consumidor consumidor);
 //comunicarDatos();
 //desconectar();
cerrarSesion(ses);
 }
}

