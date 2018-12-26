package servlets;

import assists.*;import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import model.*;
import io.*;
public class LogoutServlet extends HttpServlet {
        protected void doGet(HttpServletRequest request, HttpServletResponse response)  
                                throws ServletException, IOException {
            response.setContentType("text/html");

            HttpSession session = request.getSession();
            session.invalidate();

            PrintWriter out=response.getWriter();
            out.println("<html><body>");

            request.getRequestDispatcher("link.html").include(request, response);
            response.sendRedirect("/shop/List");   
            out.println("</html></body>");

            out.close();
    }
}
