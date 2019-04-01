package org.kohsuke.github;

import java.io.IOException;

public class GHProjectColumnBuilder {
	 private final GHOrgProject project;
	    private final Requester builder;

	    
	    public GHProjectColumnBuilder(GHOrgProject project, String name) {
	    	this.project = project;
	        this.builder = new Requester(project.root);
	        builder.with("name",name);
		}
	    
	    
	    public GHProjectColumnBuilder body(String str) {
	        builder.with("body",str);
	        return this;
	    }
	    
	    
	    
	    /**
	     * Creates a new column.
	     */
	    public GHProjectColumn create() throws IOException {	    	
	    	builder.setHeader("Accept", "application/vnd.github.inertia-preview+json");
	        return builder.to(project.getApiTailUrl("issues"),GHProjectColumn.class).wrap(project);
	    }
}
