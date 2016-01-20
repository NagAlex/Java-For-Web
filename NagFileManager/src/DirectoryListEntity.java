import java.io.*;
import java.nio.file.*;
//import java.util.*;

public class DirectoryListEntity {
    private int count;
    private boolean isDir;
    private Path entity;
    private String displayName;

    public DirectoryListEntity() {
    	count = -1;
    	isDir = false;
    	entity = null;
    	displayName = "empty";
    }
    
    public int getCount() {
    	return count;
    }

    public void setCount(int count) {
    	this.count = count;
    }
    
    public boolean isDirectory() {
    	return isDir;
    }

    public void setDirectory(boolean isDir) {
    	this.isDir = isDir;
    }

    public Path getEntity() {
    	return entity;
    }

    public void setEntity(Path entity) {
    	this.entity = entity;
    }

    public String getEntityName() {
    	return entity.getFileName().toString();
    }

    public String getDisplayName() {
    	return displayName;
    }

    public void setDisplayName(String displayName) {
    	this.displayName = displayName;
    }

    public boolean isTextFile() {
    	return (entity.toString().endsWith(".java") || entity.toString().endsWith(".txt") || entity.toString().endsWith(".TXT"));
    }

}