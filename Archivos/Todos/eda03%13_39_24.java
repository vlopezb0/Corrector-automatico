import java.io.*;
class problema{
public static void main(String[]args)throws IOException{
BufferedReader leer=new BufferedReader(new InputStreamReader(System.in));
int media=0;
int mediana=0;
int aux = 0;
int [] matriz = new int [10];
System.out.println("Introduce los numeros:");
for(int i=0; i<=9;i++){
System.out.print("Numero "+i+":");
matriz[i]=Integer.parseInt(leer.readLine());
}
for(int j=9;j>=0;j--){
for(int i = 0; i<j; i++){
if(matriz[i]<matriz[i+1]){
aux=matriz[i+1];
matriz[i+1]=matriz[i];
matriz[i]=aux;
}
}
}
System.out.println("Numeros ordenados: ");
for(int i=0;i<=9;i++){
media+=matriz[i];
System.out.print(matriz[i]+" ");
}
media=(media/10);
System.out.println("\nMedia= "+media);
mediana=matriz[4];
System.out.println("\nMediana= "+mediana);
}
}

