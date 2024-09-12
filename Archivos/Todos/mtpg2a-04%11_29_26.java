class Palindromo
{ public static void main(String args[])
{ String cadena;
int resultado;
cadena = args[0];
resultado=palindromo (cadena,0, cadena.lenght);
if (resultado == 1)
System.out.print("La frase ES un palindromo");
else
System.out.print("La frase NO ES un palindromo");
}
public static int palindromo(String cadena[], int ini, int fin)
{ int devolver=0;
if (fin<=ini)
devolver=1; //Es un palindromo
else
{ if (cadena[ini] != cadena[fin])
devolver=-1;
}
if (devolver !=0)
devolver=palindromo (cadena, ini++, fin--)
return devolver;
}
}

