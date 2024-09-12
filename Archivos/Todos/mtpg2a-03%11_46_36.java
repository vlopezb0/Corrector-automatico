import java.io.*;

class Palindromo{

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
int final=0;
final=frase.length()-1;
if(incio == final){
return true;
}else{
if(frase.charAt(inicio)=frase.charAt(final)){
inicio++;
final--;
palindromo(frase)
}else{
return false;
}
}
}//fin metodo
}fin clase


