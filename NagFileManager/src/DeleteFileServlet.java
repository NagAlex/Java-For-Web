import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.nio.file.*;

public class DeleteFileServlet extends HttpServlet {
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs) 
    		   throws ServletException, IOException {
    	Path delFile = Paths.get(rq.getParameter("file"));
    	Path parent = delFile.getParent();
    	Files.delete(delFile);
    	RequestDispatcher rd = rq.getRequestDispatcher("/FileManager?dir=" + parent.toString());
    	rd.forward(rq, rs);
    }
}