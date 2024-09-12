import java.io.*;
class Euclides{
public static void main(String []args)throws IOException{

BufferedReader leer= new BufferedReader (new InputStreamReader(System.in));
int n,m,aux,resto,resultado;
do{
System.out.println("Introduce m");
m=Integer.parseInt(leer.readLine());
}while(m<=0);
do{
System.out.println("Introduce n");
n=Integer.parseInt(leer.readLine());
}while(n<=0);
if(n>m){
System.out.println("m debe ser mayor que n, intercambiamos valores.");
aux=n;
n=m;
m=aux;
}
resto=m%n;
resultado=mcd(m,n,resto);

System.out.println("el resultado es "+resultado);

}//fin del main

public static int mcd(int m, int n, int resto){
int resultado;
if(resto==0){
resultado=n;
}
else{
m=n;
n=resto;
resto=m%n;
resultado=mcd(m,n,resto);
}
return resultado;
}//fin del metodo

}//fin de la clase
