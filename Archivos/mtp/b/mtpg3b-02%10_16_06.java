import java.io.*;
class fibonacci{
static BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));
public static void main (String [] args) throws IOException{
System.out.print("Por favor, introduzca termino de la sucesion: ");
int n=Integer.parseInt(leer.readLine());
fibonacci(n);
int valor=0;
valor=fibonacci(valor);
System.out.println("El termino "+n+" de la sucesion de Fibonacci es "+valor);
}//Fin main

public static int fibonacci(int n){
int valor =0;
if (n==0){
valor = 0;
}else{
if (n==1){
valor=1;
}else{
valor= fibonacci(n-2) + fibonacci(n-1);
}//Fin else
}//Fin else
return valor;
}// Fin fibonacci
}//Fin class



