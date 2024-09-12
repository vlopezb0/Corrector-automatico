import java.io.*;
class Fibonacci{
public static void main(String[]args)throws IOException{
BufferedReader leer=new BufferedReader(new InputStreamReader(System.in));
int n, resul;
System.out.println("Introduzca el término a calcular");
n=Integer.parseInt(leer.readLine());
resul=recursividad(n);
System.out.println("El resultado es: "+resul);

}// fin del main
public static int recursividad (int n){
int resul;
if(n==1){
resul=0;
}
else{
if(n==2){
resul=1;
}
else{
resul=recursividad(n-1)+recursividad(n-2);
}
}
return resul;
}//fin metodo

}//fin del class
