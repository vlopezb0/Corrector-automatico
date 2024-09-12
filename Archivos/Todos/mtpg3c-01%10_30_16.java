import java.io.*;

class Euclides{
public static void main(String[]args) throws IOException{
BufferedReader leer=new BufferedReader(new InputStreamReader(System.in));
int n,m,mcd;
System.out.println("Introduzca el numero entero n: ");
n=Integer.parseInt(leer.readLine());
System.out.println("Introduzca el numero entero m para calcular el maximo comun divisor, m debe ser mayor que n: ");
m=Integer.parseInt(leer.readLine());
mcd=euclides(n,m);
System.out.println("El maximo comun divisor de " n + " y " + m " es" + mcd);


while(n>=m){
System.out.println("m debe ser mayor que n");
System.out.println("Introduzca el numero entero n: ");
n=Integer.parseInt(leer.readLine());
System.out.println("Introduzca el numero entero m para calcular el maximo comun divisor, m debe ser mayor que n: ");
m=Integer.parseInt(leer.readLine());
}
}

public static int euclides(n,m){
int mcd;
if((m%n)==0){
mcd=n;
}else{
m=n;
n=m%n;
mcd=euclides(n,m);
}
return mcd;
}
}
