import java.io.*;
import java.util.Vector;
public class Primos{

public static void main(String[]args)throws IOException{

int primero, segundo;
Vector resultado;
BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
System.out.print("Introduce el primer numero: ");
primero=Integer.parseInt(br.readLine());
System.out.print("Introduce el segundo numero: ");
segundo=Integer.parseInt(br.readLine());
resultado=Primos(primero, segundo);
System.out.println(resultado.toString());

}
public static Vector Primos(int n, int m){

Vector aux=null;
for(int i=n; i<=m; i++){
if(primo (i)==true)
aux.add(i);
}
return aux;
}
public static boolean primo(int num) {
boolean p = false;
if (num < 4)
p = true;
else {
if (num % 2 == 0)
p = false;
else {
int contador = 0;
int i = 1;
int limite = (num - 1) / 2;
if (limite % 2 == 0)
limite--;

while(i <= limite) {
if (num % i == 0)
contador++;
i += 2;
if (contador == 2)
i = limite + 1;
}

if (contador == 1)
p = true;
}
}
return p;
} //fin metodo primo
}
