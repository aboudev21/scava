package org.eclipse.crossmeter.repository.model.eclipse;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

import org.eclipse.crossmeter.platform.factoids.*;
import org.eclipse.crossmeter.repository.model.*;
import org.eclipse.crossmeter.repository.model.bts.bugzilla.*;
import org.eclipse.crossmeter.repository.model.cc.forum.*;
import org.eclipse.crossmeter.repository.model.cc.nntp.*;
import org.eclipse.crossmeter.repository.model.cc.wiki.*;
import org.eclipse.crossmeter.repository.model.eclipse.*;
import org.eclipse.crossmeter.repository.model.github.*;
import org.eclipse.crossmeter.repository.model.googlecode.*;
import org.eclipse.crossmeter.repository.model.metrics.*;
import org.eclipse.crossmeter.repository.model.redmine.*;
import org.eclipse.crossmeter.repository.model.sourceforge.*;
import org.eclipse.crossmeter.repository.model.vcs.cvs.*;
import org.eclipse.crossmeter.repository.model.vcs.git.*;
import org.eclipse.crossmeter.repository.model.vcs.svn.*;

@JsonTypeInfo(use=JsonTypeInfo.Id.NAME,
	include=JsonTypeInfo.As.PROPERTY,
	property = "_type")
@JsonSubTypes({
	@Type(value = EclipseProject.class, name="org.eclipse.crossmeter.repository.model.eclipse.EclipseProject"), })
@JsonIgnoreProperties(ignoreUnknown = true)
public class EclipseProject extends Project {

	protected List<EclipsePlatform> platforms;
	protected List<Review> reviews;
	protected List<Article> articles;
	protected List<Release> releases;
	protected String paragraphUrl;
	protected String descriptionUrl;
	protected String downloadsUrl;
	protected String projectplanUrl;
	protected String updatesiteUrl;
	protected String state;
	
	public String getParagraphUrl() {
		return paragraphUrl;
	}
	public String getDescriptionUrl() {
		return descriptionUrl;
	}
	public String getDownloadsUrl() {
		return downloadsUrl;
	}
	public String getProjectplanUrl() {
		return projectplanUrl;
	}
	public String getUpdatesiteUrl() {
		return updatesiteUrl;
	}
	public String getState() {
		return state;
	}
	
	public List<EclipsePlatform> getPlatforms() {
		return platforms;
	}
	public List<Review> getReviews() {
		return reviews;
	}
	public List<Article> getArticles() {
		return articles;
	}
	public List<Release> getReleases() {
		return releases;
	}
}
