import java.io.*;
class Palindromo{

static BufferedReader leer=new BufferedReader (new Input SreamReader (System.in));

public static void main (String []args) throws IOException{
string caden;
int longuitud, inicio=0;
boolean iguales;
System.out.println("Introduzca cadena");
caden=leer.readLine();
longuitud=caden.leng();
iguales=comprobar (caden, longuitud, inicio);
if (iguales){
System.out.println("La cadena es un palindromo");
}
else{
System.out.println("La cadena no es un palindromo");
}
}
public static boolean comprobar (String caden, int longuitud, int inicio){
boolean iguales=false;

if( inicio>= longuitud){
return iguales;
}
else{
if(caden.charAt(inicio)!=caden.charAt(longuitud){
comprobar (subString(inicio+1, longuitud-1)
