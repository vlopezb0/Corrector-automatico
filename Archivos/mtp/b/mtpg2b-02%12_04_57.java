import java.io.*;
class Fibonacci{
public static void main(String []args)throws IOException{
BufferedReader leer=new BufferedReader(new InputStreamReader (System.in));
System.out.println("Introduzca el nmero n de la sucesin");
int n=Integer.parseInt(leer.readLine());
int resultado;
if(n>=0){
resultado=Fibonacci (n);
System.out.println("El resultado es: "+resultado);
}
else
System.out.println("No se puede calcular la sucesion con ese termino");

}

public static int Fibonacci (int n){
int resultado;
if(n>=0)
if(n==0)
resultado=0;
else
resultado=1;
else
resultado=Fibonacci(n-1)+Fibonacci(n-2);
return resultado;
}
}










