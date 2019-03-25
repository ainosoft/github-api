package org.kohsuke.github;

import java.io.IOException;
import java.net.URL;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

@SuppressWarnings({ "UnusedDeclaration" })
@SuppressFBWarnings(value = { "UWF_UNWRITTEN_PUBLIC_OR_PROTECTED_FIELD", "UWF_UNWRITTEN_FIELD",
		"NP_UNWRITTEN_FIELD" }, justification = "JSON API")
public class GHProject extends GHObject {

	GitHub			root;
	private String	html_url;

	private String	name;
	private String	body;
	private String	state;

	private GHUser	owner;		// not fully populated. beware.

	/* package */ GHProject wrap(GitHub root) {
		this.root = root;
		if (root.isOffline()) {
			owner.wrapUp(root);
		}
		return this;
	}

	private void edit(String key, String value) throws IOException {
		Requester requester = new Requester(root);
		if (!key.equals("name"))
			requester.with("name", name); // even when we don't change the name, we need to send it in
		requester.with(key, value).method("PATCH").to(getApiTailUrl(""));
	}

	String getApiTailUrl(String tail) {
		if (tail.length() > 0 && !tail.startsWith("/"))
			tail = '/' + tail;
		return "/projects/" + getOwnerName() + "/" + name + tail;
	}

	public String getOwnerName() {
		// consistency of the GitHub API is super... some serialized forms of GHRepository populate
		// a full GHUser while others populate only the owner and email. This later form is super helpful
		// in putting the login in owner.name not owner.login... thankfully we can easily identify this
		// second set because owner.login will be null
		return owner.login != null ? owner.login : owner.name;
	}

	public String getName() {
		return name;
	}

	public void setName(String value) throws IOException {
		edit("name", value);
	}

	public String getBody() {
		return body;
	}

	public void setBody(String value) throws IOException {
		edit("body", value);
	}

	public String getState() {
		return state;
	}

	public void setState(String value) throws IOException {
		edit("state", value);
	}

	public void setHtml_url(String html_url) {
		this.html_url = html_url;
	}

	@Override
	public URL getHtmlUrl() {
		return GitHub.parseURL(html_url);
	}

}
