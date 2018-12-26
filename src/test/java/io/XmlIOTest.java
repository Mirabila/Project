package io;
import java.util.*;
import io.*;
import model.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class XmlIOTest {
    @Test
    public void loadItemsFromFile(){
	ArrayList<Item> items = new ArrayList<Item>();
	Item i = new Item("Mayami","115","New","500","90","50","Pomade");
	XmlIO x = new XmlIO();
	x.loadItemsFromFile("example.xml", items); 
        int flag = 0;
	if(!(items.get(0).getName().equals(i.getName()))){
		flag = 1;
	}
	if(!(items.get(0).getDesc().equals(i.getDesc()))){
		flag = 1;
	}
	if(!(items.get(0).getArt().equals(i.getArt()))){
		flag = 1;
	}
	if(!(items.get(0).getPrice().equals(i.getPrice()))){
		flag = 1;
	}
	if(!(items.get(0).getSale().equals(i.getSale()))){
		flag = 1;
	}
	if(!(items.get(0).getCat().equals(i.getCat()))){
		flag = 1;
	}
	if(!(items.get(0).getAmount().equals(i.getAmount()))){
		flag = 1;
	}
	assertEquals(0, flag);

     
    }
}
