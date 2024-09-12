import com.sun.media.jsdt.*;
import java.io.*;

public class Servidor
{
static String type="socket";
static Session ses;
static Channel ch1,ch2;
static Cliente c11,c12,c13;

public static void main(String args[])
{
try {
RegistryFactory.startRegistry(type);
System.out.println("Registro iniciado");
} catch(NoRegistryException nre) {
System.out.println("No puede iniciarse el registro");
} catch(RegistryExistsException ree) {
System.out.println("El registro ya esta iniciado");
}
crearClientes();
crearSesiones();
crearCanales();
comunicarDatos();
desconectar();
}

static void crearSesiones()
{
try {
// sesin 1
URLString url1 = URLString.createSessionURL("localhost", 12000, type, "ses");
c11=new Cliente("clSes1");
ses = SessionFactory.createSession(c11, url1, false);

} catch(Exception e) {
e.printStackTrace();
}
}

static void crearCanales()
{
try {
ses.join(c11); // los clientes deben unirse primero a la sesin
ses.join(c12);
ses.join(c13);

ch1 = ses.createChannel(c11, "canal1", true, true, true); // con auto-join
ch1.addConsumer(c12,c12);
ch1.join(c12);

ch2 = ses.createChannel(c12, "canal2", true, true, true); // con auto-join
ch2.addConsumer(c13,c13);
ch2.join(c13);

} catch(Exception e) {
e.printStackTrace();
}
}

static void crearClientes()
{
c11=new Cliente("c1");
c12=new ClienteOperador("c2");
c13=new ClientePrinter("c3");
}

static void comunicarDatos()
{
try {
// envo de datos por los cinco canales
Data data = new Data(new Suma(3,5));
ch1.sendToOthers(c11, data);
((ClienteOperador)c12).sendToClient(ch2);
}catch (Exception e){
e.printStackTrace();
}
}

static void desconectar()
{
try {
// clientes salen de canales
ch1.leave(c11);
ch1.leave(c12);
ch2.leave(c12);
ch2.leave(c13);

// clientes salen de sesiones
ses.leave(c11);
ses.leave(c12);
ses.leave(c13);

// cerrar canales, sesiones y registro
ses.close(true);
System.out.println("Sesion cerrada");
RegistryFactory.stopRegistry(type);
System.exit(0);
} catch(Exception e) {
System.out.println("No se pueden liberar los recursos");
e.printStackTrace();
}
}
}

class Cliente implements Client
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
}

class ClienteOperador implements Client,ChannelConsumer
{
protected String name;

public ClienteOperador(String name)
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

public Operacion op;

public void dataReceived(Data data)
{
op=(Operacion)data.getDataAsObject();
op.calc();
}

public void sendToClient (Channel ch){
System.out.println("Enviando resultado...");
ch.sendToOthers(this,new Data(op));
}
}

class ClientePrinter implements Client,ChannelConsumer
{
protected String name;

public ClientePrinter(String name)
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
 data.getChannel().getName()+"' la operacion '"+data.toString()+"'");
}
}

abstract class Operacion implements Serializable{
abstract public void calc();
abstract public String toString();
}

class Suma extends Operacion implements Serializable{
int a,b,resultado;
public Suma(int a, int b){
this.a=a;
this.b=b;
}

public void calc(){
resultado=a+b;
}

public String toString(){
return this.a+"+"+this.b+"="+this.resultado;
}
}
