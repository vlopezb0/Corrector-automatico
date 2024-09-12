import java.io.*;
class ordenar{
public static void main (String [] args)throws IOException{
BufferedReader leer= new BufferedReader(new InputStreamReader(System.in));
int [] numeros = new int [10];
double media=0;
int mediana=0;
for (int i=0;i<10;i++){
System.out.println("introduce nmero... "+i);
numeros[i]=Integer.parseInt(leer.readLine());
}
for(int i = 0; i < 10; i++) {
int min = i; // lleva el ndice del menor elemento
// encuentra el menor entre i y el final del array
for(int j = i; j < 10; j++) {
if (numeros[j] < numeros[min]) min = j;
}
// Intercambia el menor con el i.
// Deja los elementos entre 0 e i ordenados.
int tmp;
tmp = numeros[i];
numeros[i] = numeros[min];
numeros[min] = tmp;
}
for(int i=0;i<10;i++){
System.out.print(numeros[i]+" ");
}
System.out.println("");
for(int i=0;i<10;i++){
media=media+numeros[i];
}
System.out.println("media= "+media/10);

System.out.println("mediana= "+numeros[5]);


}// fin main
}// fin clase



