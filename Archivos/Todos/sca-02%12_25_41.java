import com.sun.media.jsdt.*;

public class Ejemplo {

public static void main(String[] args) {
String type = "socket";

try {
if(RegistryFactory.registryExists(type) == false){
RegistryFactory.startRegistry (type);
}
} catch (NoRegistryException e) { e.printStackTrace();
} catch (RegistryExistsException e1) { e1.printStackTrace(); }

try {
Cliente cliente_1 = new Cliente ("cliente_1");
Cliente cliente_2 = new Cliente ("cliente_2");
Cliente cliente_3 = new Cliente ("cliente_3");
Cliente cliente_4 = new Cliente ("cliente_4");
Cliente cliente_5 = new Cliente ("cliente_5");

URLString url = URLString.createSessionURL ("localhost", 4461, "socket", "Session1");
URLString url2 = URLString.createSessionURL ("localhost", 4461, "socket", "Session2");

Session session = SessionFactory.createSession (cliente_1, url, true);
session.join (client_2);
session.join (client_3);

Session session2 = SessionFactory.createSession (cliente_1, url2, true);
session2.join (client_2);
session2.join (client_4);
session2.join (client_5);

Channel channel1 = session.createChannel (cliente_1, "ChatChannel", true, true, true);
Channel channel2 = session.createChannel (cliente_3, "ChatChannel", true, true, true);
Channel channel3 = session.createChannel (cliente_2, "ChatChannel", true, true, true);
Channel channel4 = session.createChannel (cliente_4, "ChatChannel", true, true, true);
Channel channel5 = session.createChannel (cliente_5, "ChatChannel", true, true, true);
channel1.join (client_2);
channel1.join (client_2);

ChannelConsumer consumer = new Consumer ("Consumidor1","hola");
channel.addConsumer(cliente_1, consumer);

} catch (JSDTException e) {
e.printStackTrace();
} catch (Exception e) {
e.printStackTrace();
}
}
}


class Consumer implements ChannelConsumer{

/** The name of this channel consumer. */
protected String name;

/** The location to write all messages received. */
TextArea messageArea;


public Consumer(String name, TextArea messageArea) {
this.name = name;
this.messageArea = messageArea;
}


public synchronized void dataReceived(Data data) {
String message;
int position = 0;
int priority = data.getPriority();
String senderName = data.getSenderName();
Channel channel = data.getChannel();
String theData = data.getDataAsString();


/* Construct message and output it to the end of the message area. */

message = senderName + ": " + theData + "\n";
position = messageArea.getText().length();
messageArea.insert(message, position);
}
}
