package org.kohsuke.github;

import java.io.IOException;
import java.net.URL;

public class GHProjectCard extends GHObject{
	private boolean archived;
	private String note;
	private String column_url, content_url, project_url;
	 String html_url = null;
	
	GitHub root;
	private GHUser creator;
	
	 /*package*/ GHProjectCard wrap(GitHub root) {
		 
	        this.root = root;
	        if (root.isOffline()) {
	        	creator.wrapUp(root);
	        	
	        }
	        return this;
	    } 
	 
	 GHProjectCard wrap(GHProjectColumn column) {
		 this.root=column.root;
		 return this;
	 }

	public boolean isArchived() {
		return archived;
	}

	public void setArchived(boolean archived) {
		this.archived = archived;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public GitHub getRoot() {
		return root;
	}

	public void setRoot(GitHub root) {
		this.root = root;
	}
	
	

	public String getColumn_url() {
		return column_url;
	}

	public void setColumn_url(String column_url) {
		this.column_url = column_url;
	}

	public String getContent_url() {
		return content_url;
	}

	public void setContent_url(String content_url) {
		this.content_url = content_url;
	}

	public String getProject_url() {
		return project_url;
	}

	public void setProject_url(String project_url) {
		this.project_url = project_url;
	}

	public GHUser getCreator() {
		return creator;
	}

	public void setCreator(GHUser creator) {
		this.creator = creator;
	}

	@Override
	public URL getHtmlUrl() throws IOException {		
		return GitHub.parseURL(html_url);
	}

	
	
	
	 

}
