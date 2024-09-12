import java.io.*;
class MCD{
public static void main(String[]args)throws IOException{
BufferedReader leer=new BufferedReader (new InputStreamReader(System.in));
int n,m,result,aux;
System.out.print("Introduce un numero entero: ");
n=Integer.parseInt(leer.readLine());
System.out.print("Introduce un numero entero: ");
m=Integer.parseInt(leer.readLine());
if (n<m)
result=calcular(n,m);
else{
aux=n;
n=m;
m=aux;
result=calcular(n,m);
}
System.out.println("El MCD de los numeros "+n+" y "+m+" es: "+result);
}
public static int calcular(int n, int m){
int valor,resto;
resto=m%n;
if (resto==0)//caso base
valor=n;
else//caso inductivo
valor= calcular(resto,n);
return valor;
}
}
