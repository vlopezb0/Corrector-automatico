

import com.sun.media.jsdt.*;


public class Estructura{

public static void main(String args[]) {
Client cliente1,cliente2,cliente3 = null;
Session sesion1,sesion2 = null;
URLString url1 = URLString.createSessionURL("hostname",4361,"socket","sesion1");
URLString url2 = URLString.createSessionURL("hostname",4361,"socket","sesion2");
Channel canal1,canal2,canal3;
String sessionType = null;
String hostname = null;
int hostport = 0;


/* Create a session, [re]bind it and create a channel. */
try{
cliente1 = new EjemploCliente("cliente1");
cliente2 = new EjemploCliente("cliente2");
cliente3 = new EjemploCliente("cliente3");

sesion1 = SessionFactory.createSession(cliente, url1, false);
sesion2 = SessionFactory.createSession(cliente, url2, false);

sesion1.join(cliente1);
sesion1.join(cliente2);
sesion1.join(cliente3); 

canal1=sesion1.createChannel(cliente, "canal1", true, true, false);
canal2=sesion1.createChannel(cliente, "canal2", true, true, false);
canal3=sesion1.createChannel(cliente, "canal3", true, true, false);

canal1.join(cliente1);
canal1.join(cliente2);

canal2.join(cliente1);
canal2.join(cliente3);

canal3.join(cliente2);
canal3.join(cliente3);



}catch(JSDTException e){
System.out.println(e.toString());
}


}

}

class ExampleCliente implements Client {

private String name;

public EjemploCliente(String name) {
this.name = name;
}
public Object authenticate(AuthenticationInfo info) {
return(null);
}
public String getName() {
return(name);
}
}
