import java.io.*;

class Ejemplo {

public static void main(String [] args) throws IOException {
int anNumeros[] = new int[10];
BufferedReader leer=new BufferedReader(new InputStreamReader(System.in));
System.out.println("Introduce 10 numeros por teclado");
for (int i=0; i<10; i++) {
System.out.print("Introduzca el " + (i+1) + " numero: ");
anNumeros[i] = Integer.parseInt(leer.readLine());
}
System.out.println();
ordenar(anNumeros);
System.out.println("La matriz ordenada:");
for (int i=0; i<10; i++) {
System.out.print(" "+anNumeros[i]);
}
System.out.println();
System.out.print("La media es: ");
System.out.println(calcularMedia(anNumeros));
System.out.print("La mediana es: ");
System.out.println(calcularMediana(anNumeros));
} /* Fin mtodo main */

public static int calcularMedia(int [] a) {
int sumatorio=0;
for(int i=0;i<10;i++){
sumatorio+=a[i];
}
return sumatorio/10;
} /* Fin mtodo calcularMedia */

public static int calcularMediana(int [] a) {
int n1=0, n2=0;
n1=a[4];
n2=a[5];
return (n1+n2)/2;
} /* Fin mtodo calcularMediana */

public static void ordenar(int [] a) {
for (int i=1; i<a.length;i++) {
for(int j=0;j<a.length-1;j++){
if (a[j]>a[j+1]){
int temp=a[j];
a[j]=a[j+1];
a[j+1]=temp;
}
}//fin del for2
} //fin del for1
} /* Fin del mtodo ordenar */

} /* Fin clase Ejemplo */


