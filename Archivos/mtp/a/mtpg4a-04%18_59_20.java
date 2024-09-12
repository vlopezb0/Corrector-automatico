import java.io.*;
class Palindromo{
public static void main(String[]args)throws IOException{
String palabra;
BufferedReader leer=new BufferedReader(new InputStreamReader(System.in));
System.out.println("Introduzca la palabra que desea leer:");
palabra=leer.readLine();
}

public static void palindromo(String palabra) {
if (palabra.length()==1){
System.out.println("La palabra es palindromo");
}
else {
palabra=palindromo(subString(0,palabra.length()));
}
}
}


