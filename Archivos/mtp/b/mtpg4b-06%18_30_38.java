class fibonnacci {

public static void main (String [] args) {
int termino, n;
n=Integer.parseInt (args [0]);

termino=calcular_termino (n);
imprimir_termino (termino);
}
public static int calcular_termino (int n) {
if (n<=2) {
if (n==0) {
n=0;
}
else {
n=1;
}
}
else {
n=calcular_termino (n-1) + calcular_termino (n-2);
}
return n;
}

public static void imprimir_termino (int termino) {
System.out.println ("El termino es: "+termino);
}
}
