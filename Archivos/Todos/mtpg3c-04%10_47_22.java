import java.io.*;
class ejercicio57{
public static void main(String[]args)throws IOException{
BufferedReader leer=new BufferedReader(new InputStreamReader(System.in));
int n;
int m;
double cociente=0;
double resto=0.1;
int a;
System.out.println("Introduzca el primer entero");
n=Integer.parseInt(leer.readLine());
System.out.println("Introduzca el segundo entero");
m=Integer.parseInt(leer.readLine());
while(resto!=0){
cociente=n/m;
a=(int)cociente;
resto=(cociente-a)*a;
//caso base
if(resto==0){
System.out.println(n+" es el maximo comun divisor");
//caso inductivo
}else{  
m=n;
n=(int)resto;
System.out.println("Como tu resto no es 0, entonces "+n+" y "+m+"no tienen maximo comun divisor");
}
}//fin del bucle
}//fin del main
}//fin de la clase
