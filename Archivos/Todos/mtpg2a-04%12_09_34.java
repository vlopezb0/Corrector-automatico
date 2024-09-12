class palindromo
{ public static void main(String args[])
{ String cadena;
int resultado;
cadena = args[0];
resultado=palindromos (cadena,0, cadena.length()-1 ,0);
if (resultado == 1)
System.out.print("La frase ES un palindromo");
else
System.out.print("La frase NO ES un palindromo");
}
public static int palindromos(String cadena, int ini, int fin, int contador)
{ int devolver=0;
if (ini >= fin) //ya no hay nada que comprobar
devolver=1; //y Es un palindromo
else
{ if (cadena.charAt(ini) != cadena.charAt(fin))
devolver=-1;
// los caractares son distintos y no es un palindromo
}
if (devolver ==0)
devolver=palindromos (cadena, ++ini, --fin, ++contador);
// comprobar siguientes letras del posible palindromo
return devolver;
// Devolver el dato de las posteriores llamadas o de la actual
}
}

