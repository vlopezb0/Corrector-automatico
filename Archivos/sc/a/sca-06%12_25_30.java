import com.sun.media.jsdt.*;

public class servidor{
public static void main(String args[]) {

Session session1 = null;
Session session2 = null;
URLString url = null;
URlString url2 = null;
String sessionType = "socket";
String hostname = "localhost";
int hostport = 4664;
int hostport2 =4663;
url1=URLString.createSessionURL(hostname, hostport, sessionType, "sesion1");
url2=URLString.createSessionURL(hostname, hostport2, sessionType, "sesion2");

boolean created = false;
Client client = new ExampleClient ("cliente");

try {
if (RegistryFactory.RegistryExits(type == false)) {
RegistryFactory.startRegistry(type);
}
}catch (NoRegistryException nre) {
System.out.println("Could not star a registry...");
}catch (RegistryExistsException ree) {
System.out.println("The registry is already running");
}

try {
ExampleClient C1 = new ExampleClient("C1");
ExampleClient C2 = new ExampleClient("C2");
ExampleClient C3 = new ExampleClient("C3");
ExampleClient C4 = new ExampleClient("C4");
ExampleClient C5 = new ExampleClient("C5");

session1 = SessionFactory.createSession(client, url, true));
session1.createChannel(client, "Channel1", true, true, false);
session1.createChannel(client, "Channel2", true, true, false);
session1.createChannel(client, "Channel3", true, true, false);

session2 = SessionFactory.createSession(client, url2, true));
session2.createChannel(client, "Channel4", true, true, false);
session2.createChannel(client, "Channel5", true, true, false);

}
} catch (JSDTException e) {
System.out.println("No puede crearse la sesin");
}
}//main
}//clase servidor

public class ExampleClient implements Client {

protected String name;

public ExampleClient(String name) {
this.name = name;
}
public Object authenticate(AuthenticationInfo info) {
System.err.println("ChatClient: authenticate.");
return(null);
}
public String getName() {
return(name);
}
}

