import java.io.*;
class Maximo{
public static void main(String [] args)throws IOException{
BufferedReader leer=new BufferedReader(new InputStreamReader(System.in));
int m,n,resul=0;
System.out.println("Introduce nuemro: ");
m=Integer.parseInt(leer.readLine());
System.out.println("Introduce nuemro: ");
n=Integer.parseInt(leer.readLine());
if(n<m){
resul=max(m,n);
System.out.println("El resultado es:"+resul);
}
else{
System.out.println("No se puede hacer mcd porque n no es menor que m");
}
}
public static int max(int m,int n){
int resul,resto;
resto=m%n;
if(resto==0){
resul=n;
}
else{
resul=max(n,resto);
}
return resul;
}
}
