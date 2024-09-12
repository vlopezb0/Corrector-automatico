import java.io.*;

class problema{
public static void main(String []args)throws IOException{
BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));
int [] numeros = new int[10];

for(int i=0;i<10;i++){
System.out.println("Introduzca un numero");
numeros[i]=Integer.parseInt(leer.readLine());
}

ordenar(numeros);
int media=media(numeros);
int mediana=mediana(numeros);

System.out.print("Numeros: [");
for(int j=0;j<numeros.length;j++){
System.out.print(numeros[j]+",");
}
System.out.print("]\n Media: "+media+"\n Mediana: "+mediana);
}

public static void ordenar(int [] a) {
for (int i=1; i<a.length;i++) {
for(int j=0;j<a.length-1;j++){
if (a[j]>a[j+1]){

int temp=a[j];
a[j]=a[j+1];
a[j+1]=temp;

}
}
}
} /* fin del mtodo de ordenacion */

public static int media(int [] a){
int suma=0;
for(int i=0;i<a.length;i++){
suma=suma+a[i];
}
return suma/a.length;
}

public static int mediana(int [] a){
int mediana=0;
int aux=a.length/2;
if(a.length%2 == 0){
mediana = (a[aux] + a[aux+1] ) / 2;
}
else{
mediana = a[aux];
}
return mediana;
}

}
