package org.kohsuke.github;

import java.io.IOException;

public class GHCreateProjectBuilder {
	
	 private final GitHub root;
	    protected final Requester builder;
	    private final String apiUrlTail;
	    
	    public GHCreateProjectBuilder(GitHub root, String apiUrlTail, String name) {	    	
	    	 this.root = root;
	         this.apiUrlTail = apiUrlTail;
	         this.builder = new Requester(root);
	       // builder.setHeader("Accept", "application/vnd.github.inertia-preview+json");
	         this.builder.with("name",name);
	        
	         
		}
	    
	    public GHCreateProjectBuilder body(String body) {
	        this.builder.with("body",body);
	        return this;
	    }
	    
	    
	    
	    

	    public GHOrgProject create() throws IOException {
	    	builder.setHeader("Accept", "application/vnd.github.inertia-preview+json");		     	
	        return builder.method("POST").to(apiUrlTail, GHOrgProject.class).wrap(root);
	    }

}
