package assists;

import servlets.*;import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;
import model.*;
import io.*;
public class Powder extends HttpServlet {
    ArrayList<Item> items = new ArrayList<Item>();
    ArrayList<BasketItem> item = new ArrayList<BasketItem>();
 

    protected void service(HttpServletRequest request, HttpServletResponse response)
                      throws ServletException, IOException {
	items = new ArrayList<Item>();
	item = new ArrayList<BasketItem>();
	new XmlIO().loadItemsFromFile("shop.xml", items);
	String uri = request.getRequestURI();
	new XmlItem().loadItemsFromFile("basket.xml", item);
	String name = " ";
        HttpSession session = request.getSession(false);
        if(session != null){
	     
	     name = (String)session.getAttribute("name");
        }
	int s = 0;
	int p = 0;
	PrintWriter out = response.getWriter();
	out.println("<html><head>");
	out.println("</head><body>");
	
	for(int i = 0; i < items.size(); i++){
		if( uri.equals("/shop/Powder/add" + items.get(i).getArt()) ) {
			if(session != null){
				try{
					p = Integer.parseInt(items.get(i).getPrice());
					s = Integer.parseInt(items.get(i).getSale());
				} catch(Exception e){

				}
				BasketItem bi = new BasketItem(name, items.get(i).getName(), items.get(i).getArt(), String.valueOf(p*(100 - s)/100), "1");
				item.add(bi);
				new XmlItem().saveItemsToFile("basket.xml", item);
			}
			response.sendRedirect("/shop/Powder");   
			return;   
		}
	}	
	response.setContentType("text/html");
	if(session != null){
		if(name.equals("admin")){
			request.getRequestDispatcher("admin.html").include(request, response);            
		} else{
	    	 	request.getRequestDispatcher("auth.html").include(request, response);            
	        }
        }
        else{
            request.getRequestDispatcher("link.html").include(request, response);
        }
	new XmlItem().loadItemsFromFile("basket.xml", item);
	if( uri.equals("/shop/Powder")){
		out.println(getMainPage(request,response));
	}
        out.println("</body></html>");
        out.close();
	items.clear();
    }

	public String getMainPage(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession(false);
		items = new ArrayList<Item>();
		new XmlIO().loadItemsFromFile("shop.xml", items);
		int p = 0;
		int s = 0;
		StringBuilder sb = new StringBuilder();
		sb.append("<section class=\"courses\">");
		
       		for(int i = 0; i < items.size(); i++){
			if(!items.get(i).getSale().equals("0") && items.get(i).getCat().equals("Powder") && (!items.get(i).getAmount().equals("0"))){
				sb.append("<article>");
				sb.append("<figure>");
				String ss = items.get(i).getArt();	
				sb.append("<img src=\"images/" + ss +".jpg\"/>");						
				sb.append("</figure>");
				sb.append("<hgroup>");
				sb.append("<h2>" + items.get(i).getName() + "</h2>");
				try{
					p = Integer.parseInt(items.get(i).getPrice());
					s = Integer.parseInt(items.get(i).getSale());
					sb.append("<h3>New price:" + (p * (100 - s) / 100)  + "</h3>");
				} catch(Exception e){
				}
				
				sb.append("<h3>Sale:" + items.get(i).getSale() + "%</h3>");
				sb.append("<h3>Old price:<strike>" + items.get(i).getPrice()  + "</strike></h3>");
				sb.append("<h3>");
				sb.append("Description: " + items.get(i).getDesc());
				sb.append("</h3>");sb.append("<h3>");
				sb.append("Amount: " + items.get(i).getAmount());
				if(session != null){
					sb.append("<form method=\"GET\" action=\"/shop/Powder/add" + items.get(i).getArt() + "\">\n");
					sb.append("<input type=\"submit\" value=\"Add to basket\">\n");
					sb.append("</form>");
				}
				sb.append("</hgroup></article>");
			}
		}
		for(int i = 0; i < items.size(); i++){
			if(items.get(i).getSale().equals("0") && items.get(i).getCat().equals("Powder") && (!items.get(i).getAmount().equals("0"))){
				sb.append("<article>");
				sb.append("<figure>");
				String ss = items.get(i).getArt();	
				sb.append("<img src=\"images/" + ss +".jpg\"/>");				
				sb.append("</figure>");
				sb.append("<hgroup>");
				sb.append("<h2>" + items.get(i).getName() + "</h2>");
				sb.append("<h3>Price:" + items.get(i).getPrice()  + "</h3>");
				sb.append("<h3>");
				sb.append("Description: " + items.get(i).getDesc());
				sb.append("</h3>");sb.append("<h3>");
				sb.append("Amount: " + items.get(i).getAmount());
				if(session != null){
					sb.append("<form method=\"GET\" action=\"/shop/Powder/add" + items.get(i).getArt() + "\">\n");
					sb.append("<input type=\"submit\" value=\"Add to basket\">\n");
					sb.append("</form>");
				}
				sb.append("</hgroup></article>");
			}
		}
	
		return sb.toString();

	}
}
