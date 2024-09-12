import com.sun.media.jsdt.*;
import java.io.*;

class Ejercicio {

static BufferedReader leer = new BufferedReader (new InputStreamReader (System.in));
static boolean arrancado = false;
public static void main (String [] args) throws IOException {

boolean seguir = true;

// Mostramos el men;
int opcion=0;
String type="";
Servidor s=new Servidor(type);

do {
System.out.println("MEN DEL PROGRAMA:");
System.out.println("1. Inicio/Parada del registro JSDT");
System.out.println("2. Creacin del Cliente, la Sesin y el Canal");
System.out.println("3. Listar las sesiones activas");
System.out.println("4. Salir");
System.out.print("Introduzca la opcin deseada: ");

try {
opcion=Integer.parseInt(leer.readLine());
} catch (NumberFormatException e) {
System.out.println("Introduzca un numero correcto");
}

// En opcion metemos la opcin del men;

switch (opcion) {
case 1: iniciar_parar (s);
seguir=true;
break;
case 2: creaciones (s);
seguir=true;
break;
case 3: listar ();
seguir=true;
break;
case 4: System.out.println("Cerrando el programa...");
seguir=false;
break;
}
} while (seguir);
}

// Mtodo iniciar/parar un registro JSDT
public static void iniciar_parar (Servidor s) {

if(arrancado){
s.stop();
arrancado=false;
}
else{
s.start();
arrancado=true;
}
}

// Mtodo creaciones para crear Cliente, Sesion y Canal
public static void creaciones (Servidor s) throws IOException {
System.out.print("introduzca nombre cliente: ");
String client=leer.readLine();
System.out.print("introduzca nombre sesion: ");
String sesion=leer.readLine();
System.out.print("introduzca nombre canal: ");
String canal=leer.readLine();
System.out.print("introduzca direccion: ");
String dir=leer.readLine();
System.out.print("introduzca puerto: ");
int puerto=Integer.parseInt(leer.readLine());

//Creando el nuevo cliente
Cliente c = s.getCliente();
c= new Cliente (client);

try {
//Creando la nueva sesin
URLString url1 = URLString.createSessionURL (dir, puerto, s.getType(), sesion);
Session ses = s.getSesion();
ses= SessionFactory.createSession (c, url1, false);

//Creando el nuevo canal
ses.join(c);
Channel can = s.getCanal();
can = ses.createChannel(c, canal, true, true, true);
can.join (c);
can.addConsumer(c, c);
} catch (Exception e) {
System.out.println("Error de alguna excepcion");
}
}

// Mtodo listar para listar las sesiones activas
public static void listar () {

}
}

class Cliente implements Client, ChannelConsumer {
protected String name;

public Cliente (String name) {
this.name=name;
}

public Object authenticate (AuthenticationInfo info) {
return null;
}

public String getName() {
return name;
}

public void dataReceived(Data data) {
System.out.println("El cliente "+getName()+ " recibe del canal "+data.getChannel().getName()+ " el dato "+data.getDataAsString());
}
}

class Servidor {
String type;
Session sesion1;
Channel canal1;
Cliente c1;

public Servidor (String type) {
this.type="socket";
}

public void start(){
try {
RegistryFactory.startRegistry (type);
System.out.println("Registro iniciado");
} catch (NoRegistryException nre) {
System.out.println("No puede iniciarse el registro");
} catch (RegistryExistsException ree) {
System.out.println("El registro ya est iniciado");
}
}

public void stop(){
try {
RegistryFactory.stopRegistry (type);
System.out.println("Registro iniciado");
} catch (NoRegistryException nre) {
System.out.println("No puede iniciarse el registro");
}
}

public Cliente getCliente() {
return c1;
}

public Session getSesion() {
return sesion1;
}

public Channel getCanal() {
return canal1;
}

public String getType() {
return type;
}
}
