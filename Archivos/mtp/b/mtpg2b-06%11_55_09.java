import java.io.*;
class fibo
{
static BufferedReader leer=new BufferedReader (new InputStreamReader (System.in));
public static void main(String[]args)throws IOException
{
int n;
do
{
System.out.println("Introduzca un numero");
n=Integer.parseInt(leer.readLine());
if(n<0)
{
System.out.println("No se pueden introducir numeros negativos");
}
}
while(n<0);
Fibo(n);
int resultado=Fibo(n);
System.out.println("El fibonacci de "+n+" es "+resultado);
}//fin del main
public static int Fibo(int n)
{
int resultado;
if(n==0||n==1)//caso base
{
if(n==0)
resultado=0;
else
resultado=1;
}
else//caso inductivo o recursivo
resultado=Fibo(n-1)+Fibo(n-2);
return resultado;
}//fin de Fibo
}//fin del class

