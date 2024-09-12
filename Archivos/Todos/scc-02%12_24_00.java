import java.io.*;
import com.sun.media.jsdt.*;

public class principal {
static cliente cl1,cl2,cl3;
static String type="socket";
public static void main (String args []) throws IOException {

try {
iniciarRegistro();
int puerto = 12000;
String ip = "localhost";
cl1=new cliente("cl1");
cl2=new cliente("cl2");
cl3=new cliente("cl3");

URLString url1 = URLString.createSessionURL(ip, puerto, type, "ses");
Session ses = SessionFactory.createSession(cl1, url1, true);
ses.join(cl2);
ses.join(cl3);

Channel ch1 = ses.createChannel(cl1, "canal1", true, true, true);
Channel ch2 = ses.createChannel(cl1, "canal2", true, true, true);
ch1.join(cl1);
ch1.join(cl2);
ch2.join(cl2);
ch2.join(cl3);
ch1.addConsumer(cl1,cl2);
ch2.addConsumer(cl1,cl3);
// pararRegistro();
} catch (Exception e) {
System.out.println ("Error");
}

}

public static void iniciarRegistro(){
try {
RegistryFactory.startRegistry(type);
System.out.println("Registro iniciado");
} catch(NoRegistryException nre) {
System.out.println("No puede iniciarse el registro");
} catch(RegistryExistsException ree) {
System.out.println("El registro ya esta iniciado");
}
}

public static void pararRegistro(){
try {
RegistryFactory.stopRegistry(type);
System.out.println("Registro parado");
} catch(NoRegistryException nre) {
System.out.println("No puede pararse el registro");
} catch (Exception e) {
System.out.println ("No se pueden liberar los recursos");
}
}




}



class cliente implements Client,ChannelConsumer {

protected String name;

public cliente(String name) {
this.name=name;
}

public Object authenticate(AuthenticationInfo info){
return null;
}

public String getName(){
return name;
}

public void dataReceived(Data data){
System.out.println("El cliente '"+getName()+"' recibe por el canal '"+
data.getChannel().getName()+"' el dato '"+data.getDataAsString()+"'");
}

/* public void enviarDatos( Mensaje m){
try {
Data data= new Data( m);
data.setPriority( Channel.HIGH_PRIORITY );
canal.sendToOthers( this ,data);
}
catch (Exception e) {
System.err.println(" Error");
}
}
*/
}


class Mensaje1 implements java.io.Serializable{
int operando1;
int operando2;
int resultado;

public Mensaje1(int op1, int op2) {
operando1 = op1;
operando2 = op2;
}

public int getOpe1 () {
return operando1;
}
public int getOpe2 () {
return operando2;
}
public void resultado (int result) {
resultado = result;
}
}

class Mensaje2 implements java.io.Serializable{
double operando1;
double resultado;

public Mensaje2(double op1) {
operando1 = op1;
}

public double getOpe1 () {
return operando1;
}

public void resultado (double result) {
resultado = result;
}
}


class Mensaje3 implements java.io.Serializable{
int operando1;
int operando2;
int operando3;
int resultado;

public Mensaje3(int op1, int op2, int op3) {
operando1 = op1;
operando2 = op2;
operando3 = op3;
}

public int getOpe1 () {
return operando1;
}
public int getOpe2 () {
return operando2;
}
public int getOpe3 () {
return operando3;
}
public void resultado (int result) {
resultado = result;
}
}
