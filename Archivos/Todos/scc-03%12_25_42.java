import com.sun.media.jsdt.*;
import java.io.*;

public class experimento
{
static String type="socket";
static Session sesion1;
static Channel canal1,canal2;
static Cliente c1,c2,c3,clSes1,clSes2;

public static void main(String args[])throws IOException
{
try {
RegistryFactory.startRegistry(type);
System.out.println("Registro iniciado");
} catch(NoRegistryException nre) {
System.out.println("No puede iniciarse el registro");
} catch(RegistryExistsException ree) {
System.out.println("El registro ya esta iniciado");
}
BufferedReader leer=new BufferedReader(new InputStreamReader(System.in));
crearClientes();
crearSesiones();
crearCanales();
//comunicarDatos();

System.out.println("Pulsa 1 para suma\nPulsa 2 para inverso\nPulsa 3 para maximo");
int opcion=Integer.parseInt(leer.readLine());
comunicarDatos(opcion);

desconectar();
}

static void crearSesiones()
{
try {
// sesin 1
URLString url1 = URLString.createSessionURL("localhost", 1000, type, "sesion1");
clSes1=new Cliente("clSes1");
sesion1 = SessionFactory.createSession(clSes1, url1, false);

} catch(Exception e) {
e.printStackTrace();
}
}

static void crearCanales()
{
try {
// canales sesin 1
sesion1.join(c1); // los clientes deben unirse primero a la sesin
sesion1.join(c2);

canal1 = sesion1.createChannel(c1, "canal1", true, true, true); // con auto-join
canal1.addConsumer(c1,c1);
canal1.join(c2);
canal1.addConsumer(c2,c2);
canal2 = sesion1.createChannel(c1, "canal2", true, true, true);
canal2.addConsumer(c1,c1);
canal2.join(c3);
canal2.addConsumer(c3,c3);
canal2.join(c2);
canal2.addConsumer(c2,c2);


} catch(Exception e) {
e.printStackTrace();
}
}

static void crearClientes()
{
c1=new Cliente("c1");
c2=new Cliente("c2");
c3=new Cliente("c3");
}

static void comunicarDatos(int opcion)
{
try {
Mensaje m1,m2,m3;
Data data=null;

switch (opcion){
case 1: m1=new MensajeT1(3,4);
data = new Data("m1");
break;
case 2: m2= new MensajeT2(6);
data = new Data("m2");
break;
case 3: m3= new MensajeT3(2,3,4);
data = new Data("m3");
break;
}

canal1.sendToClient(c1,"c2",data);


// envo de datos por los cinco canales
// Data data = new Data("hola");
// canal1.sendToOthers(c1, data);
// canal2.sendToOthers(c1, data);

} catch(Exception e) {
e.printStackTrace();
}
}

static void desconectar()
{
try {
// clientes salen de canales
canal1.leave(c1);
canal1.leave(c2);
canal2.leave(c1);
canal2.leave(c3);


// clientes salen de sesiones
sesion1.leave(c1);
sesion1.leave(c2);
sesion1.leave(c3);

// cerrar canales, sesiones y registro
sesion1.close(true);
System.out.println("Sesion 1 cerrada");

RegistryFactory.stopRegistry(type);
System.exit(0);
} catch(Exception e) {
System.out.println("No se pueden liberar los recursos");
e.printStackTrace();
}
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
data.getChannel().getName());

Mensaje mens= (Mensaje)data.getDataAsObject();

}
}

abstract class Mensaje{

public String getOperacion(){
}
}
class MensajeT1 extends Mensaje implements java.io.Serializable{
int operando1;
int operando2;
String operacion = "suma";

public MensajeT1(int op1, int op2){
operando1 = op1;
operando2 = op2;
}
public int getOperando1(){
return operando1;
}
public int getOperando2(){
return operando2;
}
}

class MensajeT2 extends Mensaje implements java.io.Serializable{
int operando1;
String operacion = "inverso";

public MensajeT2(int op1){
operando1 = op1;
}
public int getOperando1(){
return operando1;
}
}

class MensajeT3 extends Mensaje implements java.io.Serializable{
int operando1;
int operando2;
int operando3;
String operacion = "maximo";

public MensajeT3(int op1, int op2, int op3){
operando1 = op1;
operando2 = op2;
operando3 = op3;
}
public int getOperando1(){
return operando1;
}
public int getOperando2(){
return operando2;
}
public int getOperando3(){
return operando3;
}
}
