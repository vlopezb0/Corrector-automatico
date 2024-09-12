import com.sun.media.jsdt.*;

public class Aplicacion {

public static void main(String [] args){
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

String hostName="161.67.212.180";
int port=10000;//4461;
String connectionType="socket";
String sessionName="Sesion 1";

//------------------------------------------------------------------------

// Crear 5 clientes: C1, C2, C3, C4 y C5
Cliente C1=new Cliente("C1");
Cliente C2=new Cliente("C2");
Cliente C3=new Cliente("C3");
Cliente C4=new Cliente("C4");
Cliente C5=new Cliente("C5");

// Crear 5 consumidores: Co1, Co2, Co3, Co4 y Co5
Consumidor Co1=new Consumidor("Co1");
Consumidor Co2=new Consumidor("Co2");
Consumidor Co3=new Consumidor("Co3");
Consumidor Co4=new Consumidor("Co4");
Consumidor Co5=new Consumidor("Co5");

// Crear la sesion 1

Session sesion1=crearSesion(C1,URLString.createSessionURL(hostName,port,connectionType,sessionName));
// Crear para la sesion 1 los tres canales S1C1 S1C2 S1C3
Channel S1C1=crearCanal(sesion1,C1);
Channel S1C2=crearCanal(sesion1,C1);
Channel S1C3=crearCanal(sesion1,C2);
// Unir Cliente C1 y C2 a S1C1
unirClienteSesion(C2,sesion1);
unirClienteCanal(S1C1,C2);
// Unir Cliente C1 y C3 a S1C2
unirClienteSesion(C3,sesion1);
unirClienteCanal(S1C2,C3);
// Unir Cliente C2 y C3 a S1C3
unirClienteCanal(S1C3,C3);

// Crear la sesion 2
//Session sesion2=crearSesion(C4,URLString.createSessionURL(hostName,port,connectionType,sessionName));

cerrarSesion(sesion1);
} // Fin_main

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
} // Fin_crearSesion

private static void cerrarSesion(Session sesion){
try {
sesion.close(true);
System.out.println(">> Sesin Terminada");
} catch (ConnectionException e) {
// TODO Auto-generated catch block
e.printStackTrace();
} catch (NoSuchSessionException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
} // Fin_cerrarSesion

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
} // Fin_UnirClienteSesion

private static Channel crearCanal(Session sesion,Client cliente){
Channel canal = null;

try{
canal=sesion.createChannel(cliente,"Canal",true,true,true);
}
catch(JSDTException jsdte){
System.out.println("Couldn't create the channel");
}
return canal;
} // Fin_CrearCanal

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

} //Fin_unirClienteCanal

private static void unirConsumidor(Channel canal,Cliente cliente,Consumidor consumidor){
try{
canal.addConsumer(cliente,consumidor);
System.out.println("Se ha unido "+consumidor);
}catch(Exception e){
System.out.println("Couldn't not add channel consumer");
}
} //Fin_unirConsumidor

} // Fin class
