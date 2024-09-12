import com.sun.media.jsdt.*;

public class Ejercicio{

public static void main(String[] args) {
String type = "socket";

try {
RegistryFactory.startRegistry (type);
} catch (NoRegistryException e) { e.printStackTrace();
} catch (RegistryExistsException e1) { e1.printStackTrace(); }

try {
Client client_1 = new Client ("c1");
Client client_2 = new Client ("c2");
Client client_3 = new Client ("c3");
Client client_4 = new Client ("c4");
Client client_5 = new Client ("c5");

URLString url_1 = URLString.createSessionURL ("localhost", 4461, "socket", "Sesion_1");
URLString url_2 = URLString.createSessionURL ("localhost", 4461, "socket", "Sesion_2");

//primero hay que a�adir los clientes a las sesiones y luego a los canales
Sesion_1.join(client_1);
Sesion_1.join(client_2);
Sesion_1.join(client_3);
Sesion_2.join(client_1);
Sesion_2.join(client_2);
Sesion_2.join(client_4);
Sesion_2.join(client_5);

Channel Canal_1 = Sesion_1.createChannel (client_1, "Canal_1", true, true, true);
Channel Canal_2 = Sesion_1.createChannel (client_1, "Canal_2", true, true, true);
Channel Canal_3 = Sesion_1.createChannel (client_2, "Canal_3", true, true, true);

Channel Canal_4 = Sesion_2.createChannel (client_1, "Canal_4", true, true, true);
Channel Canal_5 = Sesion_2.createChannel (client_4, "Canal_5", true, true, true);

try{
Canal_1.join(client_2);
Canal_2.join(client_3);
Canal_3.join(client_3);
Canal_4.join(client_2);
Canal_4.join(client_4);
Canal_4.join(client_5);
Canal_5.join(client_5);
}catch(JSDTException e){
System.out.println("No se a podido aadir el cliente");
}
} catch (JSDTException e) {
e.printStackTrace();
} catch (Exception e) {
e.printStackTrace();
}
}
}
