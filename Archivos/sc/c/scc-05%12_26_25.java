import com.sun.media.jsdt.*;

public class Servidor
{
static String type="socket";
static Session ses;
static Channel ch1,ch2;
static Cliente cl1,cl2,cl3, clSes;

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
crearSesion();
crearCanales();
comunicarDatos();
desconectar();
}

public static void crearSesion(){
 try {
 // Creo la sesion ses
 URLString url1 = URLString.createSessionURL("localhost", 12000, type, "ses");
 clSes=new Cliente("clSes");
 ses = SessionFactory.createSession(clSes, url1, false);
 }
catch(Exception e) {
 e.printStackTrace();
 }
}

public static void crearCanales(){
 try {
 // canales ses
 ses.join(cl1); // los clientes deben unirse primero a la sesin
 ses.join(cl2);
 ses.join(cl3);
 ch1 = ses.createChannel(cl1, "ch1", true, true, true); // con auto-join
 ch1.addConsumer(cl1,cl1);
 ch1.join(cl2);
 ch1.addConsumer(cl2,cl2);
 ch2 = ses.createChannel(cl2, "ch2", true, true, true);
 ch2.addConsumer(cl2,cl2);
 ch2.join(cl3);
 ch2.addConsumer(cl3,cl3);

 }//end try
catch(Exception e) {
 e.printStackTrace();
 }//end catch
 }//end crearCanales

public static void crearClientes(){
 cl1=new Cliente("cl1");
 cl2=new Cliente("cl2");
 cl3=new Cliente("cl3");
 }

public static void comunicarDatos(){
 try {
 // envo de datos por los canales
//primero envio una suma
MensajeSuma suma = new MensajeSuma (2,2);
 Data data = new Data(suma);
 ch1.sendToOthers(cl1, data);
//ahora envio el inverso 
MensajeInverso inv = new MensajeInverso(2);
Data data2 = new Data(inv);
ch1.sendToOthers(cl1, data2);
// mensaje del maximo de tres numeros
MensajeMaximo max = new MensajeMaximo(1,2,3);
Data data3 = new Data(max);
ch1.sendToOthers(cl1, data3);

 }//end try
catch(Exception e) {
 e.printStackTrace();
 }//end comunicarDatos
}

public static void desconectar()
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
 System.out.println("Ses cerrada");
 RegistryFactory.stopRegistry(type);
 System.exit(0);
 }//end try
catch(Exception e) {
 System.out.println("No se pueden liberar los recursos");
 e.printStackTrace();
 }//end catch
}//end desconectar

}//end Servidor


class MensajeSuma implements java.io.Serializable {

int operando1;
int operando2;
public MensajeSuma (int op1, int op2) {
this.operando1= op1;
this.operando2 = op2;
}
}//end class

class MensajeInverso implements java.io.Serializable{
int operando;
public MensajeInverso (int op){
this.operando = op;
}
}//end class

class MensajeMaximo implements java.io.Serializable{
int operando1;
int operando2;
int operando3;
public MensajeMaximo (int op1, int op2, int op3){
this.operando1 = op1;
this.operando2= op2;
this.operando3 = op3;
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
//si es el cliente dos el que recibe el mensaje
if (getName().equals("cl2")){
//if (data.getDataAsObject() instanceof MensajeSuma){
//int resultado = data.getDataAsObject().operando1 +
//data.getDataAsObject().operando2;
//Data datasum = new Data (resultado);
//ch2.sendToOthers(cl2, data);
//}
}//end if
 //System.out.println("El cliente '"+getName()+"' recibe por el canal '"+
//data.getChannel().getName()+"' el dato '"+data.getDataAsString()+"'");
}


}//end class Cliente

