import java.io.*;

class PalindromoRecursivo{

public static void main (String[]args)throws IOException{

BufferedReader leer= new BufferedReader(new InputStreamReader(System.in));

System.out.println("Introduzca una palabra");
String cadena= leer.readLine();

if (palindromo(cadena)){

System.out.println("La palabra es un palindromo");

}//fin if

else{

System.out.println("La palabra no es un palindromo");
}//fin else
}//fin main
static boolean palindromo (String cadena){

boolean palin= false;

if (cadena.length()<=1){

palin=true;
}//fin if


else{
if(cadena.charAt(0)==cadena.charAt(cadena.length()-1)){

palin= palindromo (cadena.substring(1,cadena.length()-1));

}//fin del if

}//fin del else
return palin;
}//fin de palindromo

}//fin class palindromorecursivo
