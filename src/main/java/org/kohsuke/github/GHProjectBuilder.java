package org.kohsuke.github;

public class GHProjectBuilder {

	private final GHRepository	repo;
	private final Requester		builder;

	GHProjectBuilder(GHRepository repo, String title) {
		this.repo = repo;
		this.builder = new Requester(repo.root);
		builder.with("title", title);
	}

	public GHProjectBuilder body(String str) {
		builder.with("body", str);
		return this;
	}

}
