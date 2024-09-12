import java.io.*;
class Euclides {
public static void main (String [] args) throws IOException {
BufferedReader leer=new BufferedReader
(new InputStreamReader (System.in));

int m, n, aux, resto, max;
m=Integer.parseInt (leer.readLine ());
n=Integer.parseInt (leer.readLine ());
if (m<n) {
aux=m;
m=n;
n=aux;
}
max=calcular_max (m,n);
System.out.println ("El M.C.D. de: "+m+" y "+n+" es: "+max);
}
public static int calcular_max (int m, int n) {
int resto,max;
resto=m%n;
if (resto==0) {
max=n;
}
else {
m=n;
n=resto;
max=calcular_max (m,n);
}
return max;
}
}
