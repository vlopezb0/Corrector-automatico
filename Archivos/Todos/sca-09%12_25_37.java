import com.sun.media.jsdt.ChannelConsumer;
import com.sun.media.jsdt.Data;
/*
* Created on 21-feb-2007
*
* TODO To change the template for this generated file go to
* Window - Preferences - Java - Code Style - Code Templates
*/

/**
* @author Jos Manuel Quintana
*
* TODO To change the template for this generated type comment go to
* Window - Preferences - Java - Code Style - Code Templates
*/
public class Consumidor implements ChannelConsumer {

public synchronized void dataReceived(Data arg0) {

}

}

/*
* Created on 19-feb-2007
*
* TODO To change the template for this generated file go to
* Window - Preferences - Java - Code Style - Code Templates
*/
import com.sun.media.jsdt.*;
/**
* @author Jos Manuel Quintana
*
* TODO To change the template for this generated type comment go to
* Window - Preferences - Java - Code Style - Code Templates
*/
public class Cliente implements Client {


private String name;

public Cliente(String name) {
this.name=name;
}


public Object authenticate(AuthenticationInfo arg0) {
return null;
}


public String getName() {
return(name);
}

}
import com.sun.media.jsdt.Channel;
import com.sun.media.jsdt.ChannelConsumer;
import com.sun.media.jsdt.Client;
import com.sun.media.jsdt.JSDTException;
import com.sun.media.jsdt.NoRegistryException;
import com.sun.media.jsdt.RegistryExistsException;
import com.sun.media.jsdt.RegistryFactory;
import com.sun.media.jsdt.Session;
import com.sun.media.jsdt.SessionFactory;
import com.sun.media.jsdt.URLString;


public class Experimento {

public static void main(String []args){
lanzarRegistro();

//////////////////SESION1//////////////////
//Crear sesin con AutoJoin
Session s1=crearSesion("C1","sesion1",false,4763);
unirClienteSesion("C1",s1);
unirClienteSesion("C2",s1);
unirClienteSesion("C3",s1);

//Crear canal con AutoJoin
Channel c1=crearCanal(s1,new Cliente("C1"),"canal1",false);
Channel c2=crearCanal(s1,new Cliente("C1"),"canal2",false);
Channel c3=crearCanal(s1,new Cliente("C2"),"canal3",false);

//Aadir cliente a la sesion y al canal
unirClienteCanal("C1",c1);
unirClienteCanal("C2",c1);
unirClienteCanal("C1",c2);
unirClienteCanal("C3",c2);
unirClienteCanal("C2",c3);
unirClienteCanal("C3",c3);

//Aadir consumidor
crearConsumidor(new Cliente("C1"),c1);
crearConsumidor(new Cliente("C2"),c1);
crearConsumidor(new Cliente("C1"),c2);
crearConsumidor(new Cliente("C3"),c2);
crearConsumidor(new Cliente("C2"),c3);
crearConsumidor(new Cliente("C3"),c3);



//////////////////SESION2//////////////////
//Crear sesin sin AutoJoin
Session s2=crearSesion("C1","sesion2",false,4764);
unirClienteSesion("C1",s2);
unirClienteSesion("C2",s2);
unirClienteSesion("C3",s2);

//Crear canal con AutoJoin
Channel c4=crearCanal(s1,new Cliente("C1"),"canal1",false);
Channel c4=crearCanal(s1,new Cliente("C1"),"canal2",false);

//Channel c3=crearCanal(s1,new Cliente("C2"),"canal3",false);

//Aadir cliente a la sesion y al canal
unirClienteCanal("C1",c1);
unirClienteCanal("C2",c1);
unirClienteCanal("C1",c2);
unirClienteCanal("C3",c2);
unirClienteCanal("C2",c3);
unirClienteCanal("C3",c3);

//Aadir consumidor
crearConsumidor(new Cliente("C1"),c1);
crearConsumidor(new Cliente("C2"),c1);
crearConsumidor(new Cliente("C1"),c2);
crearConsumidor(new Cliente("C3"),c2);
crearConsumidor(new Cliente("C2"),c3);
crearConsumidor(new Cliente("C3"),c3);



//Abandonar canal
//abandonarCanal("Jose",c1);

//Abandonar canal
//abandonarSesion("Jose",s1);

//Cerrar sesin. Al cerrar una sesin se liberan todos los canales asociados
//cerrarSesion(s1);
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

public static Session crearSesion(String nombreCliente,String nombreSesion,boolean conAutoJoin, int puerto){
boolean created=false;
Client client=new Cliente(nombreCliente);
Session session=null;
URLString url=URLString.createSessionURL("localhost",puerto,"socket",nombreSesion); //puerto: un nmero grande para que no coincida con otro de la mquina en uso
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

}

