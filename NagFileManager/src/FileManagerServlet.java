import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class FileManagerServlet extends HttpServlet {
    List<DirectoryListEntity> dirList;

    protected void service(HttpServletRequest rq, HttpServletResponse rs)
    		   throws ServletException, IOException {
    	String initDir = rq.getParameter("dir");

    	if(initDir.endsWith(".txt") || initDir.endsWith(".TXT") || initDir.endsWith(".java")) {
    	    RequestDispatcher rd = rq.getRequestDispatcher("/ReadFile?file=" + initDir);
    	    rd.forward(rq, rs);
    	}
    	
    	if(initDir.equals("-1")) 
    	    initDir = getInitParameter("home");

    	Path dir = Paths.get(initDir);
    	if(!Files.isDirectory(dir)) {
    	    initDir = dir.getParent().toString();
    	}

    	dirList = getDirectoryList(initDir);

    	StringBuffer outStr = new StringBuffer("<html>" + 
    			"<head>" + 
    			"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">" +
    			"<title>" + initDir + "</title>" + 
    			"</head>" + 
    			"<body><b>Current Folder: <font color=\"navy\" size=\"5\">" + initDir + "</font><br>" +
//    			"(<img alt=\"New File\" src=\"./images/newtextfile.gif\">" +
    			"(<a href=\"AskCreateFile?dir=" + initDir + "\">Create New File</a>)</b><br><br>" +
    			"<table border=\"1\" cellspacing=\"2\">");
    	
//    	pw.println(outStr);
	
    	for(DirectoryListEntity entry: dirList) {
	    outStr.append("<tr><td>" + entry.getCount() + "</td>");

    	    if(entry.isDirectory())
		outStr.append("<td><img src=\"./images/folder.gif\"></td>");
    	    else
  	    	outStr.append("<td><img src=\"./images/docs.gif\"></td>");

    	    outStr.append("<td><a href=\"FileManager?dir=" + entry.getEntity().toString() + "\">" + 
    	    	      entry.getDisplayName()+ "</a></td>");

    	    if(!entry.isDirectory()) 
    	    	outStr.append("<td><a href=\"AskDelete?file=" + entry.getEntity().toString() +
    	    	              "\"><img alt=\"Delete\" src=\"./images/delete.gif\"></a></td>");
    	    if(entry.isTextFile())
    	    	outStr.append("<td><a href=\"EditFile?file=" + entry.getEntity().toString() +
    	    	              "\"><img alt=\"Edit\" src=\"./images/edit.gif\"></a></td>");

    	    outStr.append("</tr>");
//    	    pw.println(outStr);
    	}

    	outStr.append("</table></body></html>");
    	rs.setContentType("text/html");
    	PrintWriter pw = rs.getWriter();
    	pw.println(outStr);
    	pw.close();
    }

    private List<DirectoryListEntity> getDirectoryList(String dir) throws IOException {
    	DirectoryListEntity ent;
    	List<DirectoryListEntity> result = new ArrayList<>();
    	Path dirName = Paths.get(dir);

    	try ( DirectoryStream<Path> dirstrm = Files.newDirectoryStream(dirName) ) {

    	    int dirCount = -1;
    	    for(Path entry: dirstrm) {
    	    	BasicFileAttributes attribs = Files.readAttributes(entry, BasicFileAttributes.class);
    	    	ent = new DirectoryListEntity();
  	    	ent.setEntity(entry);
  	    	ent.setDisplayName(entry.getFileName().toString());

    	    	if(attribs.isDirectory()) {
    	            ent.setDirectory(true);
    	            dirCount++;
    	            result.add(dirCount, ent);
    	        } else {
  	    	    ent.setDirectory(false);
  	    	    result.add(ent);
  	    	}
    	    }

    	} catch(InvalidPathException e) {
    	  //  outStr = "Path Error " + e.toString() + "<br>";
    	} catch(NotDirectoryException e) {
    	  //  outStr = initDir + " is not a Directory.<br>";
    	}

    	int nameCount = dirName.getNameCount();
    	Path parent = dirName.getParent();

    	if(nameCount < 2) 
    	    parent = dirName.getRoot();

	ent = new DirectoryListEntity();
    	ent.setEntity(parent);
    	ent.setDirectory(true);
    	ent.setDisplayName("..");
    	result.add(0, ent);

   	int count = -1;
    	for(DirectoryListEntity entry: result) {
    	    entry.setCount(++count);
    	}

    	return result;
    }
}