package org.eclipse.scava.metricprovider.trans.textpreprocessing.model;

import com.mongodb.*;
import java.util.*;
import com.googlecode.pongo.runtime.*;
import com.googlecode.pongo.runtime.querying.*;


public class NewsgroupArticlesTextPreprocessing extends Pongo {
	
	protected List<String> plainText = null;
	
	
	public NewsgroupArticlesTextPreprocessing() { 
		super();
		dbObject.put("plainText", new BasicDBList());
		NEWSGROUPNAME.setOwningType("org.eclipse.scava.metricprovider.trans.textpreprocessing.model.NewsgroupArticlesTextPreprocessing");
		ARTICLENUMBER.setOwningType("org.eclipse.scava.metricprovider.trans.textpreprocessing.model.NewsgroupArticlesTextPreprocessing");
		PLAINTEXT.setOwningType("org.eclipse.scava.metricprovider.trans.textpreprocessing.model.NewsgroupArticlesTextPreprocessing");
	}
	
	public static StringQueryProducer NEWSGROUPNAME = new StringQueryProducer("newsGroupName"); 
	public static NumericalQueryProducer ARTICLENUMBER = new NumericalQueryProducer("articleNumber");
	public static ArrayQueryProducer PLAINTEXT = new ArrayQueryProducer("plainText");
	
	
	public String getNewsGroupName() {
		return parseString(dbObject.get("newsGroupName")+"", "");
	}
	
	public NewsgroupArticlesTextPreprocessing setNewsGroupName(String newsGroupName) {
		dbObject.put("newsGroupName", newsGroupName);
		notifyChanged();
		return this;
	}
	public int getArticleNumber() {
		return parseInteger(dbObject.get("articleNumber")+"", 0);
	}
	
	public NewsgroupArticlesTextPreprocessing setArticleNumber(int articleNumber) {
		dbObject.put("articleNumber", articleNumber);
		notifyChanged();
		return this;
	}
	
	public List<String> getPlainText() {
		if (plainText == null) {
			plainText = new PrimitiveList<String>(this, (BasicDBList) dbObject.get("plainText"));
		}
		return plainText;
	}
	
	//Method created by Adrián
	public NewsgroupArticlesTextPreprocessing setPlainText(List<String> plainText) {
		dbObject.put("plainText", plainText);
		this.plainText = plainText;
		notifyChanged();
		return this;
	}	
}