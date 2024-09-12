import java.io.*;
class Fibonacci{
public static void main (String []args)throws IOException{
BufferedReader leer=new BufferedReader (new InputStreamReader(System.in));
int n,resultado;
System.out.println("Introduzca numero para calcular Fibonacci");
n=Integer.parseInt(leer.readLine());
if(n>=0){
resultado=Fibon(n);
System.out.println("El resultado es: "+resultado);
}
else{
System.out.println("El numero introducido no es valido para calcular Fibonacci");
}

}
public static int Fibon(int n){
int resultado;
if(n<=1){ //Caso base
if(n==0){
resultado=0;
}
else{
resultado=1;
}
}
else{
resultado=Fibon(n-1)+Fibon(n-2);
}
return resultado;
}
}

