package org.kohsuke.github;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

/**
 * @author testing1
 *
 */
/**
 * @author testing1
 *
 */
public class GHProjectColumn extends GHObject{
	private String  project_url,cards_url,name;
	GitHub root;
//	private GHUser creator;
	 String html_url = null;
	
	 /*package*/ GHProjectColumn wrap(GitHub root) {
	        this.root = root;
	        if (root.isOffline()) {
	        	//creator.wrapUp(root);
	        }
	        return this;
	    }
	 GHProjectColumn wrap(GHOrgProject project) {
		 this.root=project.root;
		 return this;
	 }
	
	 
	 public PagedIterable<GHProjectCard> listCards() {
	        return listCards(30);
	      }
	
	 public PagedIterable<GHProjectCard> listCards(final int pageSize){
		return new PagedIterable<GHProjectCard>() {	 
      public PagedIterator<GHProjectCard> _iterator(int pageSize) {
     	 Requester requester=root.retrieve();
		    requester.setHeader("Accept", "application/vnd.github.inertia-preview+json");
     	 
          return new PagedIterator<GHProjectCard>(requester.asIterator("/projects/columns/" + id + "/cards", GHProjectCard[].class, pageSize)) {
              @Override
              protected void wrapUp(GHProjectCard[] page) {
                  for (GHProjectCard c : page)
                      c.wrap(root);
              }
          };
      }
  }.withPageSize(pageSize);
}
	 
	 /**
	  * Get the project card from 'card_id'Long
	  * @param card_id
	  * @return GHProjectCard
	  * @throws IOException
	  */
	 
	 public GHProjectCard getCard(long card_id) throws IOException {
	        try {
	        	Requester requester=root.retrieve();
			    requester.setHeader("Accept", "application/vnd.github.inertia-preview+json");
	            return  requester.to("/projects/columns/cards/" +card_id, GHProjectCard.class).wrap(root);
	        } catch (FileNotFoundException e) {
	            return null;
	        }
	    }
	 
     /**
      * create new card in column      
      * @param note  must be null if you want to link the card with Issue or PullRequest
      * @return
      */
	 public GHProjectCardBuilder createCard(String note) {
	        return new GHProjectCardBuilder(this,note);
	    }

	 
	
	  String getApiTailUrl(String tail) {
	       // if (tail.length()>0 && !tail.startsWith("/"))    tail='/'+tail;
	        return "/projects/columns/"+getColumnID()+"/" +"cards";
	    }
	    
	    public Long getColumnID() {         
			return id;
	    }
	

	public String getProject_url() {
		return project_url;
	}
	public void setProject_url(String project_url) {
		this.project_url = project_url;
	}
	public String getCards_url() {
		return cards_url;
	}
	public void setCards_url(String cards_url) {
		this.cards_url = cards_url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public GitHub getRoot() {
		return root;
	}


	public void setRoot(GitHub root) {
		this.root = root;
	}




	@Override
	public URL getHtmlUrl() throws IOException {
		return GitHub.parseURL(html_url);
	}
	

}
