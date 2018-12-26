package model;

import java.util.*;
import io.*;
public class Item {
    String name;
    String art;
    String desc;
    String price;
    String sale;
    String amount;
    String cat;
    String hash;
    public Item( String n, String a, String d, String p, String s, String am, String c) {
        name = n;
	art = a;
	desc = d;
	price = p;
	
	sale = s;
	amount = am;
	cat = c;
    }

public String  getName() {
        return name;
    }
public String  getDesc() {
        return desc;
    }
public String  getArt() {
        return art;
    }
public String  getPrice() {
        return price;
    }
public String  getSale() {
        return sale;
    }
public String  getAmount() {
        return amount;
    }
public String  getCat() {
        return cat;
    }
public String getHash() {
        return hash;
    }
public void setSale(String s){
	try{
		int t = Integer.parseInt(s);
		if(t < 100 && t >= 0)
			sale = s;
	}
	catch(Exception e){}
	

    }
public void addItem(int a){
	int b = Integer.parseInt(amount);
	b += a;
	if(b >= 0){
		amount = String.valueOf(b);
	}	
    }
public int newPrice(){
	int p = 0; int s = 0;
		try{
		p = Integer.parseInt(price);
		s = Integer.parseInt(sale);
		} catch(Exception e){}
		return p - (p * s / 100);
	}
}
