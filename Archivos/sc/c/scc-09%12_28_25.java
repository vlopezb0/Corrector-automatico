import com.sun.media.jsdt.*;
import java.io.Serializable;
import java.io.StreamCorruptedException;



public class Experimento3 {

public static void main(String []args){
lanzarRegistro();

//////////////////SESION1//////////////////
Session ses=crearSesion("cl1","ses",false,12000);
unirClienteSesion("cl1",ses);
unirClienteSesion("cl2",ses);
unirClienteSesion("cl3",ses);

Channel ch1=crearCanal(ses,new Cliente("cl1"),"ch1",false);
Channel ch2=crearCanal(ses,new Cliente("cl2"),"ch2",false);



crearConsumidor(new Cliente("cl1"),ch1);
crearConsumidor(new Cliente("cl2"),ch1);
crearConsumidor(new Cliente("cl2"),ch2);
crearConsumidor(new Cliente("cl3"),ch2);

unirClienteCanal("cl1",ch1);
unirClienteCanal("cl2",ch1);
unirClienteCanal("cl2",ch2);
unirClienteCanal("cl3",ch2);

//////////////////Operaciones//////////////////
Data d1=new Data(new MensajeT1(4,5));
Data d2=new Data(new MensajeT2(4));
Data d3=new Data(new MensajeT3(4,5,6));
enviarDatosALosDemas(new Cliente("cl1"),ch1,new MensajeT1(4,5));

//////////////////Detener registro, Cerrar sesiones, Clientes abandonan sesiones y canales//////////////////
abandonarCanal("cl1",ch1);
abandonarCanal("cl2",ch1);
abandonarCanal("cl2",ch2);
abandonarCanal("cl3",ch2);

abandonarSesion("cl1",ses);
abandonarSesion("cl2",ses);
abandonarSesion("cl3",ses);

// Cerrar sesin. Al cerrar una sesin se liberan todos los canales asociados
cerrarSesion(ses);

detenerRegistro();

System.exit(0);
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

public void enviarDatosATodos(Client client, Channel channel,Mensaje mensaje){
//turn the java object into a Data object
Data data=new Data(mensaje);
data.setPriority(Channel.HIGH_PRIORITY);
try{
//send serialize object to all channel consumers
channel.sendToAll(client, data);
System.out.println("El cliente: "+client.getName()+" ha enviado el mensaje: a todos los consumidores del "+channel.getName());
}catch (JSDTException e){
System.out.println("No se puede enviar el objeto por el canal");
}
}

public static void enviarDatosALosDemas(Client client, Channel channel,Mensaje mensaje){
//turn the java object into a Data object
Data data=new Data(mensaje);
data.setPriority(Channel.HIGH_PRIORITY);
try{
//send serialize object to all channel consumers
channel.sendToOthers(client, data);
System.out.println("El cliente: "+client.getName()+" ha enviado el mensaje: a todos los consumidores menos a el mismo del "+channel.getName());
}catch (JSDTException e){
System.out.println("No se puede enviar el objeto por el canal");
}
}





}















class Cliente implements Client {


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



class Consumidor implements ChannelConsumer {

public synchronized void dataReceived(Data arg0) {
System.out.println("PASA POR AQUI POR FAVOR");
Mensaje o = null;
try {
o = (Mensaje)arg0.getDataAsObject();
} catch (StreamCorruptedException e) {
e.printStackTrace();
} catch (ClassNotFoundException e) {
e.printStackTrace();
}
if(o instanceof MensajeT1){
System.out.println("He recibido un mensaje T1");
}
else if(o instanceof MensajeT2){
}
else if(o instanceof MensajeT3){
}
}

}






///SUMA
class MensajeT1 extends Mensaje implements Serializable {

private int numero1;
private int numero2;

public MensajeT1(int i,int j){
numero1=i;
numero2=j;
}

}



///INVERSO
class MensajeT2 extends Mensaje implements Serializable {

private double numero1;

public MensajeT2(double n){
numero1=n;
}
}



///MAXIMO
class MensajeT3 extends Mensaje implements Serializable{

private int numero1;
private int numero2;
private int numero3;

public MensajeT3(int i, int j, int k){
numero1=i;
numero2=j;
numero3=k;
}
}

class Operaciones {
public static int suma (int i, int j)
{
return i + j;
}

public static double getInverso (int i)
{
return 1/i;
}


public static int getMaximo (int i, int j, int k)
{
int max;

max = i;

if (max < j)
max = j;
if (max < k)
max = k;

return max;
}
}

class Mensaje implements Serializable {
}
