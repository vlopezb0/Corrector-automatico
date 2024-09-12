import java.io.*;

class Euclides {
public static void main(String [] args){

int m, n, mcd;
double division;
BuffereReader leer=new BufferedReader(new InputStreamReader(System.in());

System.out.println("Introduce el primer numero");
m=Integer.ParseInt(leer.readLine();
System.out.println("Introduce el segundo numero");
n=Integer.ParseInt(leer.readLine();


metodo1(m, n);
}

public static metodo1(int m, int n){
double resto=m%n;
if (resto==0){
System.out.println("El mcd de los numeros"+m+" y "+n" es:";
System.out.println(n);
}
else {
metodo2(m, n, resto);
}
}
public static void metodo2 (int m, int n, double resto) {
m=n;
n=resto;
metodo1();
}
}

}

}
