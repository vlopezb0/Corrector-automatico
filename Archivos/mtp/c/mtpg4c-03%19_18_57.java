import java.io.*;
class Comun_divisor{
public static void main(String [] args) throws IOException{
BufferedReader leer=new BufferedReader
(new InputStreamReader (System.in));
double n,m,Comun_divisor;
System.out.println("Programa que calcula el maximo comun divisor: ");
System.out.println("escribe dos numeros para conocer su maximo comun divisor");
System.out.println("(el primero tiene que ser mas pequeo que el segundo");
n=Double.parseDouble(leer.readLine());
m=Double.parseDouble(leer.readLine());
while (n>m){
System.out.println("el primer numero tiene que ser mas pequeo");
n=Double.parseDouble(leer.readLine());
m=Double.parseDouble(leer.readLine());
}
Comun_divisor=r(n,m);
System.out.println(Comun_divisor);
}
public static double r(double n,double m){
double resto,C_d;
resto=m%n;
if (resto==0){
C_d=n;     
}
else{
C_d=calcular_comun_divisor(m,n);
}
return C_d;
}
public static double calcular_comun_divisor(double n,double m){
double resto;
m=n;
n=resto;
return resto;
}
}





