import java.io.*;
class Palindromo{
public static void main (String [] args) throws IOException{
BufferedReader leer = new BufferedReader
(new InputStreamreader (System.in);
String palabra;
int tamao;
boolean sn;
System.out.println("Introduzca palabra: ");
palabra=leer.readLine();
tamao=palabra.length;
sn=s(palabra,tamao);
if (sn=true){
System.out.println("ES PALINDROMO");
}
else{
System.out.println("NO ES PALINDROMO");
}
}
public static boolean s(String palabra,int tamao){

