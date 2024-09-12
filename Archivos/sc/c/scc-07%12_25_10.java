import com.sun.media.jsdt.*;
import java.io.*;

public class servidor
{
static String type="socket";
static Session ses;
static Channel ch1, ch2;
static Cliente cl1,cl2,cl3,clSes;

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

crearSesiones();
crearClientes();

crearCanales();
comunicardatos();
desconectar();
}

static void crearSesiones()
{
try {
// ses
URLString url1 = URLString.createSessionURL("localhost", 12000, type, "ses");
clSes=new Cliente("clSes");
ses = SessionFactory.createSession(clSes, url1, false);
} catch(Exception e) {
e.printStackTrace();
}
}

static void crearCanales()
{
try {
// canales ses
ses.join(cl1); // los clientes deben unirse primero a la sesin
ses.join(cl2);
ses.join(cl3);
ch1 = ses.createChannel(cl1, "canal1", true, true, true); // con auto-join
ch1.addConsumer(cl1,cl1);
ch1.join(cl2);
ch1.addConsumer(cl2,cl2);
ch2 = ses.createChannel(cl2, "canal2", true, true, true);
ch2.addConsumer(cl2,cl2);
ch2.join(cl3);
ch2.addConsumer(cl3,cl3);


} catch(Exception e) {
e.printStackTrace();
}
}

static void crearClientes()
{
cl1=new Cliente("cl1");
cl2=new Cliente("cl2");
cl3=new Cliente("cl3");

}

static void comunicardatos(){


MensajeOperador men1=new MensajeOperador("+");
MensajeNumero men2=new MensajeNumero(7,9);
Data data1=new Data(men1);
Data data2=new Data(men2);
try{
ch1.sendToOthers(cl1,data1);
ch2.sendToOthers(cl2,data1);
Thread.sleep(550);
ch2.sendToOthers(cl2,data2);
}catch(Exception e){}
}

static void desconectar()
{
try {
// clientes salen de canales
ch1.leave(cl1);
ch1.leave(cl2);
ch2.leave(cl2);
ch2.leave(cl3);

// clientes salen de sesiones
ses.leave(cl1);
ses.leave(cl2);
ses.leave(cl3);

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

class Cliente implements Client, ChannelConsumer{

String nombreCliente=null;

int numero1,numero2;
String operador;

public Cliente(String nombre){

nombreCliente=nombre;
}
public String getName()
{
return nombreCliente;
}

/* authenticate viene de la interfaz Client. */
public Object authenticate(AuthenticationInfo info)
{
return (null);
}

public synchronized void dataReceived(Data datosRecibidos){

try{

MensajeOperador MenOp= (MensajeOperador)datosRecibidos.getDataAsObject();
operador=MenOp.getOperador();

}catch(Exception e){


try{
MensajeNumero Mennu=(MensajeNumero)datosRecibidos.getDataAsObject();
numero1=Mennu.getNumero1();
numero2=Mennu.getNumero2();
System.out.println(numero1);
System.out.println(operador);
System.out.println(numero2);
}catch(Exception e1){}
}
}
}

class MensajeOperador implements java.io.Serializable
{
private short tipo;
String operador=null;

public MensajeOperador(String operador)
{

this.operador=operador;
}

public int getTipo() {
return tipo;
}

public void setTipo(short t) {
tipo = t;
}
public String getOperador(){
return operador;
}
}

class MensajeNumero implements java.io.Serializable
{
private short tipo;
private int numero1,numero2;

public MensajeNumero(int numero1,int numero2)
{

this.numero1=numero1;
this.numero2=numero2; 

}

public int getTipo() {
return tipo;
}

public void setTipo(short t) {
tipo = t;
}
public int getNumero1(){return numero1;}
public int getNumero2(){return numero2;}
}
