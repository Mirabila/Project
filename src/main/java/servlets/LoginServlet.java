package servlets;

import assists.*;import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.TreeMap;
import model.*;
import io.*;
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        TreeMap<String,String> map = new TreeMap<String, String>();
        new ReaderWriter().read("users.txt", map);
	String password1 = new ReaderWriter().generateHash(password);
        if(password1.equals(map.get(name)) ){
            request.getRequestDispatcher("auth.html").include(request, response);
            HttpSession session = request.getSession();
            session.setAttribute("name", name);
            new ReaderWriter().write("users.txt", map);
        }
        else{
            request.getRequestDispatcher("link.html").include(request, response);
            out.print("Sorry, username or password error!");
            request.getRequestDispatcher("login.html").include(request, response);
        }
	response.sendRedirect("/shop/List");   
        out.println("</html></body>");
        out.close();
    }

}
