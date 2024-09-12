import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.*;

public class Musica {
 //--Adaptar la estructura a los objetivos y restricciones del problema
 //--de acuerdo a un patrón de diseño arquitectónico de tres capas
 //--(presentación, dominio y almacenamiento).
 static ArrayList discos = null;

 public static void main(String argv[]) {
   discos = new ArrayList();

   discos.addAll(Almacenamiento.procesarXML("c:\\collece\\cd_catalog1.xml"));
   discos.addAll(Almacenamiento.procesarXML("c:\\collece\\cd_catalog2.xml"));

   Collections.sort(discos);
   pintarDiscos();
 }

 private static void pintarDiscos() {
Disco disco = null;
for (int i=0; i<discos.size(); i++) {
disco = (Disco)discos.get(i);
if (disco.getAnyo() > 1990 && disco.getPrecio() > 9) {
System.out.println(disco.toString());
}
}
 }
}

// Almacenamiento (XML)
class Almacenamiento {

 public static ArrayList procesarXML(String xmlCatalogo) {
   final ArrayList discos = new ArrayList();
   try {
     SAXParserFactory factory = SAXParserFactory.newInstance();
     SAXParser saxParser = factory.newSAXParser();
     DefaultHandler handler = new DefaultHandler() {
       boolean etiqTit=false,etiqArt=false,etiqPrec=false,etiqYear=false;
Disco disco = new Disco();
       public void startElement(String uri, String localName,
                   String qName, Attributes attributes) throws SAXException {
 //System.out.println(qName);          
 if(qName.equals("TITLE")) etiqTit=true;
         else if(qName.equals("ARTIST")) etiqArt=true;
         else if(qName.equals("PRICE")) etiqPrec=true;
         else if(qName.equals("YEAR")) etiqYear=true;

 if(qName.equals("CD")) disco = new Disco();
       }
       public void characters(char ch[], int start, int length) throws SAXException {
         if(etiqTit) {
           //--Eliminar las instrucciones de impresión no necesarias
           //System.out.println("TÍTULO: "+new String(ch,start,length));
           disco.setTitulo(new String(ch,start,length));
   etiqTit=false;
         } else if(etiqArt) {
           //System.out.println("ARTISTA: "+new String(ch,start,length));
   disco.setArtista(new String(ch,start,length));
           etiqArt=false;
         } else if(etiqPrec) {
           //System.out.println("PRECIO: "+new String(ch,start,length));
   disco.setPrecio(Integer.parseInt(new String(ch,start,length)));
           etiqPrec=false;
         } else if(etiqYear) {
           //System.out.println("AÑO: "+new String(ch,start,length));
           disco.setAnyo(Integer.parseInt(new String(ch,start,length)));
           etiqYear=false;
           
           discos.add(disco);
         }
       }
     };
     saxParser.parse(xmlCatalogo,handler);
   } catch (Exception e) {
     e.printStackTrace();
   }
   return discos;
 }

}

// Dominio
class Disco implements Comparable{

private String titulo;
public String getTitulo() { return titulo; }
public void setTitulo(String titulo) { this.titulo = titulo; }

private String artista;
public String getArtista() { return artista; }
public void setArtista(String artista) { this.artista = artista; }

private int precio;
public int getPrecio() { return precio; }
public void setPrecio(int precio) { this.precio = precio; }

private int anyo;
public int getAnyo() { return anyo; }
public void setAnyo(int anyo) { this.anyo = anyo; }

public Disco() {
titulo=""; artista=""; precio=0; anyo=0;
}

public int compareTo(Object o) {
Disco disco = (Disco)o;
return this.precio - disco.precio;
}

public String toString() {
return precio + "  " + anyo + "  " + titulo + "  " + artista;
}

}
