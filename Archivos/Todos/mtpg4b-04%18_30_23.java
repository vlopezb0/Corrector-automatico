import java.io.*;
class Fibonacci {
public static void main (String [] args) throws IOException {
int n, serie;
BufferedReader leer=new BufferedReader(new InputStreamReader(System.in));
System.out.println("Introduzca el termino n de la serie de Fibonacci");
n=Integer.parseInt(leer.readLine());
serie=fibonacci(n);
System.out.println("La sucesion es"+" "+serie);
}
public static int fibonacci(int n)throws IOException {
int serie=0;
if (n==0){
serie=0;
}
else {
if (n==1) {
serie=1;
}
else {
if (n>1) {
serie=fibonacci(n-1)+fibonacci(n-2);
}
}
}
return serie;
}
}


