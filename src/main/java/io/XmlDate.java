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
public class XmlDate {
    private ArrayList<MyDate> dates = new ArrayList<MyDate>();
 enum elem {calendar, date,day,month,year,money}
    public void loadDatesFromFile(ArrayList<MyDate> dates) {
        try {
            File fXmlFile = new File("date.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();
	
            NodeList nList = doc.getElementsByTagName(elem.date.toString());
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		    int a = 0; int b = 0; int c = 0; int d = 0; 
			Element eElement = (Element) nNode;
		    try{
			a = Integer.parseInt(eElement.getElementsByTagName(elem.day.toString()).item(0).getTextContent());
			b = Integer.parseInt(eElement.getElementsByTagName(elem.month.toString()).item(0).getTextContent());
			c = Integer.parseInt(eElement.getElementsByTagName(elem.year.toString()).item(0).getTextContent());
			d = Integer.parseInt(eElement.getElementsByTagName(elem.money.toString()).item(0).getTextContent());
			}


		    catch(Exception e){
			}
                   
                    dates.add(new MyDate(a,b,c,d));
		    
		
                }
            }

        } catch (
                Exception e) {
            e.printStackTrace();
            throw new RuntimeException("ErrorTaskLoading");
        }
 
    }

    public void saveDatesToFile(ArrayList<MyDate> dates){
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement(elem.calendar.toString());
            doc.appendChild(rootElement);

            for (int i = 0; i < dates.size(); i++) {
                Element date = doc.createElement(elem.date.toString());
                rootElement.appendChild(date);

                Element day = doc.createElement(elem.day.toString());
                day.appendChild(doc.createTextNode(String.valueOf(dates.get(i).getDay())));
                date.appendChild(day);

                Element month = doc.createElement(elem.month.toString());
                month.appendChild(doc.createTextNode(String.valueOf(dates.get(i).getMonth())));
                date.appendChild(month);

                Element year = doc.createElement(elem.year.toString());
                year.appendChild(doc.createTextNode(String.valueOf(dates.get(i).getYear())));
                date.appendChild(year);

                Element money = doc.createElement(elem.money.toString());
                money.appendChild(doc.createTextNode(String.valueOf(dates.get(i).getMoney())));
                date.appendChild(money);

            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("date.xml"));
            transformer.transform(source,result);
        } catch (Exception e) {
            throw new RuntimeException("ErrorTaskSaving");
        }
    }
	
}
