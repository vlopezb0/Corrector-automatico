import java.io.*;

public class PrimosApp {
public static void main(String[] args) throws IOException{
boolean bPrimo;
int n, m;
BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));
System.out.println("Introduzca el primer numero");
n = Integer.parseInt(leer.readLine());
System.out.println("Introduzca el segundo numero");
m = Integer.parseInt(leer.readLine());
for(int numero=n; numero<m; numero+=2){
bPrimo=true;
for(int i=3; i<numero/2; i+=2){
if(numero%i==0){
bPrimo=false;
break;
}
}
if(bPrimo){
System.out.print(numero+" - ");
}
}
}
}
