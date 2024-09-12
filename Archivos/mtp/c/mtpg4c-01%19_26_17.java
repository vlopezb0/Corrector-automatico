import java.io.*;
class euclides {
public static void main (String [] args) throws IOException {
int n, m, mcd;

BufferedReader leer =new BufferedReader (new InputStreamReader (System.in));

System.out.println ("Introduzca primer numero:");
n=Integer.parseInt (leer.readLine ());

System.out.println ("Introduzca segundo numero:");
m=Integer.parseInt (leer.readLine ());
System.out.println ();

if (n<m) {
int aux=m;
m=n;
n=aux;
}
System.out.println (euclides (n, m));

}//Fin del metodo main

public static int euclides (int n, int m) {
int resto, valor;
resto =m%n;
if (resto==0) {
valor=n;
}
else {
valor =euclides (resto, n);
}
return valor;

}//Fin del metodo euclides
}//Fin de la clase

