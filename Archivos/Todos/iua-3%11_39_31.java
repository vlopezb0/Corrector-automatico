import java.io.*;
class ejemplo{

public static void ordenacion(int [] sinOrdenar){
for (int i=1; i<sinOrdenar.length;i++) {
 for(int j=0;j<sinOrdenar.length-1;j++){
 if (sinOrdenar[j]>sinOrdenar[j+1]){
 int temp=sinOrdenar[j];
 sinOrdenar[j]=sinOrdenar[j+1];
 sinOrdenar[j+1]=temp;
 } // end_if
} // end_for
} // end_for
} // fin ordenacion

public static double media(int [] m){
double resultado=0;
for(int i=0; i<m.length; i++){
resultado+=m[i];
}
resultado=resultado/m.length;
return resultado;
}

public static int mediana(int [] m){
int mediana=(int)(m.length/2)-1;
return m[mediana];
}

public static void main (String [] args) throws IOException{
BufferedReader leer = new BufferedReader (new InputStreamReader (System.in));
int i;
int [] numeros = new int [10];
for (i = 0; i<10; i++){
System.out.print("Dame un nmero: ");
numeros [i] = Integer.parseInt (leer.readLine());
System.out.println();
}
ordenacion(numeros);
System.out.println("\n\t>> Su media es: "+media(numeros));
System.out.println("\n\t>> Su mediana es: "+mediana(numeros));
System.out.print ("La matriz ordenada es: ");
for (i = 0; i<10; i++){
System.out.print(" "+numeros[i]);
}
} //end_main

} //end_class
