import java.io.*;
class Divisor {
public static void main (String [] args) throws IOException {
int m, n, mcd;
BufferedReader leer = new BufferedReader
(new InputStreamReader(System.in));
System.out.println("Introduzca primer numero: ");
m=Integer.parseInt(leer.readLine());
System.out.println("Introduzca segundo numero: ");
n=Integer.parseInt(leer.readLine());
System.out.println();

if (m<n) {
int aux=m;
m=n;
n=aux;
}
System.out.println(euclides(n,m));
}
public static int euclides(int n, int m) {
int resto, valor;
resto=m%n;
if (resto==0) {
valor=n;
}
else {
valor = euclides(resto, n);
}
return valor;
}
}










