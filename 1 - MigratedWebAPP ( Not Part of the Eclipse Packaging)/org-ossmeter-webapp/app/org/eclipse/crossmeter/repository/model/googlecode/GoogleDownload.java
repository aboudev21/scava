package org.eclipse.crossmeter.repository.model.googlecode;

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
	@Type(value = GoogleDownload.class, name="org.eclipse.crossmeter.repository.model.googlecode.GoogleDownload"), })
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleDownload extends Object {

	protected List<GoogleLabel> labels;
	protected boolean starred;
	protected String fileName;
	protected String uploaded_at;
	protected String updated_at;
	protected String size;
	protected int downloadCounts;
	
	public boolean getStarred() {
		return starred;
	}
	public String getFileName() {
		return fileName;
	}
	public String getUploaded_at() {
		return uploaded_at;
	}
	public String getUpdated_at() {
		return updated_at;
	}
	public String getSize() {
		return size;
	}
	public int getDownloadCounts() {
		return downloadCounts;
	}
	
	public List<GoogleLabel> getLabels() {
		return labels;
	}
}
