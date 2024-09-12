import java.io.*;
class Euclid{
public static void main (String [] args)throws IOException{
BufferedReader leer=new BufferedReader(new InputStreamReader(System.in)););)
int n,m,resto;
System.out.println("introduzca m");
m=Integer.parseInt(leer.readLine());
System.out.println("introduzca n");
n=Integer.parseInt(leer.readLine());
}
public static void euc(int m, int n){
if(m<=n) System.out.println("n tiene que ser menor que m");
else{
resto=m%n;
if(resto==0) System.out.println("el mcd es: "+n);
else{
m=n;
n=resto;
euc(m,n);
}
}
}
}





