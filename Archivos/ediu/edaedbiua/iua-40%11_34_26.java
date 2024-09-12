import java.io.*;

class Problema{
public static void main(String []args)throws IOException{
BufferedReader leer = new BufferedReader (
new InputStreamReader (System.in));
int [] numeros = new int [10];
int i;
System.out.println("Introduzca los nmeros");
i=0;
while(i<10){
System.out.println("Introduzca nmero");
try{
numeros[i] = Integer.parseInt(leer.readLine());
i++;
}
catch(Exception e){
System.out.println("Debe introducir un entero");
}
}

//Ahora pasamos a ordenarlos
ordenar(numeros);
System.out.print("Nmeros ordenados:");
for(i=0;i<10;i++) System.out.print(" "+numeros[i]);
System.out.println();

// Calculamos la media
System.out.println ("La media es: " + media(numeros)+"\n");
// Calculamos la mediana
System.out.println("La mediana es: "+ mediana(numeros));
}
public static void ordenar(int [] a){
for (int i=1; i<a.length;i++) {
 for(int j=0;j<a.length-1;j++){
 if (a[j]>a[j+1]){
 int temp=a[j];
 a[j]=a[j+1];
 a[j+1]=temp;
 }
 }
 }
}

public static double media(int [] numeros){
double suma = 0;

for (int i=0; i<10; i++) {
suma += numeros [i];
}
return (suma/10.0);
}

public static double mediana(int [] numeros){
return ((numeros[4]+numeros[5])/2.0);
}
}
