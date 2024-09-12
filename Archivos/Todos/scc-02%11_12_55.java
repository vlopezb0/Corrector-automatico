import java.io.*;
import com.sun.media.jsdt.*;

public class principal {

static String type="socket";
public static void main (String args []) throws IOException {
String ip;
int puerto;
do {
iniciarRegistro();
URLString url1 = URLString.createSessionURL(ip, puerto, type, linea);
cliente c11=new cliente("cl1");
cliente c12=new cliente("cl2");
cliente c13=new cliente("cl3");
Session ses = SessionFactory.createSession(c11, url1, true);
Channel ch1 = sesi.createChannel(c11, "canal1", true, true, true); // con auto-join
chl.addConsumer(c1,c1);
chl.addConsumer(c1,c2);
chl.addConsumer(c1,c3);
// pararRegistro();

 cliente c1=new cliente(linea);

} catch (Exception e) {
System.out.println ("Problemas encontrados");
}
}
else if (opcion == 3) {
listarSesionesActivas();
}
} while (opcion != 4);
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


public static void listarSesionesActivas(){
try{
URLString listaURL [] = RegistryFactory.list();
for(int i=0; i<listaURL.length; i++)
System.out.println(listaURL[i].toString());
}
catch(Exception e){
System.out.println ("Error al listar las sesiones");
}
}

}
