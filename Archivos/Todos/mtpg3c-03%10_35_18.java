import java.io.*;

class Euclides {
public static void main(String[]args)throws IOException{
BufferedReader leer=new BufferedReader(new InputStreamReader(System.in));
System.out.println("Introduzca el 1entero");
int n=Integer.parseInt(leer.readLine());
System.out.println("Introduzca el 2entero");
int m=Integer.parseInt(leer.readLine());

if(n<m){
int resultado=Euclides(m,n);
System.out.println("El mximo comun divisor de "+m+" y "+n+" es: "+resultado);
}else{
System.out.println("El primer nmero que introduzcas debe ser menor que el segundo");
}
}// main

public static int Euclides(int m,int n){
int resultado,resto;
if(m%n==0){
resultado=n;
}else{
resto=m%n;
resultado=Euclides(n,resto);
}
return resultado;
}// Euclides
}// class
