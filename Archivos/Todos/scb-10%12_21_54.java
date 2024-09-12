import com.sun.media.jsdt.*;
import java.io.*;

public class Aplicacion {

private static Session sesion;
private static Cliente c1;
private static Channel canal;

public static void main(String [] args){
sesion=null;
c1=new Cliente("c1");
canal=null;
menu();
}

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
}

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
}

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
}

private static Channel crearCanal(Session sesion,Client cliente){
Channel canal = null;

try{
canal=sesion.createChannel(cliente,"\"Chat ESDLA\"",true,true,true);
}
catch(JSDTException jsdte){
System.out.println("Couldn't create the channel");
}
return canal;
}

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

}

private static void unirConsumidor(Channel canal,Cliente cliente,Consumidor consumidor){
try{
canal.addConsumer(cliente,consumidor);
System.out.println("Se ha unido "+consumidor);
}catch(Exception e){
System.out.println("Couldn't not add channel consumer");
}
}

private static void aplicacion(int eleccion){

switch(eleccion){
case 1: System.out.println("\t\t-- INICIO / PARADA del Registro");
// Se debera iniciar si no existe y pararlo si ya existe
menu1();
break;

case 2: System.out.println("\t\t-- CREACION Cliente, Sesion y Canal del Registro");
menu2();
break;

case 3: System.out.println("\t\t-- LISTAS las sesiones activas.");
break;
}
}

public static void menu(){
BufferedReader leer=new BufferedReader(new InputStreamReader(System.in));

int eleccion=0;
boolean salir=false;
while(!salir)
try{
System.out.println("(1) Inicio/Parada del registro.");
System.out.println("(2) Creacin de un cliente, una sesin y un canal.");
System.out.println("(3) Listar las sesiones activas.");
System.out.println("(4) Salir.");
System.out.print("Su eleccion es: ");
eleccion=Integer.parseInt(leer.readLine());
System.out.println();
if(eleccion<1 || eleccion>4)
eleccion=0;
else{
if(eleccion<4)
aplicacion(eleccion);
else{
System.out.println("Gracias por utilizar nuestro Software.");
salir=true;
}
}
if(eleccion==0)
System.out.println(">> Por favor introduzca un numero [1-4]");
}
catch(NumberFormatException nfe){
eleccion=0;
System.out.println(">> Por favor introduzca un numero [1-4]");
}
catch(IOException ioe){}
}

public static void iniciarPararRegistro(int eleccion){
System.out.println("ENTRO CON "+eleccion);
if(eleccion==1){
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
}
else{
// parar el registro
cerrarSesion(sesion); // por lo pronto cerramos la nica sesin creada
System.out.println("REGISTRO PARADO: MENTIRA DE LAS GORDAS");
}
}

public static void menu1(){
BufferedReader leer=new BufferedReader(new InputStreamReader(System.in));

int eleccion=0;
boolean salir=true;
while(!salir)
try{
System.out.println("\t(1) Inicio del registro.");
System.out.println("\t(2) Parada del registro.");
System.out.println("\t(3) Volver.");
System.out.print("\tSu eleccion es: ");
eleccion=Integer.parseInt(leer.readLine());
System.out.println();
if(eleccion<1 || eleccion>3)
eleccion=0;
else{
if(eleccion<3)
iniciarPararRegistro(eleccion);
else
salir=true;
}
if(eleccion==0)
System.out.println(">> Por favor introduzca un numero [1-3]");
}
catch(NumberFormatException nfe){
eleccion=0;
System.out.println(">> Por favor introduzca un numero [1-3]");
}
catch(IOException ioe){
System.out.println("IOE "+ioe);
}
}

public static void menu2(){
BufferedReader leer=new BufferedReader(new InputStreamReader(System.in));

int eleccion=0;
boolean salir=false;
while(!false)
try{
System.out.println("\t(1) Crear Cliente.");
System.out.println("\t(2) Crear Sesin.");
System.out.println("\t(3) Crear Canal.");
System.out.println("\t(4) Goya la mejor Pelcula: VOLVER");
System.out.print("\tSu eleccion es: ");
eleccion=Integer.parseInt(leer.readLine());
System.out.println();
if(eleccion<1 || eleccion>4)
eleccion=0;
else{
if(eleccion<4)
switch(eleccion){
case 1: System.out.print("Introduzca su nombre de Cliente: ");
String nombreCliente=leer.readLine();
c1=new Cliente(nombreCliente);
// falta terminar
break;
case 2: System.out.print("Introduzca el nombre del Host: "); String hostName=leer.readLine();
int puerto=10000;
try{
System.out.print("Introduzca el puerto [>=10000]: "); puerto=Integer.parseInt(leer.readLine());
if(puerto<10000)
puerto=10000;
}
catch(NumberFormatException nfe){
puerto=10000;
}
System.out.print("Introduzca el nombre de la Sesin: "); String nombreSesion=leer.readLine();
sesion=crearSesion(c1,URLString.createSessionURL(hostName,puerto,"socket",nombreSesion));
break;
case 3: Channel canal=crearCanal(sesion,c1);
}
else
salir=true;
}
if(eleccion==0)
System.out.println(">> Por favor introduzca un numero [1-3]");
}
catch(NumberFormatException nfe){
eleccion=0;
System.out.println(">> Por favor introduzca un numero [1-3]");
}
catch(IOException ioe){
System.out.println("IOE "+ioe);
}
}
}
