import java.io.*;
class Fibonacci{
public static void main(String[]args)throws IOException{
BufferedReader leer=new BufferedReader(new InputStreamReader(System.in));
System.out.println("Introduca el valor maximo al que va a llegar la serie Fibonacci");
int n=Integer.parseInt(leer.readLine());
int resultado=Fibonacci(n);
System.out.println("La serie Fibonacci hasta el numero "+n+" es: "+resultado);
}

public static int Fibonacci(int n){
int resultado;
if(n==0){
resultado=0;
}else{
if(n==1){
resultado=1;
}else{
resultado=Fibonacci(n-1) + Fibonacci(n-2);
}//else
}//else
return resultado;
}
}






