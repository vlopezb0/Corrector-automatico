import com.sun.media.jsdt.*;
import java.io.*;
public class Problema2{
static Cliente1 c1;
static Cliente2 c2,c3;
static Session sesion1;
static Channel canal1 ;
 static Channel canal2;
 static BufferedReader leer=new BufferedReader(new InputStreamReader(System.in));

public static void main(String args[])throws Exception{
/*System.out.println("-----menu-----");
System.out.println("1-registrarse");
System.out.println("2-opcion2 del problema");


 boolean seguir=true;
while(seguir){
int opc=Integer.parseInt(leer.readLine());

if(opc==1){
registrarse();
}
if(opc==2){
crearSesyCan();
}

if (opc==3){
crearClientes();
}
if(opc==4){
seguir = false;
}
}*/

}
public static void registrarse()throws Exception{
try {
RegistryFactory.startRegistry("socket");
System.out.println("Registro iniciado");
} catch(NoRegistryException nre) {
System.out.println("No puede iniciarse el registro");
} catch(RegistryExistsException ree) {
System.out.println("El registro ya esta iniciado");
}

}

public static void crearSesyCan()throws Exception{
Cliente c1=new Cliente("c1");
URLString url1 = URLString.createSessionURL("localhost", 1000, "socket", "sesion1");
sesion1 = SessionFactory.createSession(c1, url1, true);
sesion1.join(c1);
canal1 = sesion1.createChannel(c1, "canal1", true, true, true);
canal2 = sesion1.createChannel(c1, "canal2", true, true, true);
canal1.addConsumer(c1,c1);
}

public static void crearClientes()throws Exception{
c1 = new Cliente1 ("C1");
c2 = new Cliente2 ("C2");
c3 = new Cliente2 ("C3");
Data data = new Data(new OperacionSuma(2,3));
 canal1.sendToClient(c1, "C2",data);


}

}

class OperacionSuma extends Operacion implements java.io.Serializable{
int op1,op2;
public OperacionSuma(int op1,int op2){
this.op1=op1;
this.op2=op2;
}
public int resultado(){
return op1+op2;
}

}

abstract class Operacion{
public abstract int resultado();
}
class OperacionInverso extends Operacion implements java.io.Serializable{
int result;
public OperacionInverso(int op1){
result = 1 / op1;

}
public int resultado(){
return result;
}
}

class OperacionMaximo extends Operacion implements java.io.Serializable{
int result;
public OperacionMaximo(int op1,int op2, int op3){
if (op1 > op2)
result = op1;
if(result < op3)
result = op3;

}
public int resultado(){
return result;
}
}

class Cliente1 implements Client,ChannelConsumer
{
protected String name;

public Cliente1(String name)
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
data.getChannel().getName()+"' el dato '"+data.getDataAsString()+"'");
}
}
class Cliente2 implements Client,ChannelConsumer
{
protected String name;
int dato;

public Cliente2(String name)
{
this.name=name;
}

public int getDato(){
return dato;
}
public Object authenticate(AuthenticationInfo info)
{
return null;
}

public String getName()
{
return name;
}

public void dataReceived(Data data)throws Exception
{
Operacion operacion=(Operacion)data.getDataAsObject();
int result=operacion.resultado();
Data data2 = new Data(result);
data.getChannel().sendToClient(this, "C3",data2);
}
}
