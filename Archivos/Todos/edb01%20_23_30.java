import java.io.*;
import java.util.Vector;

public class Ordena_Diez{

public static void main(String args[])throws IOException{
int numero, opcion=0;

int numeros[];
numeros=new int[10];
BufferedReader leer=new BufferedReader(new InputStreamReader(System.in));
for (int i=0; i<10;i++){
System.out.println("Introduzca un numero: ");
numeros[i]=Integer.parseInt(leer.readLine());
}
while(opcion!=4){
System.out.println();
System.out.println ("Que operacion desea realizar: ");
System.out.println ("1.-Ordenacion del array: ");
System.out.println ("2.-Hacerle la media");
System.out.println ("3.-Hacerle la mediana");
System.out.println ("4.-Salir del programa");
opcion=Integer.parseInt(leer.readLine());
switch(opcion){
case 1:
bubble(numeros);
System.out.println("El array ordenado quedara:");
for (int i=0; i<10;i++) {
System.out.print(numeros[i]+" ");
}
System.out.println();
break;
case 2:
media(numeros);
break;
case 3:
mediana(numeros);
break;
default:
System.out.println("Opcion incorrecta");
break;
}
}

}
public static void bubble(int numeros[]){
 for (int i=1; i<10;i++) {
 for(int j=0;j<9;j++){
 if (numeros[j] > numeros[j+1]){
 int temp = numeros[j];
 numeros[j]= numeros[j+1];
 numeros[j+1]= temp;
 }
 }
 }
} /* fin del mtodo bubble */


public static void media(int numeros[]){
int suma=0;
double media=0.0;
for(int j=0;j<10;j++){
suma=suma+numeros[j];
}
media=suma/10.0;
System.out.println("La media es: "+media);
}

public static void mediana(int numeros[]){
bubble(numeros);
double mediana=0.0;
mediana=(numeros[4]+numeros[5])/2.0;
System.out.println("La mediana es: "+mediana);
}
}

