import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.ArrayList;

public class Musica {
 //--Adaptar la estructura a los objetivos y restricciones del problema
 //--de acuerdo a un patrón de diseño arquitectónico de tres capas
 //--(presentación, dominio y almacenamiento).

 public static void main(String argv[]) {
   Almacenar.procesarDatos();
 }

 public static void procesarXML(String xmlCatalogo) {
   try {
     SAXParserFactory factory = SAXParserFactory.newInstance();
     SAXParser saxParser = factory.newSAXParser();
     DefaultHandler handler = new DefaultHandler() {
       boolean etiqTit=false,etiqArt=false,etiqPrec=false,etiqYear=false;
       public void startElement(String uri, String localName,
                   String qName, Attributes attributes) throws SAXException {
         if(qName.equals("TITLE")) etiqTit=true;
         else if(qName.equals("ARTIST")) etiqArt=true;
         else if(qName.equals("PRICE")) etiqPrec=true;
         else if(qName.equals("YEAR")) etiqYear=true;
       }
       public void characters(char ch[], int start, int length) throws SAXException {
         if(etiqTit) {
           //--Eliminar las instrucciones de impresión no necesarias
           System.out.println("TÍTULO: "+new String(ch,start,length));
           etiqTit=false;
         } else if(etiqArt) {
           System.out.println("ARTISTA: "+new String(ch,start,length));
           etiqArt=false;
         } else if(etiqPrec) {
           System.out.println("PRECIO: "+new String(ch,start,length));
           etiqPrec=false;
         } else if(etiqYear) {
           System.out.println("AÑO: "+new String(ch,start,length));
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

public class Almacenar () {

public static void procesarDatos() {
ArrayList lst_discos1 = procesarXML("c:\\collece\\cd_catalog1.xml");
ArrayList lst_discos2 = procesarXML("c:\\collece\\cd_catalog2.xml");
TreeSet ts_disco = new TreeSet();
Disco disco = new Disco("", "", 0, 0);
for (int i=0; i<lst_discos1.size(); i++) {
if (((Disco)lst_discos.get(i)).compareTo(disco) > -2) {
ts_disco.add(lst_discos.get(i));
}
}

for (int i=0; i<lst_discos2.size(); i++) {
if (((Disco)lst_discos.get(i)).compareTo(disco) > -2) {
ts_disco.add(lst_discos.get(i));
}
}

Iterator it = ts_disco.iterator();
while(it.hasNext()){
disco = (Disco)it.next();
disco.toString();
}
}

public static ArrayList procesarXML(String xmlCatalogo) {
SAXParserFactory factory = SAXParserFactory.newInstance();
      SAXParser saxParser = factory.newSAXParser();
ArrayList lstDiscos = new ArrayList();
      DefaultHandler handler = new DefaultHandler() {
Disco disco = new Disco("", "", 0, 0);
boolean etiqTit=false,etiqArt=false,etiqPrec=false,etiqYear=false;
public void startElement(String uri, String localName,
                   String qName, Attributes attributes) throws SAXException {

if (qName.equals("CD")) {
disco = new Disco("", "", 0, 0);
} else if (qName.equals("TITLE")) {
etiqTit = true;
} else if (qName.equals("ARTIST")) {
etiqArt = true;
} else if (qName.equals("PRICE")) {
etiqPrec = true;
} else if (qName.equals("YEAR")) {
etiqYear = true;
}
}

public void characters(char ch[], int start, int length) throws SAXException {
          if(etiqTit) {
            disco.setTitulo(new String(ch,start,length));
etiqTit = false;       
} else if(etiqArt) {
            disco.setArtista(new String(ch,start,length));
           etiqArt=false;
          } else if(etiqPrec) {
            disco.setPrecio(Integer.parseInt(new String(ch,start,length)));
etiqPrec = false;
          } else if(etiqYear) {APrecio(Integer.parseInt(new String(ch,start,length)));
disco.setAnio(Integer.parseInt(new String(ch,start,length));
            etiqYear=false;
          }        
}

public void endElement(String name) {
lstDiscos.add(disco);
}

}
saxParser.parse(xmlCatalogo,handler);
return lstDiscos;
}
}

public class Disco implements Comparable{

private String titulo;
private String artista;
private int precio;
private int anio;

public Disco(String titulo, String artista, int precio, int anio){

this.titulo = titulo;
this.artista = artista;
this.precio = precio;
this.anio = anio;
}

public void setTitulo(String titulo){

this.titulo = titulo;
}

public String getTitulo(){

return this.titulo;
}

public void setArtista(String artista){

this.artista = artista;
}

public String getArtista(){

return this.artista;
}

public void setPrecio(int precio){

this.precio = precio;
}

public int getPrecio(){

return this.precio;
}

public void setAnio(int anio){

this.anio = anio;
}

public int getAnio(){

return this.anio;
}

public int compareTo(Object o){

if(o instanceOf Disco){

if(this.getAnio > 1990 && o.getAnio > 1990){

if(this.getPrecio > 9 && o.getPrecio > 9){

if(this.precio > o.precio){
return 1;
}

if(this.precio < o.precio){
return -1;
}else{
return 0;
}
} else {
return -2;
}
} else {
return -2;
}

}
}

public boolean equals(Object obj) {
if(o instanceOf Disco){
if(this.getTitulo.equals(obj.getTitulo())){
return true;
}
else
return false;
}

else
return false;
}

public String toString(){

return this.getTitulo() + this.getAutor() + this.getAnio() + this.getAnio();
}
}
