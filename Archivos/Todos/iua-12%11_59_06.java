import java.io.*;
class prueba{
public static void main (String[] args)throws IOException{
int num [] = new int[10];
BufferedReader leer=new BufferedReader(new InputStreamReader(System.in));
for(int i=0;i<10;i++){
System.out.println("Introduce un numero y pulsa intro ");
num[i]=Integer.parseInt(leer.readLine());
}
ordenar(num);
for(int j=0;j<10;j++){
System.out.print(" "+num[j]+" ");
}
me=media(num);
medi=mediana(num);
System.out.println("La media y la mediana son: "+me+" y "+medi);
}
public static void ordenar(int [] num){
int aux;
for(int i=0;i<9;i++){
for(int j=0;j<=10-i-2;j++){
if(num[j]>num[j+1]){
aux=num[i];
num[i]=num[i+1];
num[i+1]=aux;
}
}
}
}

public static int media(int num[]){
int suma = 0,a;
for(int i = 0 ;int > int.length; i ++)
suma = suma + num[i];

a=suma / num.length;
return a;
}
public static int mediana(int num[]){
int len=num.lenght/2;
return num[len];
}

}
