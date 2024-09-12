import java.io.*;
class Euclides{
public static void main(String []args)throws IOException{
BufferedReader leer=new BufferedReader(new InputStreamReader(System.in));
int n,m,aux,mcd;
System.out.println("Introduzca el numero n");
n=Integer.parseInt(leer.readLine());
System.out.println("Introduzca el numero m");
m=Integer.parseInt(leer.readLine());
if(m<n){ //intercambio de variables
aux=m;
n=m;
m=aux;
}
mcd=Euclides(n,m);
System.out.println("El mcd es: "+mcd);
}

public static int Euclides (int n, int m){
int resto,mcd=1;
resto=m%n;
if(resto==0)
mcd=n;
else{
m=n;
n=resto;
Euclides(n,m);
}
return mcd;
}
}


