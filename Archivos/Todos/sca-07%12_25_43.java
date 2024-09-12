import com.sun.media.jsdt.*;
import com.sun.media.jsdt.event.*;

public class servidor {

public static void main(String argv[]){

if(!RegistryFactory.registryExists("socket")){
mObservador.notificaEvento("Iniciando registro...");
RegistryFactory.startRegistry("socket");
}

URLString url = URLString.createSessionURL("localhost",4461,"socket","sesion1");
URLString url2 = URLString.createSessionURL("localhost",4461,"socket","sesion2");

cliente cliente1=new cliente("cliente1");
cliente cliente2=new cliente("cliente2");
cliente cliente3=new cliente("cliente3");
cliente cliente4=new cliente("cliente4");
cliente cliente5=new cliente("cliente5");

Session sesion1=SessionFactory.createSession(cliente1,url,true);
Session sesion2=SessionFactory.createSession(cliente2,url,true);

sesion1.join(cliente1);
sesion1.join(cliente2);
sesion1.join(cliente3);

sesion2.join(cliente1);
sesion2.join(cliente2);
sesion2.join(cliente3);
sesion2.join(cliente4);
sesion2.join(cliente5);

Channel channel1=session.createChannel(cliente1,"canal1",true,true,false);
Channel channel2=session.createChannel(cliente3,"canal1",true,true,false);
Channel channel3=session.createChannel(cliente3,"canal1",true,true,false);
Channel channel4=session.createChannel(cliente4,"canal1",true,true,false);
Channel channel5=session.createChannel(cliente4,"canal1",true,true,false);

channel1.addConsumer(cliente1,cliente1);

channel1.addConsumer(cliente2,cliente2);
channel2.addConsumer(cliente1,cliente1);
channel2.addConsumer(cliente3,cliente3);
channel3.addConsumer(cliente2,cliente2);
channel3.addConsumer(cliente3,cliente3);
channel4.addConsumer(cliente1,cliente1);
channel4.addConsumer(cliente2,cliente2);
channel4.addConsumer(cliente3,cliente3);
channel4.addConsumer(cliente4,cliente4);
channel4.addConsumer(cliente5,cliente5);

channel5.addConsumer(cliente4,cliente4);
channel5.addConsumer(cliente5,cliente5);
}

}

class cliente implements Client, ChannelConsumer{

private String name;

public cliente(nombre){

this.name=nombre;
}

public Object authenticate(AuthenticationInfo info)
{
return null;
}

public String getName()
{
return name;
}

public shynchronized dataReceived(Data data){}


}
