import java.io.*;
class fibonacci{
public static void main(String []args) throws IOException{
int n,resultado;
BufferedReader leer=new BufferedReader (new InputStreamReader (System.in)) ;
System.out.println("Introduzca n para calcular serie de Fibanacci");
n=Integer.parseInt(leer.readLine());
resultado=recursividad(n);
System.out.println("El resultado es igual a: "+resultado);
}//fin del main
public static int recursividad (int n){
int resultado;
if(n==0){
resultado=0;
}
else{
if(n==1){
resultado=1;
}
else{
resultado=recursividad(n-1)+recursividad(n-2);
}
}
return resultado;
}//fin de recursividad
}//fin de la calse
