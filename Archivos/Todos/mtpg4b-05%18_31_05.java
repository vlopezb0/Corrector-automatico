import java.io.*;
class fibonacci{
public static void main(String[]args)throws IOException{
BufferedReader hola=new BufferedReader(new InputStreamReader(System.in));
int n;
n=0;
long suma;
System.out.println("Introduzca el numero");
n=Integer.parseInt(hola.readLine());
suma=fibonacci_recursivo(n);
System.out.println(suma);
}

static long fibonacci_recursivo(int n){
long suma;
if(n>1){
suma=(fibonacci_recursivo(n-1)+ fibonacci_recursivo(n-2));
}
else
suma=1;

return suma;
}
}


