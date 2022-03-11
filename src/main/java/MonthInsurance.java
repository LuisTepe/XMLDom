import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class MonthInsurance {
    public static void main(String[] args) {
        HashMap<String, Double> subtotales = new HashMap<String, Double>();
        String date;
        Double insurance;
        try{
            File xmlDoc = new File("insurance.xml");
            DocumentBuilderFactory dbFact = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFact.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlDoc);

            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("insurance_record");

            for (int i = 0; i< nList.getLength(); i++){
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE){
                    Element eElement = (Element) nNode;
                    date = eElement.getElementsByTagName("contract_date").item(0).getTextContent();
                    insurance = Double.parseDouble(eElement.getElementsByTagName("insurance").item(0).getTextContent());
                    double val = 0.0;
                    double cont = 0;
                    try{
                        val = insurance;
                    }catch (NumberFormatException e){

                    }

                    if (subtotales.containsKey(date.substring(0,7))){
                        Double sum = subtotales.get(date.substring(0,7));
                        subtotales.put(date.substring(0,7), sum + val);
                    }else{
                        subtotales.put(date.substring(0,7), val);
                    }

                }
            }

            for (Map.Entry<String, Double> entry: subtotales.entrySet()){
                String key = entry.getKey();
                if (subtotales.containsKey(key)){
                    double v1 = entry.getValue();
                    System.out.println("Fecha " + key + " Total ventas " + v1);
                }
            }
        }
        catch(Exception e){
        }
    }
}
