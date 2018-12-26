package io;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import model.*;
public class XmlItem {
    private ArrayList<BasketItem> items = new ArrayList<BasketItem>();
 enum elem {basket,item,username,name,art,price,amount}


    public void loadItemsFromFile(String filename, ArrayList<BasketItem> items) {
        try {
            File fXmlFile = new File(filename);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName(elem.item.toString());
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    items.add(new BasketItem(
			eElement.getElementsByTagName(elem.username.toString()).item(0).getTextContent(),
			eElement.getElementsByTagName(elem.name.toString()).item(0).getTextContent(),
			eElement.getElementsByTagName(elem.art.toString()).item(0).getTextContent(),
			eElement.getElementsByTagName(elem.price.toString()).item(0).getTextContent(),
			eElement.getElementsByTagName(elem.amount.toString()).item(0).getTextContent()));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public void saveItemsToFile(String filename, ArrayList<BasketItem> items){
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement(elem.basket.toString());
            doc.appendChild(rootElement);

            for (int i = 0; i < items.size(); i++) {
                Element staff = doc.createElement(elem.item.toString());
                rootElement.appendChild(staff);

                Element art = doc.createElement(elem.art.toString());
                art.appendChild(doc.createTextNode(items.get(i).getArt()));
                staff.appendChild(art);
		Element us = doc.createElement(elem.username.toString());
                us.appendChild(doc.createTextNode(items.get(i).getUsername()));
                staff.appendChild(us);

                Element description = doc.createElement(elem.price.toString());
                description.appendChild(doc.createTextNode(items.get(i).getPrice()));
                staff.appendChild(description);

                Element price = doc.createElement(elem.name.toString());
                price.appendChild(doc.createTextNode(items.get(i).getName()));
                staff.appendChild(price);

                Element amount = doc.createElement(elem.amount.toString());
                amount.appendChild(doc.createTextNode(items.get(i).getAmount()));
                staff.appendChild(amount);
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filename));
            transformer.transform(source,result);
        } catch (Exception e) {
              e.printStackTrace();
        }
    }
}
