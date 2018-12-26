package servlets;

import assists.*;import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.util.ArrayList;
import model.*;
import io.*;
public class Adding extends HttpServlet {
     ArrayList<Item> it = new ArrayList<Item>();
    public void init(ServletConfig config) {
    }

    public void service(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
	
    
	StringBuilder sb = new StringBuilder();
	PrintWriter out = response.getWriter();
	response.setContentType("text/html");
	
	     
	out.println("<html><head>");
	out.println("</head><body>");
	
	request.getRequestDispatcher("admin.html").include(request, response);
	HttpSession session = request.getSession(false);       
	String uri = request.getRequestURI();
	if( uri.equals("/shop/Adding") ) {
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
        out.println("</body></html>");
    }


}
