import java.io.*;
class Fibonnaci{
public static void main(String[]args)throws IOException{
BufferedReader leer=new BufferedReader(new InputStreamReader(System.in));
int numero,resul;
System.out.print("Introduce un entero: ");
numero=Integer.parseInt(leer.readLine());
resul=fibonnaci(numero);
System.out.println("Resultado: "+resul);
}
public static int fibonnaci(int num){
int valor;
if (num > 1){//caso inductivo
valor=(fibonnaci(num-1)+fibonnaci(num-2));
}
else{//caso base
valor=1;
}
return valor;
}
}
