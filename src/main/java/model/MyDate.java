package model;
import org.w3c.dom.*;
import java.util.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import io.*;
public class MyDate {
   int day = 0;
   int month = 0;
   int year = 0;
   int money = 0;
   public MyDate (int d, int m, int y, int mon){
	   day = d;
	   month = m;
	   year = y;
	   money = mon;
   }
public void addMoney(int a){
	money+= a;
}
public int  getDay() {
        return day;
    }
public int  getMonth() {
        return month;
    }
public int  getYear() {
        return year;
    }
public int getMoney() {
        return money;
    }
}
