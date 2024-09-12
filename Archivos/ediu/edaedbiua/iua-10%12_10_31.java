import java.io.*;
class Prueba{
public static void main(String[]args)throws IOException{
int nums []=new int[10];
int media,moda;
BufferedReader leer=new BufferedReader(new InputStreamReader(System.in));

for(int i=0;i<10;i++){
System.out.println("Introduce numero");
nums[i]=Integer.parseInt(leer.readLine());
}
ordenar(nums);
media=calculaMedia(nums);
moda=calculaMediana(nums);

System.out.println("El vector ordenado es: ");
for(int i=0;i<10;i++){
System.out.println(nums[i]+" ");
}
System.out.println("La media del vector es: "+media);
System.out.println("La moda del vector es: "+moda);
}

public static void ordenar(int nums[]){
int aux=0;
for(int i=0;i<10;i++){
int k=i;
for(int j=i+1;i<10;i++){
if(nums[j]<nums[k]) k=j;

}
/*Intercambiamos n[i] con nums[k]*/
aux=nums[i];
nums[i]=nums[k];
nums[k]=aux;
}

}
public static int calculaMedia(int[]nums){
int media=0;
for(int i=0;i<10;i++){
media=media+nums[i];
}
media=media/10;
return media;
}
public static int calculaMediana(int[]nums){
int mediana=(nums[4]+nums[5])/2;
return mediana;
}
}
