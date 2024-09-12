import java.io.*;
public class Menu {

public static void main (String [] args){
new Menu().menu();
}

public void menu (){
int valor=-1;
do{
this.mostrarOpciones();
valor=this.getOpcion(1,4);
procesarValor(valor);
}
while(valor!=4);
}

private void procesarValor(int v){
switch (v){
case 1:
System.out.println("Iniciando/Parando Registro");
//regStartStop();
break;
case 2:
System.out.println("Introduza los datos del cliente, sesion y canal que desee crear");
//creation();
break;
case 3:
//listActiveSessions();
break;
case 4:
System.out.println("Saliendo...");
break;
default:
System.out.println("Error al introducir los datos");
}
}

private void mostrarOpciones (){
String opciones="1.\tInicio/Parada del Registro\n"
=+"2.\tCrear cliente y sesin y canal\n"
=+"3.\tListar sesiones activas\n"
=+"4.\tSalir\n"
+"\nIntroduce una opcion: ";
System.out.println(opciones);
}

private int getOpcion(int min, int max){
String str=getTeclado();
int i=-1;
try {
i=Integer.parseInt(str);
if (i<min || i>max){
i=-1;
System.err.println("Se ha introducido un valor fuera de rango");
}
}catch(Exception e){
i=-1;
System.err.println("Introduzca un numero entero valido");
}

return i;
}

public String getTeclado(){
String str="";
InputStreamReader isr = new InputStreamReader(System.in);
 BufferedReader br = new BufferedReader (isr);

 try
 {
 str=br.readLine();
 }
 catch (Exception e)
 {
 e.printStackTrace();
 }
return str;
}


public static void Salir(){
}

public static void ListaSesionesActivas(){
try{
URLString [] listURL = RegistryFactory ();
for (int i=0; i<listURL; i++){
 System.out.println (listURL(i).toString ());
}
}catch (Exception e){
}
}



}
