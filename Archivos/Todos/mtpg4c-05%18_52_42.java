import java.io.*;
class Euclides{
public static void main(String[]args)throws IOException{
BufferedReader leer=new BufferedReader(new InputStreamReader(System.in));
int m,n;
int valor;
valor=0;
System.out.println("Introduzca el primer numero");
m=Integer.parseInt(leer.readLine());
System.out.println("Introduzca el segundo numero");
n=Integer.parseInt(leer.readLine());
valor=euclides(n,m);System.out.println("El resultado es: "+valor);
}

public static int euclides(int n,int m){
int valor=0;
int resto;
resto=m%n;
if(resto==0){
valor=n;
}
else{
m=n;
n=resto;
valor=euclides(n,m);
}
return valor;
}
}










