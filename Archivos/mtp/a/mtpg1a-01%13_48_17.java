import java.io.*;
class Palindromo{
public static void main(String [] args) throws IOException {
BufferedReader leer=new BufferedReader(new InputStreamReader(System.in));
String palabra;
boolean iguales=false;
int longitud;
System.out.println("Introduzca la palabra: ");
palabra=leer.readLine();
longitud=palabra.length();
int i=0;
int j=longitud-1;
int mitad=longitud/2;
iguales=metodopalin(palabra,i,j,mitad);
if(iguales==true)
System.out.println("La palabra introducida es un palindromo");
else
System.out.println("La palabra introducida no es un palindromo");
}//fin del main

public static boolean metodopalin(String palabra,int i, int j,int mitad){
boolean resultado=false;
if(mitad==i && mitad==j){ // caso base
if(palabra.charAt(i)==palabra.charAt(j))
resultado=true;
else
resultado=false;
}
else{
metodopalin(palabra,i+1,j-1,mitad);
}
return resultado;
}//fin del metodo
}// fin del class

