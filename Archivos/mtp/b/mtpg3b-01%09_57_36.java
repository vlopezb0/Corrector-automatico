import java.io*;
class fibonaci{
public static void main (String[]args)throws IOException{
BufferedReader leer= new BufferedReader (new InputStreamReader (System.in));
int resultado,termino;
System.out.println("Introduzca el termino que quiere calcular de la serie de fibonacci: ");
termino=Integer.parseInt(leer.readLine());
resultado=fibonacci(termino);
System.out.println("El resultado de Fibonacci seria: "+resultado);

}
public static int fibonacci(int n){
int resultadoFib;
if(n==0){
resultadoFib=0;
}else{
if(n==1){
resultadoFib=1;
}else{
resultadoFib= fibonacci(termino-1)+fibonacci(termino-2);
}
}
return resultadoFib
}
}
