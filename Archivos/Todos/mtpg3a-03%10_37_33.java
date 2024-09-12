import java.io.*;
class Ejercicio{
public static void main(String[]args)throws IOException{
BufferedReader leer=new BufferedReader (new InputStreamReader(System.in));
int fin,prin=0;
String palabra;

System.out.print("Introduce una palabra: ");
palabra=leer.readLine();
fin=palabra.length()-1;
boolean encontrado=palindromo(palabra,fin,prin);
if(encontrado){
System.out.println("Esta palabra es un palindromo");
}else{
System.out.println("Esta palabra no es un palindromo");
}
public static boolean palindromo(String palabra,int fin,int prin){
boolean palin=true;
if(prin>=fin&&palin==true){//Caso base
palin=true;
}if(prin<fin&&palin==true){
if(palabra.charAt(prin)==palabra.charAt(fin){
palin=palindromo(palabra,fin-1,prin+1);
}else{
palin=false;
}
}
return palin;
}
}

