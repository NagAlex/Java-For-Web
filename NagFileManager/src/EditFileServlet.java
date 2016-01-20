import java.io.*;
import java.nio.file.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class EditFileServlet extends HttpServlet {
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs) 
    		   throws ServletException, IOException {
//    	String file = rq.getParameter("file");
    	Path file = Paths.get(rq.getParameter("file"));
    	Path parent = file.getParent();

    	try {
    	    ProcessBuilder proc = new ProcessBuilder("notepad.exe", file.toString());
    	    proc.start();
    	} catch (Exception e) {
    	    System.out.println("Ошибка запуска notepad");
    	}
    	
    	RequestDispatcher rd = rq.getRequestDispatcher("/FileManager?dir=" + parent.toString());
    	rd.forward(rq, rs);

/*    	rs.setContentType("text/html");
    	PrintWriter pw = rs.getWriter();
    	pw.println("EditFile: file = " + file);
    	pw.close();*/
    }
}