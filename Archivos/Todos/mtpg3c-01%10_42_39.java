import java.io.*;
class euclides{
public static void main (String[]args)throws IOException{
BufferedReader leer=new BufferedReader (new InputStreamReader (System.in));
int num1;
int num2;
int min=1,max=2;
System.out.println ("Introduzca el primer numero: ");
num1=Integer.parseInt (leer.readLine());
System.out.println ("Introduzca el segundo numero: ");
num2=Integer.parseInt (leer.readLine());
if(num1<num2){
int Aux=num1;
num1=num2;
num2=Aux;
}
System.out.println("El maximo comun divisor es: " +euclides(num2,num1));
}
public static int euclides(int num2, int num1){
int resto,valor;
resto=num1%num2;
if(resto==0){
valor=num2;
}
else{
valor=euclides(resto,num2);
}
return valor;
}
}
