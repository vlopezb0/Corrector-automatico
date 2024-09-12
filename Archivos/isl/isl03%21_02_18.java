import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

class Almacenamiento {
  
  private static Map mapDisco = null;
  
  private static List<Map> listaDiscos = null;
  public static List<Map> procesarXML(String xmlCatalogo) {
    listaDiscos = new ArrayList<Map>();
   try {
     SAXParserFactory factory = SAXParserFactory.newInstance();
     SAXParser saxParser = factory.newSAXParser();
   DefaultHandler handler = new DefaultHandler() {
       boolean etiqTit=false,etiqArt=false,etiqPrec=false,etiqYear,etiqCd=false;
       public void startElement(String uri, String localName,
                   String qName, Attributes attributes) throws SAXException {
         if (qName.equals("CD")) { etiqCd = true;}
         else if(qName.equals("TITLE")) etiqTit=true;
         else if(qName.equals("ARTIST")) etiqArt=true;
         else if(qName.equals("PRICE")) etiqPrec=true;
         else if(qName.equals("YEAR")) etiqYear=true;
       }
       public void characters(char ch[], int start, int length) throws SAXException {
         if(etiqCd) {
             listaDiscos.add(mapDisco);
             mapDisco = new HashMap();
         }  else if(etiqTit) {
           //--Eliminar las instrucciones de no necesarias
           System.out.println("TÍTULO: "+new String(ch,start,length));
           etiqTit=false;
           mapDisco.put("titulo", new String(ch,start,length));       
         } else if(etiqArt) {
           System.out.println("ARTST: "+new String(ch,start,length));
            mapDisco.put("artist", new String(ch,start,length));  
           etiqArt=false;
         } else if(etiqPrec) {
           System.out.println("PRECIO: "+new String(ch,start,length));
           mapDisco.put("precio", new String(ch,start,length));  
           etiqPrec=false;
         } else if(etiqYear) {
           System.out.println("AÑO: "+new String(ch,start,length));
           mapDisco.put("year", new String(ch,start,length));  
           etiqYear=false;
         }
       }
     };
     saxParser.parse(xmlCatalogo,handler);
   } catch (Exception e) {
     e.printStackTrace();
   }

   return listaDiscos;
 }

}

class Presentacion {
}

public class Musica {
 //--Adaptar la estructura a los objetivos y restricciones del problema
 //--de acuerdo a un patrón de diseño arquitectónico de tres capas
 //--(presentación, dominio y almacenamiento).

 public static void main(String argv[]) {
   List<Map> lista = Almacenamiento.procesarXML("c:\\collece\\cd_catalog1.xml");

  for (int i = 0; i < lista.size(); i++) {
     Map mapFilaI = (Map) lista.get(i);
System.out.println("TÍTULO: "+ (String) mapFilaI.get("titulo"));
       System.out.println("ARTST: "+(String) mapFilaI.get("artist"));
       System.out.println("PRECIO: "+(String) mapFilaI.get("precio"));
       System.out.println("AÑO: "+(String) mapFilaI.get("year"));
   //--El 2do fichero es cd_catalog2.xml
      
 }
}

}
