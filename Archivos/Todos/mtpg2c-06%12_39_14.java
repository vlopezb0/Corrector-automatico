import java.io.*;
class eucli
{
static BufferedReader leer=new BufferedReader(new InputStreamReader (System.in));
public static void main(String[]args)throws IOException
{
int n,m;
do
{
System.out.println("Introduzca un numero");
n=Integer.parseInt(leer.readLine());
System.out.println("Introduzca otro numero");
m=Integer.parseInt(leer.readLine());
if(n<0||m<0)
System.out.println("No se pueden introducir numeros negativos");
}
while(n<0||m<0);
eucli(n,m);
int resultado=eucli(n,m);
System.out.println("El maximo comun divisor de "+n+" y "+m+" es "+resultado);
}//fin del main
public static int eucli(int n, int m)
{
int resul,resto;
resto=m%n;
if(resto==0)//caso base
resul=n;
else
{
m=n;
n=resto;
resul=eucli(n,m);
}
return resul;
}//fin de eucli
}//fin del class

