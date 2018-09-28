package org.eclipse.scava.metricprovider.trans.requestreplyclassification.model;

import com.mongodb.*;
import java.util.*;
import com.googlecode.pongo.runtime.*;
import com.googlecode.pongo.runtime.querying.*;


public class ForumsPosts extends Pongo {
	
	
	
	public ForumsPosts() { 
		super();
		FORUMID.setOwningType("org.eclipse.scava.metricprovider.trans.requestreplyclassification.model.ForumsPosts");
		TOPICID.setOwningType("org.eclipse.scava.metricprovider.trans.requestreplyclassification.model.ForumsPosts");
		POSTID.setOwningType("org.eclipse.scava.metricprovider.trans.requestreplyclassification.model.ForumsPosts");
		CLASSIFICATIONRESULT.setOwningType("org.eclipse.scava.metricprovider.trans.requestreplyclassification.model.ForumsPosts");
		DATE.setOwningType("org.eclipse.scava.metricprovider.trans.requestreplyclassification.model.ForumsPosts");
	}
	
	public static StringQueryProducer FORUMID = new StringQueryProducer("forumId"); 
	public static StringQueryProducer TOPICID = new StringQueryProducer("topicId"); 
	public static StringQueryProducer POSTID = new StringQueryProducer("postId"); 
	public static StringQueryProducer CLASSIFICATIONRESULT = new StringQueryProducer("classificationResult"); 
	public static StringQueryProducer DATE = new StringQueryProducer("date"); 
	
	
	public String getForumId() {
		return parseString(dbObject.get("forumId")+"", "");
	}
	
	public ForumsPosts setForumId(String forumId) {
		dbObject.put("forumId", forumId);
		notifyChanged();
		return this;
	}
	public String getTopicId() {
		return parseString(dbObject.get("topicId")+"", "");
	}
	
	public ForumsPosts setTopicId(String topicId) {
		dbObject.put("topicId", topicId);
		notifyChanged();
		return this;
	}
	public String getPostId() {
		return parseString(dbObject.get("postId")+"", "");
	}
	
	public ForumsPosts setPostId(String postId) {
		dbObject.put("postId", postId);
		notifyChanged();
		return this;
	}
	public String getClassificationResult() {
		return parseString(dbObject.get("classificationResult")+"", "");
	}
	
	public ForumsPosts setClassificationResult(String classificationResult) {
		dbObject.put("classificationResult", classificationResult);
		notifyChanged();
		return this;
	}
	public String getDate() {
		return parseString(dbObject.get("date")+"", "");
	}
	
	public ForumsPosts setDate(String date) {
		dbObject.put("date", date);
		notifyChanged();
		return this;
	}
	
	
	
	
}