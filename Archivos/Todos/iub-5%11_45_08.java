import java.io.*;
import java.util.Vector;
public class primos {
public static void main(String[] args) throws IOException{
int n,m;
Vector primos;
BufferedReader leer= new BufferedReader (new
InputStreamReader (System.in));
System.out.println("Introduce n: ");
n=Integer.parseInt(leer.readLine());
System.out.println("Introduce m: ");
m=Integer.parseInt(leer.readLine());
if(n<m){
for(int i=n; i<=m; i++)
if (esPrimo(i)){
System.out.print(i+" ");
}
}
else{
if(m>n)
for(int i=m; i<=n; i++)
if (esPrimo(i)){
System.out.print(i+" ");
}
else //son iguales
if(esPrimo(n))
System.out.print(i);
}
}

public static boolean esPrimo(int n){
boolean esprimo=true;
for(int i =2; i<=(n/2); i++)
if((n%i)==0) esprimo=false;
return esprimo;
}
}


