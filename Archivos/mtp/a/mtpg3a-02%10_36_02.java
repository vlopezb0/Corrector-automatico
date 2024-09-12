import java.io.*;
class Palindromo{
static BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));
public static void main(String [] args)throws IOException{
System.out.print("Por favor, introduzca cadena: ");
String cadena=leer.readLine();
boolean encontrado=false;
if (cadena.length)> 15{
System.out.print("No se puede tratar la cadena");
}else{
System.out.print("Cadena correcta");
int longitud=cadena.length-1;
int prin=cadena.charAt(0);
int fin=cadena.charAt(longitud);
palindromo(cadena,prin,fin);
}
}//Fin main

public static String palindromo(String cadena, int prin,int fin){
boolean seguir;
if (prin==fin){
prin++;
fin --;
}//fin while








