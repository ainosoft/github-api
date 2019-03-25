package org.kohsuke.github;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class GHProjectTest {

	@Test
	public void connection() {
		GitHub gitHub = null;
		GHOrganization organization;

		try {
			gitHub = GitHub.connect("aino-swapnali-new", "8727fea90f82fb3d5b55d305c12ba7033dbeb994");
			organization = gitHub.getOrganization("swapnali-test");

			List<GHRepository> listRepository = organization.listRepositories().asList();
			List<GHProject> ListProjects = organization.listProjects().asList();

			assertTrue(ListProjects != null);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
