 import java.io.*;
class Euclides {
public static void main (String [] args) throws IOException {
int m,n;
BufferedReader leer=new BufferedReader(new InputStreamReader(System.in));
System.out.println("Introduzca el primer numero");
m=Integer.parseInt(leer.readLine());
System.out.println("Introduzca el segundo numero");
n=Integer.parseInt(leer.readLine());
System.out.println();

if (n<m) {
int aux=n;
n=m;
m=aux;
}
System.out.println("El maximo comun divisor de " +m+ " y "+n+ " es " +euclides(n,m));
}

public static int euclides (int n, int m) {
int resto, valor;
resto=m%n;

if (resto==0) {
valor=n;
}
else {
valor=euclides(resto,n);
}

return valor;
}
}

