import java.io.*;
class Problema{
public static void main (String [] Args) throws IOException{
BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));
int [] n = new int[10];
for(int i=0;i<10;i++){
System.out.println("Introduzca numero "+i);
n[i]=Integer.parseInt(leer.readLine());
}
System.out.println("Numeros insertados:\n");
for(int j=0;j<10;j++){
System.out.println(n[j]+"\t");
}
System.out.println();
System.out.println("Numeros ordenados:\n");
for(int i=0;i<10;i++){
int k= i;
 for (int j= i+1; j<10; j++) {
 if (n[j]<n[k]) k= j;
 }
 // intercambiamos n[i] con n[k]
 int aux= n[i];
 n[i]= n[k];
 n[k]= aux;
 }
int suma = 0;
for(int j=0;j<10;j++){
System.out.println(n[j]+"\t");
suma+= n[j];
}
/*Media*/
System.out.println("Media ="+suma/10);
/*Mediana*/
int mediana= (n[4]+n[5])/2;
System.out.println("Mediana"+ mediana);


}

}

