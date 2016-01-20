import java.io.*;
import java.nio.file.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class CreateNewFileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest rq, HttpServletResponse rs) 
    		   throws ServletException, IOException {
    	String dir = rq.getParameter("dir");
    	String filename = rq.getParameter("filename");
    	String press = rq.getParameter("press");
/*    	rs.setContentType("text/html");
    	PrintWriter pw = rs.getWriter();
    	pw.println("<b>Create new file: folder = " + dir + "  filename = " + filename + "  press = " + press + "</b>");
    	pw.close();
*/
    	if(press.equalsIgnoreCase("OK")) {
    	    Path file = Paths.get(dir, filename);
    	    try {
    	    	Path newFile = Files.createFile(file);
    	    } catch (FileAlreadyExistsException e) {
    	    	System.out.println("File " + file + " already exists");
    	    }
    	}
    	RequestDispatcher rd = rq.getRequestDispatcher("/FileManager?dir=" + dir);
    	rd.forward(rq, rs);
    }
}