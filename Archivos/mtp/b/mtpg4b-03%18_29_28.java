import java.io.*
class Fibonacci {
public static void main (String [] args) throws IOException {
BufferedReader leer = new BufferedReader
(new InputStreamReader (System.in));
int n, serie;
System.out.println ("Introduce el valor de n");
n=Integer.parseInt(leer.readLine());
serie=r(n);
if (serie!=-1){
System.out.println(serie);
}
else{
System.out.println("esta mal");
} 
} // Fin del main
public static int r (int n) {
int fibonacci;
if (n>=1) {
if (n=1) {
fibonacci=1;
}
else {
fibonacci=fibonacci(n-1)+fibonacci(n-2);
}
}
else {
System.out.println("El numero tiene que ser mayor que 1");
fibonacci=-1;
}
return fibonacci;
} //Fin metodo recursivo.
} //Fin clase.

