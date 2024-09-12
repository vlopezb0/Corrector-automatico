import java.io.*;
class euclides{
static BufferedReader leer= new BufferedReader(new InputStreamReader(System.in));
public static void main(String [] args) throws IOException{
int m,n,mcd;
System.out.print("Introduzca primer numero: ");
m=Integer.parseInt(leer.readLine());
System.out.print("Introduzca segundo numero: ");
n=Integer.parseInt(leer.readLine());
System.out.println();

//Ahora ordenamos los valores, porque tiene que ser n<m
if (n>m){
int aux=m; //Variable auxiliar
m=n;
n=aux;
}else{
System.out.println(euclides(n,m));
}//fin if-else
}//Fin main

public static int euclides(int n, int m){
int resto, valor;
resto=m%n;
if (resto==0){
valor=n;
}else{
valor=euclides(resto,n);
}//Fin else
return valor;
} //Fin euclides
} //Fin class




