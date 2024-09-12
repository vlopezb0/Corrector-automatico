import java.io.*;
class Euclides{
public static void main (String [] args)throws IOException{
BufferedReader leer=new BufferedReader(new InputStreamReader (System.in));
int n,m;
do{System.out.println("Introduzca un numero n ");
n=Integer.parseInt(leer.readLine());
System.out.println("Introduzca otro numero m ");
m=Integer.parseInt(leer.readLine());
if (n<m||m<0){
System.out.println("Introduzca otros numeros correctos");
}
}while (n<m);
euclides (n,m);
}//fin del metodo main
public static int euclides(int n,int m){
int resultado,resto;
resto=m%n;
resultado =0;
if (resto==0){
System.out.println("El numero "+n+ "es maximo comun divisor");
}else {
m=n;
n=resto;
resultado=euclides(n,m);
}
return resultado;
}

}
