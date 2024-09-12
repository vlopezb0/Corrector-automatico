import java.io.*;
class Fibonacci{
public static void main (String [] args)throws IOException{
int n,p;
BufferedReader leer = new BufferedReader (new InputStreamReader(System.in));
System.out.println("Introduzca un entero:");
n=Integer.parseInt (leer.readLine () );
p=problemafibonacci(n);
System.out.println("El resultado es:" +p);
}

public static int problemafibonacci(int n){
if(n==0){
fibonacci=0;
}
if(n==1){
fibonacci=1;
}
if(n>1){
fibonacci=problemafibonacci(n-1)+problemafibonacci(n-2);
}
return fibonacci;
}
}
