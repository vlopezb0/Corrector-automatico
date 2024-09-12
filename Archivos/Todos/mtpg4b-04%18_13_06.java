import java.io.*;
class fibonacci {
public static void main (String [] args) throws IOException {
int n;
String serie;
BufferedReader leer=new BufferedReader(new InputStreamReader(System.in));
System.out.println("Introduzca el termino n de la serie de Fibonacci");
n=Integer.parseInt(leer.readLine());
serie=fibonacci(n);
System.out.println("La sucesion es"+" "+serie);
}
public static String fibonacci(int n, String serie)throws IOException {
if (n==0){
serie=0;
if (n==1) {
serie=1;

}
}
else {
if (n>1) {
serie=fibonacci(n-1)+fibonacci(n-2);
}
}
return serie;
}
}


