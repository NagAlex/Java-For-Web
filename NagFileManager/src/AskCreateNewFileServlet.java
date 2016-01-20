import java.io.*;
import java.nio.file.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AskCreateNewFileServlet extends HttpServlet {
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs) 
    		   throws ServletException, IOException {
    	Path dir = Paths.get(rq.getParameter("dir"));
    	rs.setContentType("text/html");
    	PrintWriter pw = rs.getWriter();
    	if(Files.isDirectory(dir)) {
    	    pw.println("<form name=\"create\" method=\"post\" action=\"CreateFile\">" +
    		       "<b>Please enter new filename: <b>" +
                       "<input type=\"hidden\" name=\"dir\" value=\"" + dir + "\">"+
                       "<input type=textbox size=30 name=\"filename\" value=\"\">"+
                       "<input type=submit name=\"press\" value=\"OK\">" + 
                       "<input type=submit name=\"press\" value=\"Cancel\">" + 
                       "</form>" );
    	} else {
    	    pw.println("<b>To create a new file, please choose a <font color=\"red\">" +
		       "folder</font> as a placement instead of file</b>");
    	}
    	pw.close();
    }
}