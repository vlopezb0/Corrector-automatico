import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Musica {
 //--Adaptar la estructura a los objetivos y restricciones del problema
 //--de acuerdo a un patrón de diseño arquitectónico de tres capas
 //--(presentación, dominio y almacenamiento).
 public static ArrayList <Disco>lista=new ArrayList<Disco>();

 public static void main(String argv[]) {
   procesarXML("c:\\collece\\cd_catalog1.xml");
   procesarXML("c:\\collece\\cd_catalog2.xml");

LogicaNegocio logicaNegocio = new LogicaNegocio();
Presentacion presentacion = new Presentacion();
   ArrayList <Disco>listaFiltrada = logicaNegocio.getListaFiltrada(lista);
   listaFiltrada = logicaNegocio.getListaOrdenada(lista);
   presentacion.mostrar(listaFiltrada);


   System.out.println("Total discos: "+ lista.size());
   //--El 2do fichero es cd_catalog2.xml
 }

 public static void procesarXML(String xmlCatalogo) {
   try {
     SAXParserFactory factory = SAXParserFactory.newInstance();
     SAXParser saxParser = factory.newSAXParser();

     DefaultHandler handler = new DefaultHandler() {
       boolean etiqTit=false,etiqArt=false,etiqPrec=false,etiqYear=false;


Disco disco;
       public void startElement(String uri, String localName,
                   String qName, Attributes attributes) throws SAXException {
         if(qName.equals("CD"))disco=new Disco();
  else if(qName.equals("TITLE")) etiqTit=true;
         else if(qName.equals("ARTIST")) etiqArt=true;
         else if(qName.equals("PRICE")) etiqPrec=true;
         else if(qName.equals("YEAR")) etiqYear=true;
       }

public void endElement(String uri,
                      String localName,
                      String qName)
               throws SAXException{

if(qName.equals("CD")){
addDisco(disco);
}

}

public void addDisco(Disco disco){
lista.add(disco);
}

public ArrayList<Disco> getLista(){
return lista;
}

       public void characters(char ch[], int start, int length) throws SAXException {

if(etiqTit) {
           //--Eliminar las instrucciones de impresión no necesarias
            //System.out.println("TÍTULO: "+new String(ch,start,length));
disco.setTitle(new String(ch,start,length));
           etiqTit=false;
         } else if(etiqArt) {
            //System.out.println("ARTISTA: "+new String(ch,start,length));
disco.setArtist(new String(ch,start,length));
           etiqArt=false;
         } else if(etiqPrec) {
            //System.out.println("PRECIO: "+new String(ch,start,length));
disco.setPrice(Double.parseDouble(new String(ch,start,length)));
           etiqPrec=false;
         } else if(etiqYear) {
            //System.out.println("AÑO: "+new String(ch,start,length));
disco.setYear(Integer.parseInt(new String(ch,start,length)));
           etiqYear=false;
         }

       }
     };
     saxParser.parse(xmlCatalogo,handler);

   } catch (Exception e) {
     e.printStackTrace();
   }
 }
}

class Disco{

private String title;
private String artist;
private double price;
private int year;

public Disco (){

}

public Disco (String title, String artist, double price, int year){

this.title = title;
this.artist = artist;
this.price = price;
this.year = year;

}

public void setTitle(String title){
this.title = title;
}

public String getTitle(){
return title;
}

public void setArtist(String artist){
this.artist = artist;
}

public String getArtist(){
return artist;
}

public void setPrice(double price){
this.price=price;
}

public double getPrice(){
return price;
}

public void setYear(int year){
this.year=year;
}

public int getYear(){
return year;
}

}

class LogicaNegocio{

public LogicaNegocio(){}

public ArrayList<Disco> getListaFiltrada(ArrayList<Disco> discos){

ArrayList<Disco> resultado = new ArrayList<Disco>();
for(Disco disco: discos){
if(disco.getYear()>=1990){
resultado.add(disco);
}
}

return resultado;
}

public ArrayList<Disco> getListaOrdenada(ArrayList<Disco> discos){


Collections.sort(discos, new Comparator(){
public int compare(Object p1, Object p2) {
        if (((Disco)p1).getPrice() < ((Disco)p2).getPrice()) return -1;         
if (((Disco)p1).getPrice() > ((Disco)p2).getPrice()) return 1;         
return 0;     
}
});

return discos;
}

}



class Presentacion{

public Presentacion(){}

public void mostrar(ArrayList <Disco> listaDiscos){

for(Disco disco: listaDiscos){

if(disco.getPrice()>9){
System.out.println("Title: "+disco.getTitle());
System.out.println("Artist: "+disco.getArtist());
System.out.println("Price: "+disco.getPrice());
System.out.println("Year: "+disco.getYear());
System.out.println("------------------------------------------");
}

}

}

}
