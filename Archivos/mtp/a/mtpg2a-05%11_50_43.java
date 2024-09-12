import java.io.*;

class palindromo
{
public static void main(String args[])
{
BufferedReader leer=new BufferedReader(
new InputStreamReader(System.in));
String cadena="";

System.out.print("Introduce frase: ");
try {
cadena=leer.readLine();
} catch (Exception e) {};
//----calculo palndroma
int n=cadena.length();
int i=0,j=n-1;
boolean palindroma=true;
while(i<j && palindroma)
if(cadena.charAt(i)!=cadena.charAt(j))
palindroma=false;
if(palindroma)
System.out.println("La frase es palindroma");
else
System.out.println("La frase no es palindroma");
}
}
