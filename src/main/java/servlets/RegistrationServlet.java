package servlets;

import assists.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.TreeMap;
import model.*;
import io.*;
public class RegistrationServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String name = request.getParameter("name");
        String password1 = request.getParameter("password1");
	String password2 = request.getParameter("password2");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
	
        TreeMap<String,String> map = new TreeMap<String, String>();
        new ReaderWriter().read("users.txt", map);
        if (map.containsKey(name)) {
            request.getRequestDispatcher("link.html").include(request, response);
            out.print("<br>We have user with this username. Try another name.");
            request.getRequestDispatcher("reg.html").include(request, response);

        }
	else if(!password1.equals(password2)){
		request.getRequestDispatcher("link.html").include(request, response);
		out.print("<br>Passwords don`t coincide.");
		request.getRequestDispatcher("reg.html").include(request, response);
		
	}
	else if(name.equals(null) || password1.equals(null) || password2.equals(null)){
		request.getRequestDispatcher("link.html").include(request, response);
		out.print("<br>Name and password need to have length more then 0.");
		request.getRequestDispatcher("reg.html").include(request, response);
		
	}
	
	
        else {
                request.getRequestDispatcher("link.html").include(request, response);
                map.put(name, password1);
                out.print("<br>You are successfully registered!");
	}
	   
	new ReaderWriter().write("users.txt", map);
        out.println("</html></body>");
        out.close();

    }


}
