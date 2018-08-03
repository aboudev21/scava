/*******************************************************************************
 * Copyright (c) 2014 OSSMETER Partners.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    James Williams - Implementation.
 *******************************************************************************/
package org.eclipse.scava.platform.app.example.util;

import org.eclipse.scava.repository.model.Project;
import org.eclipse.scava.repository.model.bts.bugzilla.Bugzilla;
import org.eclipse.scava.repository.model.cc.nntp.NntpNewsGroup;
import org.eclipse.scava.repository.model.github.GitHubBugTracker;
import org.eclipse.scava.repository.model.jira.JiraBugTrackingSystem;
import org.eclipse.scava.repository.model.redmine.RedmineBugIssueTracker;
import org.eclipse.scava.repository.model.vcs.git.GitRepository;
import org.eclipse.scava.repository.model.vcs.svn.SvnRepository;

/**
 * This class is purely for illustration purposes and is not intended for release.
 * @author jimmy
 *
 */
public class ProjectCreationUtil {

	public static Project createProjectSvnNntpBugzilla(String name, String shortname, String description, String svnUrl, String bugzillaUrl, String bugzillaProduct, String bugzillaComponent, String nntpName, String nntpUrl, boolean nntpAuth, String usr, String pwd) {
		Project project = new Project();
		project.setName(name);
		project.setShortName(shortname);
		project.setDescription(description);
		
		SvnRepository svn = new SvnRepository();
		svn.setUrl(svnUrl);
		project.getVcsRepositories().add(svn);
		
		Bugzilla bugzilla = new Bugzilla();
		bugzilla.setUrl(bugzillaUrl);
		bugzilla.setProduct(bugzillaProduct); 
		bugzilla.setComponent(bugzillaComponent); 
		project.getBugTrackingSystems().add(bugzilla);
		
		NntpNewsGroup nntp = new NntpNewsGroup();
		nntp.setName(nntpName);
		nntp.setUrl(nntpUrl);
		nntp.setAuthenticationRequired(nntpAuth);
		nntp.setUsername(usr);
		nntp.setPassword(pwd);
		nntp.setPort(119);
//		ooNntp.setInterval(10000);
		nntp.setLastArticleChecked("-1");
		project.getCommunicationChannels().add(nntp);
		return project;
	}
	
	public static Project createProjectWithNewsGroup(String name, String url, String newsGroupName, 
			Boolean authenticationRequired, String username, String password, int port, int interval){
		Project project = new Project();
		project.setName(name);
		project.setShortName(name);
		
		NntpNewsGroup newsGroup = new NntpNewsGroup();
		newsGroup.setUrl(url);
		newsGroup.setNewsGroupName(newsGroupName);
		newsGroup.setAuthenticationRequired(authenticationRequired);
		newsGroup.setUsername(username);
		newsGroup.setPassword(password);
		newsGroup.setPort(port);
//		newsGroup.setInterval(interval);
		newsGroup.setLastArticleChecked("-1");
		project.getCommunicationChannels().add(newsGroup);
		
		return project;
	}
	
	public static Project createProjectWithBugTrackingSystemGitHub(String user, String login, String password, String projectName, String repositoryOwner, String repository){
		
		Project project = new Project();
		project.setName(projectName);
		project.setShortName(projectName);
		project.setDescription(projectName);
		
		GitHubBugTracker gitHub = new GitHubBugTracker();
		gitHub.setProject(user,login, password, repository, repositoryOwner);
		
		project.getBugTrackingSystems().add(gitHub);
		
		return project;
		
	}
	
	public static Project createProjectWithBugTrackingSystemGitHub(String user, String token, String projectName, String repositoryOwner, String repository){
		
		Project project = new Project();
		project.setName(projectName);
		project.setShortName(projectName);
		project.setDescription(projectName);
		
		GitHubBugTracker gitHub = new GitHubBugTracker();
		gitHub.setProject(user, token, repository, repositoryOwner);
		project.getBugTrackingSystems().add(gitHub);
		
		return project;
		
	}
	
	
	public static Project createGitProject(String name, String url) {
		Project project = new Project();
		project.setName(name);
		project.setShortName(name);
		project.setDescription(name);
		
		GitRepository repo = new GitRepository();
		repo.setUrl(url);
		
		project.getVcsRepositories().add(repo);
		return project;
	}
	
	public static Project createProjectWithBugTrackingSystem(String name, String url, String product, String component){
		Project project = new Project();
		project.setName(name);
		project.setShortName(name);
		
		Bugzilla bugzilla = new Bugzilla();
		bugzilla.setUrl(url);
		bugzilla.setProduct(product);
		if (component!=null) bugzilla.setComponent(component);
		project.getBugTrackingSystems().add(bugzilla);
		
		return project;
	}
	
	public static Project createSvnProject(String name, String url) {
		Project project = new Project();
		project.setName(name);
		project.setShortName(name);
		SvnRepository svnRepository = new SvnRepository();
		svnRepository.setUrl(url);
		project.getVcsRepositories().add(svnRepository); 
		return project;
	}
	
	public static Project createProjectWithBugTrackingSystemJira(String name, String login, String password, String url){
		Project project = new Project();
		project.setName(name);
		project.setShortName(name);
		project.setDescription(name);
		
		JiraBugTrackingSystem jira = new JiraBugTrackingSystem();
		
		
		jira.setProject(name);
		jira.setLogin(login);
		jira.setPassword(password);
		jira.setUrl(url);
		
		project.getBugTrackingSystems().add(jira);
		
		return project;
	}
	
	public static Project createProjectWithBugTrackingSystemRedmine(String name, String url){
		
		Project project = new Project();
		project.setName(name);
		project.setShortName(name);
		project.setDescription(name);

		RedmineBugIssueTracker redmine = new RedmineBugIssueTracker();
		
		redmine.setUrl(url);
		redmine.setProject(name);
		project.getBugTrackingSystems().add(redmine);
		
	
		return project;
	}
	
	
//	public static Project createSourceForgeProject(String name) {
//		SourceForgeProject project = new SourceForgeProject();
//		project.setName(name);
//		/* TODO: Why was this code commented out?
//		SvnRepository svnRepository = new SvnRepository();
//		svnRepository.setUrl("http://" + name + ".svn.sourceforge.net/svnroot/" + name);
//		project.getVcsRepositories().add(svnRepository); */
//		return project;
//	}
}