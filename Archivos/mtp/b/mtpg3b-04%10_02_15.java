import java.io.*;
import java.lang.*;
class ejercicio{
public static void main(String[]args)throws IOException{
int termino;
int numero;;
BufferedReader leer=new BufferedReader(new InputStreamReader(System.in));
termino=Integer.parseInt(leer.readLine());
System.out.println("El termino seleccionado es: "+termino);
//caso base
if(termino<1){
numero=1;
//caso inductivo
}else{
termino=(termino-1)+(termino-2);
}
System.out.println("termino: "+termino);
}
}



