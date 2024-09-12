import java.io.*;
class Euclides{
public static void main(String [] args)throws IOException{
BufferedReader teclado=new BufferedReader(new InputStreamReader(System.in);
int n, m, mcd;
System.out.println("Introduzca el primer entero: ");
n=Integer.parseInt(teclado.readLine());
System.out.println("Introduzca el segundo entero: ");
m=Integer.parseInt(teclado.readLine());
mcd=maxcomundivid(n,m);
System.out.println("El Maximo Comun Divisor es: "+mcd);
}//fin main
public static int maxcomundivid(int n, int m){
int resto, resultado;
resto=n%m;
if(resto==0){
resultado=n;
}
else{
m=n;
n=resto;
resultado=maxcomundivid(n,m);
}
return resultado;
}//fin maxcomundivid

}//fin class
