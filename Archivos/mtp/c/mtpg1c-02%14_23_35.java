import java.io.*;
class Euclides{
public static void main(String [] args)throws IOException{
int n, m, mcd;
BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));
System.out.println("Introduzca n");
n=Integer.parseInt(leer.readLine());
System.out.println("Introduzca m");
m=Integer.parseInt(leer.readLine());
mcd=euclides(n,m);
System.out.println("El máximo común divisor es: "+mcd);
}//fin main

public static int euclides(int n, int m){
int resto, resultado, mcd;
resto=m%n;
if(n>=m){
System.out.println("El primer número debe menot que el primero");
}
else{
if(resto==0){
mcd=resto;
}
else{
m=n;
n=resto;
mcd=euclides(m%n);
}
return mcd;
}//fin euclides
}//fin class
}
