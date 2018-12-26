package model;

import model.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class ItemTest {
    @Test
    public void newPrice() {
	Item i = new Item("Item","Art","Desc","100","10","Amount","Category");
	int p = 100;
	int s = 10;
        assertEquals(p - (p * s / 100), i.newPrice());

     
    }
}
