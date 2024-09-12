import java.io.*;
class Fibonacci{
public static void main (String [] args)throws IOException{
BufferedReader leer=new BufferedReader (new InputStreamReader(System.in));
int n;
do {
System.out.println ("Introduce numero n de la sucesion");
n=Integer.parseInt(leer.readLine());
if(n<1){
System.out.println ("Tienes que introducir un numero mayor que 1");
}
} while (n<1);
fibonacci(n);
}
public static void fibonacci (int n) {
int a=0, b=1, c=1; valor;
if (n==1){
valor= 1;
}
else{
algoritmo(n);
}

public static void algoritmo(int n){

int a =n-1;
int b=n-2;
int n=a+b;

a=b;
b=valor;

}
}
