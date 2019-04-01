package org.kohsuke.github;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

@SuppressWarnings({"UnusedDeclaration"})
@SuppressFBWarnings(value = {"UWF_UNWRITTEN_PUBLIC_OR_PROTECTED_FIELD", "UWF_UNWRITTEN_FIELD",
    "NP_UNWRITTEN_FIELD"}, justification = "JSON API")
public class GHOrgProject extends GHObject {
	
	private String name,body,state,columns_url,html_url;
	private Integer number;
	private GHUser creator;
	//private GHUser owner;
	GitHub root;
	
	
	public GHOrgProject() {
		   
	}
	/**
     * Lists up all the project in this organization.
     */
	
	 public PagedIterable<GHProjectColumn> listColumn() {
	        return listColumn(30);
	      }
	
	 public PagedIterable<GHProjectColumn> listColumn(final int pageSize){
		return new PagedIterable<GHProjectColumn>() {	 
         public PagedIterator<GHProjectColumn> _iterator(int pageSize) {
        	 Requester requester=root.retrieve();
 		    requester.setHeader("Accept", "application/vnd.github.inertia-preview+json");
        	 
             return new PagedIterator<GHProjectColumn>(requester.asIterator("/projects/" + id + "/columns", GHProjectColumn[].class, pageSize)) {
                 @Override
                 protected void wrapUp(GHProjectColumn[] page) {
                     for (GHProjectColumn c : page)
                         c.wrap(root);
                 }
             };
         }
     }.withPageSize(pageSize);
}
 
	 
	 /**
	     * Gets the project column from 'column_id' Long that GitHub calls as "column id"
	     *
	     * @see GHOrgProject#getId()
	     */
	 public GHProjectColumn getColumn(long column_id) throws IOException {
	        try {
	        	Requester requester=root.retrieve();
			    requester.setHeader("Accept", "application/vnd.github.inertia-preview+json");
	            return requester.to("/projects/columns/" + column_id, GHProjectColumn.class).wrap(root);
	        } catch (FileNotFoundException e) {
	            return null;
	        }
	    }
	 
	 /**
	     * create new column in project
	     *
	     * @see GHOrgProject#getId()
	     */
	 public GHProjectColumnBuilder createColumn(String name) {
	        return new GHProjectColumnBuilder(this,name);
	    }

	
	

    /*package*/ GHOrgProject wrap(GitHub root) {
        this.root = root;
        if (root.isOffline()) {
        	creator.wrapUp(root);
        }
        return this;
    }

    String getApiTailUrl(String tail) {
       // if (tail.length()>0 && !tail.startsWith("/"))    tail='/'+tail;
        return "/projects/"+getProjectID()+"/" + "columns";
    }
    
    public Long getProjectID() {
        // consistency of the GitHub API is super... some serialized forms of GHRepository populate
        // a full GHUser while others populate only the owner and email. This later form is super helpful
        // in putting the login in owner.name not owner.login... thankfully we can easily identify this
        // second set because owner.login will be null
        //long id=this.id;
		return id;
    }


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	public String getBody() {
		return body;
	}


	public void setBody(String body) {
		this.body = body;
	}


	


	public GitHub getRoot() {
		return root;
	}


	public void setRoot(GitHub root) {
		this.root = root;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public URL getHtmlUrl() {
        return GitHub.parseURL(html_url);
    }


	public String getColumns_url() {
		return columns_url;
	}


	public void setColumns_url(String columns_url) {
		this.columns_url = columns_url;
	}


	public Integer getNumber() {
		return number;
	}


	public void setNumber(Integer number) {
		this.number = number;
	}


	public void setHtml_url(String html_url) {
		this.html_url = html_url;
	}


	public GHUser getCreator() {
		return creator;
	}


	public void setCreator(GHUser creator) {
		this.creator = creator;
	}
	
	
	
    
    
    
    

}
