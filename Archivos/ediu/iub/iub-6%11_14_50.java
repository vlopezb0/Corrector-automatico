import java.io.*;

class Primos {
public static void main (String [] args) throws Exception {
int m,n;
BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
System.out.println("Desde:");
n= Integer.parseInt(teclado.readLine());
System.out.println("Hasta:");
m= Integer.parseInt(teclado.readLine());
for (int i=n;i<=m;i++) {
if (esPrimo(i)) System.out.println(i+" ");
}
}

public static boolean esPrimo (int i){
if (i>=0 && i<=3) return true;
for (int num=2; num<=i/2; num++){
if (i%num==0) return false;
}
return true;
}

}
