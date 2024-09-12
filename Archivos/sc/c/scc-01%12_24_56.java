import com.sun.media.jsdt.*;

class Servidor {
static String type="socket";
static Session sesion1;
static Channel canal1, canal2;
static Cliente c1, c2, c3;

public static void main (String [] args) {

// INICIAR EL REGISTRO DESDE YA
try {
RegistryFactory.starRegistry(type);
System.out.println("Registro iniciado");
} catch (NoRegistryException nre) {
System.out.println("No puede iniciarse el registro");
} catch (RegistryExistsExceotion ree) {
System.out.println("El registro ya est iniciado");
}
crearClientes();
crearSesiones();
crearCanales();
comunicarDatos();

//PARAR EL REGISTRO
try {
RegistryFactory.stopRegistry(type);
System.out.println("Registro parado");
} catch (Exception nre) {
System.out.println("No puede pararse el registro");
}
}

static void crearSesiones() {
try {
URLString url1 = URLString.createSessionURL("localhost", 12000, type, "sesion1");
c1=new Cliente("Cliente_1");
sesion1=SessionFactory.createSession(c1, url1, false);
}catch (Exception e) {
e.printStackTrace();
}
}

static void crearCanales () {
try {
//canales sesion1
sesion1.join(c1);
sesion1.join(c2);
sesion1.join(c3);

canal1=sesion1.createChannel (c1, "canal1", true, true, true);
canal1.addConsumer(c1,c1);
canal1.join(c1);
canal2=sesion1.createChannel (c2, "canal2", true, true, true);
canal2.addConsumer(c2,c2);
canal2.join(c2);
canal2=sesion1.createChannel (c3, "canal1", true, true, true);
canal2.addConsumer(c3,c3);
canal1.join(c3);
}catch (Exception e) {
e.printStackTrace();
}
}

static void crearClientes () {
c1=new Cliente("c1");
c2=new Cliente("c2");
c3=new Cliente("c3");
}

static void comunicarDatos () {
// Aqu est la miga de las clases (informacin de la pizarra)
try {
//El cliente 1 pide el tipo de operacion
//realmente se hace un ejemplo por cada
suma s = new suma();
inverso i= new inverso();
maximo m = new maximo();
Data datasuma=new Data(s);
Data datainv=new Data(i);
Data datamax=new Data(m);

canal1.sendToOthers(c1, new MensajeT1(datasuma));
canal1.sendToOthers(c1, new MensajeT1(datainv));
canal1.sendToOthers(c1, new MensajeT1(datamax));

// El cliente 2 calcula y enva
// Esto estar mal, pero bueno
canal2.sendToOthers(c2, new MensajeT1(s.calcularSuma()));
canal2.sendToOthers(c2, new MensajeT1(i.calcularInverso()));
canal2.sendToOthers(c2, new MensajeT1(m.calcularMaximo()));

// El cliente 3 muestra
// Esto estar mal, pero bueno
canal2.sendToOthers(c3, new MensajeT1(s.mostrarSuma()));
canal2.sendToOthers(c3, new MensajeT1(i.mostrarInverso()));
canal2.sendToOthers(c3, new MensajeT1(m.mostrarMaximo()));

} catch (Exception e) {
e.printStackTrace();
}
}
}

class suma{

public static int num1 = 3;
public static int num2 = 5;
public int resultado;

public suma(){
}

public int calcularSuma(){
this.resultado = this.num1 + this.num2;
return resultado;
}

public void mostrarSuma(){
System.out.println("El resultado de la suma es: " + resultado);
}
}//fin de la clase suma

class inverso{

public static int numero = 5;
public double resultado;

public inverso(){
}

public double calcularInverso(){
this.resultado = 1/this.numero;
return resultado;
}

public void mostrarInverso(){
System.out.println("El inverso del nmero es: " + resultado);
}
}//fin de la clase inverso

class maximo{

public static int num1 = 3;
public static int num2 = 5;
public static int num3 = 19;
public int resultado;

public maximo(){
}

public int calcularMaximo(){
int aux = Math.max(num1, num2);
resultado = Math.max(aux, num3);
return resultado;
}

public void mostrarMaximo(){
System.out.println("Los nmeros son: "+ num1+", "+num2+", "+num3 );
System.out.println("El mximo de los tres nmeros es: " + resultado);
}
}//fin de la clase maximo

class MensajeT1 implements java.io.Serializable {

// EN ESTA CLASE TENGO MIS DUDAS

Data data = new Data ();
public MensajeT1 (Data data) {
this.data=data;
}

public Object convertir () {
Object objetoFinal=null;
objetoFinal=(MensajeT1)data.getDataAsObject();
return objetoFinal;
}
}

class Cliente {
protected String name;

public Cliente (String name) {
this.name=name;
}

public Object authenticate(AuthenticationInfo info) {
return null;
}

public void dataReceived (Data data) {
System.out.println("El cliente ha recibido");
}
}
