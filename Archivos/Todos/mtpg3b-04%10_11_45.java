import java.io.*;
class ejercicio45{
public static void main(String[]args)throws IOException{
int termino;
BufferedReader leer=new BufferedReader(new InputStreamReader(System.in));
termino=Integer.parseInt(leer.readLine());
System.out.println("El termino seleccionado es: "+termino);
//caso base
if(termino<1){
termino=casobase(termino);
//caso inductivo
}else{
termino=inductivo(termino);
}
System.out.println("termino: "+termino);
}//fin del main
static int casobase(int termino){
termino=1;
return termino;
}//fin del metodo casobase
static int inductivo(int termino){
termino=(termino-1)+(termino-2);
return termino;
}//fin del metodo casonobase
}//fin de la clase



