import java.io.*;
import java.util.Vector;
class Prueba{

public static void main(String[] args)throws IOException{
int mayor=0;
Vector resultado=new Vector();
BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
for(int i=0;i<10;i++){
resultado.addElement(Integer.parseInt(br.readLine()));
System.out.println(resultado.elementAt(i));
}
mayor=maximo(resultado);
System.out.println("El numero mayor es -->"+mayor);
System.out.println(ordenar(resultado));
}
public static int maximo (Vector v) {
int mayor=0;
Integer it=new Integer(0);
int comparar=0;
for (int i=0; i<v.size(); i++) {
it=(Integer)(v.elementAt(i));
comparar=Integer.parseInt(it.toString());
if (mayor<comparar)
mayor=comparar;
}
return mayor;
}
public static Vector ordenar (Vector v) {
Vector resultado=new Vector();
Object maximo;
for(int i=9;i>=0;i--){
maximo=new Integer(maximo(v));
resultado.addElement(maximo);
v.removeElement(maximo);
}
return resultado;
}

}
