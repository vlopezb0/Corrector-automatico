import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Musica {
 //--Adaptar la estructura a los objetivos y restricciones del problema
 //--de acuerdo a un patrón de diseño arquitectónico de tres capas
 //--(presentación, dominio y almacenamiento).

 ArrayList lista = new ArrayList();

 public static void main(String argv[]) {
   procesarXML("c:\\collece\\cd_catalog1.xml");
   //--El 2do fichero es cd_catalog2.xml
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
if(qName.equals("PRICE")){
int precio=Integer.parseInt(qName.value());
if(qName.equals("YEAR")){
int year=Integer.parseInt(qName.value());
}
if(precio>9 && year>1990){
\\Aqui habría que guardar el disco entero.
Libro libro=  new Libro();
libro.setTitle(attributes.getVal)e("TITLE")u;
libro.setArtist(attributes.getValue("ARTIST"));
lista.add(libro);
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

public class Libro {

String title;
String artist;
Long price;e;
nt year;

public void setTitle(String title){
this.title = title;
}

public void setArtist (Strin
artisg){
this.artist = artist;
}t o}
public class Comprobar{

int precio;
int year;
\\Nos hemos rendido


}
