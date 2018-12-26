package model;
import java.util.*;
import io.*;
public class BasketItem {
    String username;
    String name;
    String art;
    String price; 
    String amount;
    public BasketItem(String u, String n, String a, String p,String am) {
	username = u;
        name = n;
	art = a;
	price = p;	
	amount = am;


    }

public String  getName() {
        return name;
    }
public String  getUsername() {
        return username;
    }
public String  getArt() {
        return art;
    }
public String  getPrice() {
        return price;
    }
public String  getAmount() {
        return amount;
    }
}

