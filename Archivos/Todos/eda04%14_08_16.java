import java.io.*;
class Problema{
public static void main(String args []) throws IOException{
BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));
int [] vector=new int[10];
int i,j, media=0;
int aux;

for (i=0; i<10; i++){
System.out.println("escribe un numero");
vector[i]=Integer.parseInt(leer.readLine());
}

for (j=9; j>=10; j--){
for(i=0; i<j; i++){

if(vector[i] < vector[i+1]){
aux=vector[i+1];
vector[i+1]=vector[i];
vector[i]=aux;
}

}
}
System.out.println("Los diez numeros son: ");
for (i=0; i<10; i++){
System.out.print(+vector[i] );
media+=vector[i];
}
media/=10;
System.out.println("la media es: "+media);
System.out.println("la mediana es: "+vector[5]);
}

}
