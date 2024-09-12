import.java.io*;
class Fibo{
public static void main (String[]args)throws IOException{
BufferedReader leer=new BufferedReader (new InputStreamReader (System.in));
int n,fibo;
System.out.println("introduzca n para calcular la serie");
n=Integer.parseInt (leer.readLine());
fibo=fibna(n);
if (n<0) System.out.println("el numero debe ser mayor o igual que 0");
else{
if(n=0) resultado=0;
else{
resultado=fibna (n);
}
System.out.println(resultado);
}
}
public static int fibna (int n){
int valor;
if (n=1){
valor=1;
else{
valor=fibna(n-1)+fibna(n-2);
}
return valor;
}
}




