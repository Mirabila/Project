package servlets;

import assists.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.util.ArrayList;
import model.*;
import io.*;
public class AddingServlet extends HttpServlet {
     ArrayList<Item> it = new ArrayList<Item>();
    public void init(ServletConfig config) {
    }

    public void service(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
	
     
	String name = null;
	String item = null;
	String art = null;
	String desc = null;
	String price = null;
	String amount = null;
	String cat = null;
	StringBuilder sb = new StringBuilder();
	PrintWriter out = response.getWriter();
	response.setContentType("text/html");
	
	     
	out.println("<html><head>");
	out.println("</head><body>");
	
	request.getRequestDispatcher("admin.html").include(request, response);
	HttpSession session = request.getSession(false);       
	String uri = request.getRequestURI();
	if( uri.equals("/shop/AddingServlet") ) {
		it.clear();
		new XmlIO().loadItemsFromFile("shop.xml", it);
		item = request.getParameter("item");
		art = request.getParameter("art");
		desc = request.getParameter("desc");
		price = request.getParameter("price");
		amount = request.getParameter("amount");
		cat = request.getParameter("cat");
		int flag = 0;
		for(int i = 0; i < it.size(); i++){
			if(!it.get(i).getArt().equals(art)){
				flag = 1;
			}

		}
		/*if((!cat.equals(null)) && (!item.equals(null)) && (!art.equals(null)) && (!desc.equals(null)) && (!price.equals(null)) && (!amount.equals(null)) && (!cat.equals("")) && (!item.equals("")) && (!art.equals("")) && (!desc.equals("")) && (!price.equals("")) && (!amount.equals("")) && flag == 0){*/
			Item i = new Item(item,art,desc,price,"0",amount,cat);
			it.add(i);
			new XmlIO().saveItemsToFile("shop.xml", it);
		//}
		response.sendRedirect("/shop/AdminServlet");
	        return;
	}
        out.println("</body></html>");
    }


}
