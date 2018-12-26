package servlets;

import assists.*;import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;
import java.util.*;
import java.util.Iterator;
import java.util.Map.*;
import model.*;
import io.*;
public class AdminServlet extends HttpServlet {
    ArrayList<Item> it = new ArrayList<Item>();
  ArrayList<MyDate> md = new ArrayList<MyDate>();

    protected void service(HttpServletRequest request, HttpServletResponse response)
                      throws ServletException, IOException {
	

	StringBuilder sb = new StringBuilder();
	PrintWriter out = response.getWriter();
	response.setContentType("text/html");
	
	     
	out.println("<html><head>");
	out.println("</head><body>");
	request.getRequestDispatcher("admin.html").include(request, response);
	HttpSession session = request.getSession(false);       
	String uri = request.getRequestURI();   
	if( uri.equals("/shop/AdminServlet/adding") ) {
		it.clear();
		new XmlIO().loadItemsFromFile("shop.xml", it);
		String sale = request.getParameter("sale");
		String articul = request.getParameter("articul");
		int flag = 0;
		for(int i = 0; i < it.size(); i++){
			if(it.get(i).getArt().equals(articul)){
				it.get(i).setSale(sale);
			}

		}
		new XmlIO().saveItemsToFile("shop.xml", it);
		response.sendRedirect("/shop/AdminServlet");
	        return;
	}

	out.println(getMainPage());
	new XmlDate().loadDatesFromFile(md);
	Calendar c = new GregorianCalendar();     
          
	

	out.println("<TEXT>Incomes for day: " + oneDay(c.get(Calendar.DAY_OF_MONTH),c.get(Calendar.MONTH),c.get(Calendar.YEAR)));
	out.println("Incomes for month: " + oneMonth(c.get(Calendar.MONTH),c.get(Calendar.YEAR)));
	out.println("Incomes for year: " + oneYear(c.get(Calendar.YEAR)) + "</TEXT>");


        out.println("</body></html>");

    }
	public String getMainPage(){
		it = new ArrayList<Item>();
		new XmlIO().loadItemsFromFile("shop.xml", it);
		StringBuilder sb = new StringBuilder();
		sb.append("<adminFigure>");
		sb.append("<form method=\"GET\" action=\"/shop/AddingServlet\">");
		sb.append("Name: <input type=\"text\" name=\"item\"><br>"); 
		sb.append("Articul: <input type=\"text\" name=\"art\"><br>"); 
		sb.append("Description: <input type=\"text\" name=\"desc\"><br>"); 
		sb.append("Price: <input type=\"text\" name=\"price\"><br>"); 
		sb.append("Amount: <input type=\"text\" name=\"amount\"><br>"); 
		
		sb.append("<select name=\"cat\">");
		sb.append("<ul class=\"submenu\">");
		sb.append("<option value="+ "Pomade" + ">");
		sb.append("Pomade");
		sb.append("</option>");
		sb.append("<option value="+ "Powder" + ">");
		sb.append("Powder");
		sb.append("</option>");
		sb.append("<option value="+ "Cream" + ">");
		sb.append("Cream");
		sb.append("</option>");
		sb.append("<option value="+ "Eyeshadow" + ">");
		sb.append("Eyeshadow");
		sb.append("</option>");
		sb.append("<option value="+ "Mascara" + ">");
		sb.append("Mascara");
		sb.append("</option>");
		sb.append("<option value="+ "Parfumery" + ">");
		sb.append("Parfumery");
		sb.append("</option>");
		sb.append("</select>");
		sb.append("<br>");
		sb.append("<input type=\"submit\" value=\"Add\"><br>");
		sb.append("</form><br>");
		sb.append("</adminFigure>");sb.append("<hgroup>");
		sb.append("<form method=\"GET\" action=\"/shop/Adding\">");
		sb.append("Set sale(in %): <input type=\"text\" name=\"sale\"><br>"); 
		
		sb.append("<select name=\"articul\">");
		sb.append("<ul class=\"submenu\">");
		for(int i = 0; i < it.size(); i++) {
		    String key = it.get(i).getArt();
		    sb.append("<option value="+ key + ">");
		    sb.append(key);
		    sb.append("</option>");
		}     

	
		sb.append("</select><br");
		sb.append("<br>");
		sb.append("<input type=\"submit\" value=\"Add\"><br>");
		sb.append("</form>");
		sb.append("</hgroup>");
		return sb.toString();
	}
	public int oneDay(int d, int m, int y){
		md = new ArrayList<MyDate>();
		new XmlDate().loadDatesFromFile(md);
		int t = 0;
		for(int i = 0; i < md.size(); i++){
			if(md.get(i).getDay() == d && md.get(i).getMonth() == m && md.get(i).getYear() == y){

				t += md.get(i).getMoney();

			}
		}
		return t;
	}	
	public int oneMonth(int m, int y){
		ArrayList<MyDate> md = new ArrayList<MyDate>();
		new XmlDate().loadDatesFromFile(md);
		int t = 0;
		for(int i = 0; i < md.size(); i++){
			if(md.get(i).getMonth() == m && md.get(i).getYear() == y){
				
				t += md.get(i).getMoney();

			}
		}
		return t;
	}
	public int oneYear(int y){
		ArrayList<MyDate> md = new ArrayList<MyDate>();
		new XmlDate().loadDatesFromFile(md);
		int t = 0;
		for(int i = 0; i < md.size(); i++){
			if(md.get(i).getYear() == y){
				
				t += md.get(i).getMoney();

			}
		}
		return t;
	}
}
