import java.io.*;

class Euclides {
public static void main(String [] args)throws IOException{

int m, n;
BufferedReader leer=new BufferedReader(new InputStreamReader(System.in));

System.out.println("Introduce el primer numero");
m=Integer.parseInt(leer.readLine());
System.out.println("Introduce el segundo numero");
n=Integer.parseInt(leer.readLine());
System.out.println("El mcd de los numeros"+m+" y "+n+" es:");

metodo1(m, n);
}

public static void metodo1(int m, int n){
int resto=m%n;
if (resto==0){

System.out.println(n);
}
else {
metodo2(m, n, resto);
}
return;
}
public static void metodo2 (int m, int n, int resto) {
m=n;
n=resto;
metodo1(m, n);
return;
}
}
