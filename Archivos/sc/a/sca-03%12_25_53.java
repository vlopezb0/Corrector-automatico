import com.sun.media.jsdt.*;
import java.io.*;

class ejemplo{

static String type="socket";
static boolean created=false;
//creamos los 5 clientes
static Client cliente1 = new Cliente("c1");
static Client cliente2 = new Cliente("c2");
static Client cliente3 = new Cliente("c3");
static Client cliente4 = new Cliente("c4");
static Client cliente5 = new Cliente("c5");
//las 2 sesiones
static Session sesion1 = null;
static Session sesion2 = null;
static URLString url1 = URLString.createSessionURL("localhost",5000,"socket","Sesion1");
static URLString url2 = URLString.createSessionURL("localhost",5000,"socket","Sesion2");
//los canales
static Channel canal1 = null;
static Channel canal2 = null;
static Channel canal3 = null;
static Channel canal4 = null;
static Channel canal5 = null;
//los consumidores
static ChannelConsumer consumidor1;
static ChannelConsumer consumidor2;
static ChannelConsumer consumidor3;
static ChannelConsumer consumidor4;
static ChannelConsumer consumidor5;


 public static void main(String [] args){

//------ Codigo para crear el registro ----

try{
if (RegistryFactory.registryExists(type)==false){
RegistryFactory.startRegistry(type);
}
}catch(NoRegistryException nre){
System.out.println("No se pudo comenzar un registro de este tipo");
}catch(RegistryExistsException nrd){
System.out.println("El registro ya existe");
}

//------ Creacion de la sesion --------
try{
while(created ==false){
if(SessionFactory.sessionExists(url1) && SessionFactory.sessionExists(url2)){
session1=SessionFactory.createSession(cliente1,url1,true);
session2=SessionFactory.createSession(cliente1,url2,true);
created=true;
}else{
try{
Thread.sleep(5000);
}catch(InterruptedException e){
}
}
}
}catch (JSDTException e){
System.out.println("No se pudo crear la sesion");
 }
//------ Union de los clientes a la sesion ------
try{
sesion1.join(cliente1);
sesion1.join(cliente2);
sesion1.join(cliente3);

sesion2.join(cliente1);
sesion2.join(cliente2);
sesion2.join(cliente4);
sesion2.join(cliente5);

}catch(JSDTException e){
System.out.println("No se pudo unir el cliente a la sesion");
}
//------ Creacion del un canal y union a l -----------

try{
canal1=session1.createChannel(cliente1,"Canal1",true,true,false);
canal1.join(cliente1);
canal1.join(cliente2);

canal2=session1.createChannel(cliente1,"Canal2",true,true,false);
canal2.join(cliente1);
canal2.join(cliente3);

canal3=session1.createChannel(cliente3,"Canal3",true,true,false);
canal3.join(cliente3);
canal3.join(cliente2);

canal4=session2.createChannel(cliente1,"Canal4",true,true,false);
canal4.join(cliente1);
canal4.join(cliente2);
canal4.join(cliente4);
canal4.join(cliente5);

canal5=session2.createChannel(cliente5,"Canal5",true,true,false);
canal5.join(cliente5);
canal5.join(cliente4);

}catch(JSDTException e){
System.out.println("No se pudo crear el canal");
}

//------ Creacion de un consumidor -------

try{
consumidor1 = new ClienteConsumidor();
consumidor2 = new ClienteConsumidor();
consumidor3 = new ClienteConsumidor();
consumidor4 = new ClienteConsumidor();
consumidor5 = new ClienteConsumidor();

canal1.addConsumer(cliente1,consumidor1);
canal1.addConsumer(cliente1,consumidor2);

canal2.addConsumer(cliente1,consumidor1);
canal2.addConsumer(cliente1,consumidor3);

canal3.addConsumer(cliente3,consumidor3);
canal3.addConsumer(cliente3,consumidor2);

canal4.addConsumer(cliente1,consumidor1);
canal4.addConsumer(cliente1,consumidor2);
canal4.addConsumer(cliente1,consumidor4);
canal4.addConsumer(cliente1,consumidor5);

canal5.addConsumer(cliente5,consumidor5);
canal5.addConsumer(cliente5,consumidor4);

}catch(Exception e){
System.out.println("No se pudo aadir un consumidor al canal");
}
}


//---- Metodo para poder enviar datos a traves del canal----
public static void enviarDatos(){
 Data datos =new Data ();
 try{
 canal.sendToOther(client,datos);
 }catch(JSDTException e){
 System.out.println("No se pudo enviar el mensaje por el canal");
 }
}
}

class Cliente implements Client{

private String name;

public Cliente(String name){
this.name=name;
}

public Object authenticate(AuthenticationInfo info){
return null;
}

public String getName(){
return name;
}
}



class ClienteConsumidor implements ChannelConsumer{
public synchronized void dataReceived(Data data){

int priority = data.getPriority();
String senderName = data.getSenderName();
Channel channel = data.getChannel();
String theData = data.getDataAsString();

System.out.println(theData);
}
}



