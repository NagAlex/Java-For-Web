import java.io.*;
import java.nio.file.*;
import java.nio.charset.Charset;
import javax.servlet.*;
import javax.servlet.http.*;

public class ReadFileServlet extends HttpServlet {
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs) 
    		   throws ServletException, IOException {
    	Path file = Paths.get(rq.getParameter("file"));
    	StringBuffer strBuf = new StringBuffer("<html><head>" + 
    			      "<meta charset=\"UTF-8\">" +
    			      "<title>" + file.toString() + "</title></head>" +
                              "<body>");
        strBuf.append("<b>File:  " + file.toString() + "</b><br><p>");
        try( BufferedReader text = Files.newBufferedReader(file, Charset.forName("ISO-8859-1"))) {
    	    int next;
    	    while((next = text.read()) != -1) {
    	    	strBuf.append((char) next);
    	    	if(next == 10)
    	    	    strBuf.append("<br>");
    	    }
    	} catch (IOException e) {
    	    System.out.println(e);
    	}
    	strBuf.append("</p></body></html>");
    	
    	rs.setContentType("text/html");
    	PrintWriter pw = rs.getWriter();
    	pw.println(strBuf.toString());
    	pw.close();
    }
}