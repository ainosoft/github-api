package org.kohsuke.github;

import java.io.IOException;

public class GHProjectCardBuilder {
	private final GHProjectColumn column;
    private final Requester builder;

    
    public GHProjectCardBuilder(GHProjectColumn column, String note) {
    	this.column = column;
        this.builder = new Requester(column.root);
        builder.with("note",note);
	}
    
    /**
     * 
     * @param content_id must be issue_Id or PullRequest_Id 
     *
     */
    
    public GHProjectCardBuilder content_id(long content_id) {
        builder.with("content_id",content_id);
        return this;
    }
    
/**
 * 
 * @param content_type value should be "Issue" if the content_id is issue_id or "PullRequest" if the content_id is pullRequest_id
 * 
 */
    public GHProjectCardBuilder content_type(String content_type) {
        builder.with("content_type",content_type);
        return this;
    }
    
    
    /**
     * Creates a new card.
     */
    public GHProjectCard create() throws IOException {	    	
    	builder.setHeader("Accept", "application/vnd.github.inertia-preview+json");
        return builder.to(column.getApiTailUrl("issues"),GHProjectCard.class).wrap(column);
    }

}
