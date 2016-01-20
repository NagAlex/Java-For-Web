import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.nio.file.*;

public class AskDeleteFileServlet extends HttpServlet {
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs) 
    		   throws ServletException, IOException {
    	Path delFile = Paths.get(rq.getParameter("file"));
    	Path parent = delFile.getParent();
//    	RequestDispatcher rd;
    	rs.setContentType("text/html");
    	PrintWriter pw = rs.getWriter();
    	pw.println("<h4>Are you sure to delete file: " + delFile.toString() + "?   " + 
    		    "<font color=\"blue\">" +
    		    "[<a href=\"DeleteFile?file=" + delFile.toString() + "\">YES</a>]    " +
    		    "[<a href=\"FileManager?dir=" + parent.toString() + "\">NO</a>]" +
    		    "</font></h4>");
    	pw.close();
    }
}