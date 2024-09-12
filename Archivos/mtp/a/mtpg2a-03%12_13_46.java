import java.io.*;

public class palindromo{

static BufferedReader leer= new BufferedReader(new InputStreamReader(System.in));

public static void main(String[] args) throws IOException{
String oracion;
System.out.println("Introduzca una palabra o frase para saber si es un palindromo");
oracion=leer.readLine();
if(palindromo(oracion)){
System.out.println("La frase que ha introducido es un palindromo.");

}else{
System.out.print("La frase que ha introducido no es un palindromo");
}
}//fin del main

//metodo recursivo para saber si una frase es un palindromo
public static boolean palindromo(String frase){
int inicio=0;
int ultimo=frase.length()-1;
if(inicio == ultimo){
return true;
}else{
if(frase.charAt(inicio)==frase.charAt(ultimo)){
inicio++;
ultimo--;
palindromo(frase);

}
else
{
return false;
}
}
}//fin metodo
}//fin clase



