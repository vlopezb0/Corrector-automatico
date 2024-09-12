import java.io.*;
class Palindromo{
public static void main(String [] args)throws IOException{
BufferedReader leer=new BufferedReader(new InputStreamReader(System.in));
String palindromo;
System.out.println("Introduzca la cadena");
palindromo=leer.readLine();
if(recursivo(palindromo,0,palindromo.length()-1)){
System.out.println("Es palindromo");
}
else{
System.out.println("No es palindromo");
}
}

public static boolean recursivo(String palabra,int prin,int fin){
boolean resul;
if(fin==prin && prin<=fin){
resul=true;
}
else{
if(palabra.charAt(prin)==palabra.charAt(fin)){
resul=recursivo(palabra,prin+1,fin-1);
}
else{
resul=false;
}
}
return resul;
}
}//cierra la clase
