import java.io.*;
import java.util.Vector;

class Primos{
public static void main (String [] args)throws IOException{

int num1=0, num2=0;
//declaramos el bufferd reader
BufferedReader leer =new BufferedReader(new InputStreamReader(System.in));
System.out.println ("Introduzca el l�mite inferior del rango");
num1=Integer.parseInt (leer.readLine());
System.out.println ("Introduzca el limite superior del rango");
num2=Integer.parseInt (leer.readLine());
//ahora checamos que num1 < num2

if (num2<num1){
System.out.println ("ha confundido los limites, pero lo arreglaremos");
int aux=num1;
num1=num2;
num2=aux;
System.out.println ("Se han ajustado los limites: inferior= "+num1+"superior= "+num2);
}
//ahora ya tenemos los numeros ordenados, vamos a calcular los primos
if (num1==0){
calcularNPrimos(num2);
} else{
calcularPrimos (num1, num2);
}
}//fin del main

public static void calcularPrimos (int num1, int num2){
//al principio no habra numeros primos....
boolean noHayPrimos=true;
boolean esPrimo=true;
for (int i=num1; i<=num2; i++){
//para cada nuemro, suponemos por defecto que es primo
esPrimo=true;
for (int j=2;j<i/2&&esPrimo; j++){
if(i%j==0){
esPrimo=false;
noHayPrimos=false;
}
}//fin for dentro
if(esPrimo) System.out.println ("Numero Primo "+i);
}//fin for de fuera
if(noHayPrimos) System.out.println("El rango elegido no tiene primos");
}//fin metodo

public static void calcularNPrimos (int num1){
//al principio no habra numeros primos....
boolean noHayPrimos=true;
boolean esPrimo=true;
for (int i2=0, i=0; i2<=num1;i++){
//para cada nuemro, suponemos por defecto que es primo
esPrimo=true;
for (int j=2;j<i/2&&esPrimo; j++){
if(i%j==0){
esPrimo=false;
noHayPrimos=false;
}
}//fin for dentro
if(esPrimo){
System.out.println ("Numero Primo "+i);
i2++;
}
}//fin for de fuera
if(noHayPrimos) System.out.println("El rango elegido no tiene primos");
}//fin metodo

}//fin de la clase prueb

