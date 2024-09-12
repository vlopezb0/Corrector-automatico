import java.io.*;
import com.sun.media.jsdt.*;

public class Sesion{

boolean created = false;
//Client cliente = null;
Session session = null;
URLString url;

public Sesion(String name){
url= URLString.createSessionURL("localhost", 4461, "socket", name);
try {
while (created == false) {
 if (SessionFactory.sessionExists(url)){
session = SessionFactory.createSession(null, url, false);
created = true;
 }else{
try {
Thread.sleep(5000);
} catch (InterruptedException e) {
}
 }
}
 } catch (JSDTException e){
System.out.println("Couldn't create the Session.");
 }
}

public void addClient (Cliente c){
this.session.join(c);
}

}

class Main {
    public static void main (String [] args){
    System.out.println("Hola!");
    }
}

// import java.io.*;
// import com.sun.media.jsdt.*;

public class Cliente implements CileiCliente{

private String name;

public Cliente(String name){
this.name = name;
}

public Object authenticate (AuthenticationInfo info){
return(null);
}
public String getName(){
return (name);
}
}


// import com.sun.media.jsdt.*;
// import java.io.*;
// import Sesion;

class Registro{
public static void main(String [] args) throws IOException{
 String type = "socket";
try{
 if (RegistryFactory.registryExists(type) == false){
RegistryFactory.startRegistry (type);
 }
 }catch (NoRegistryException nre){
System.out.println ("couldn`t start a Registry of thistype.");
 } catch (RegistryExistsException ree){
System.out.println ("The Registry is already running.");
 }

iniciarAplic();
}

public void iniciarAplic(){
Sesion s1=new Sesion("S1"), s2=new Sesion("S2");
}
}


// import java.io.*;
// import com.sun.media.jsdt.*;

public class Sesion{

boolean created = false;
//Client cliente = null;
Session session = null;
URLString url;

public Sesion(String name){
url= URLString.createSessionURL("localhost", 4461, "socket", name);
try {
while (created == false) {
 if (SessionFactory.sessionExists(url)){
session = SessionFactory.createSession(null, url, false);
created = true;
 }else{
try {
Thread.sleep(5000);
} catch (InterruptedException e) {
}
 }
}
 } catch (JSDTException e){
System.out.println("Couldn't create the Session.");
 }
}

public void addClient (Cliente c){
this.session.join(c);
}

}
