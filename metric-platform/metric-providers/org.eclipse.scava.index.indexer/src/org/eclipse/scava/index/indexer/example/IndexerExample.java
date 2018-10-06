package org.eclipse.scava.index.indexer.example;

import java.io.IOException;

import org.eclipse.scava.index.indexer.ElasticSearchManager;

public class IndexerExample {

/**
 * This is a simple demonstration of how the Indexing is performed in SCAVA
 *  
 * 
 */
	
	public static void main(String[] args) {
		ExampleRepoistoryDocument issue = new ExampleRepoistoryDocument();
		String x = null;
		
		issue.setUid("GitIssue_1234");
		issue.setPlain_text("This is an example of a plain text body");
		issue.setBody("This is an exam<>KLLSF()ple of a body");
		issue.setCreator("Dan");
		issue.setEmotion("Sadness");
		issue.setSentiment("Positive");
		issue.setSeverity("Bad");
		issue.setRequest(true);
		issue.setCreated_at(null);
		issue.setUpdated_at(null);
		issue.setContains_code(false);
		issue.setBug_id("1234");
		issue.setSummary(x);
		issue.setAssignee("Dan");
		issue.setHtml_url("www.google.com");
		issue.setUrl("www.google.com");
		issue.setLabel(null);
		issue.setNumber_of_comments(110);
		issue.setClosed_at(null);

		ElasticSearchManager esm = new ElasticSearchManager();
		
		String indexSetting = "{\n" + 
				"        \"number_of_replicas\" : 2\n" + 
				"    }";
		
		String map = "{\n" + 
				"  \"properties\": {\n" + 
				"    \"bug_id\": {\n" + 
				"      \"type\": \"keyword\"\n" + 
				"    },\n" + 
				"    \"summary\": {\n" + 
				"      \"type\": \"text\"\n" + 
				"    },\n" + 
				"    \"created_at\": {\n" + 
				"      \"type\": \"date\"\n" + 
				"    },\n" + 
				"    \"closed_at\": {\n" + 
				"      \"type\": \"date\"\n" + 
				"    },\n" + 
				"    \"updated_at\": {\n" + 
				"      \"type\": \"date\"\n" + 
				"    },\n" + 
				"    \"number_of_comments\": {\n" + 
				"      \"type\": \"integer\"\n" + 
				"    },\n" + 
				"    \"label\": {\n" + 
				"      \"type\": \"keyword\"\n" + 
				"    },\n" + 
				"    \"body\": {\n" + 
				"      \"type\": \"text\"\n" + 
				"    },\n" + 
				"    \"assignee\": {\n" + 
				"      \"type\": \"keyword\"\n" + 
				"    },\n" + 
				"    \"html_url\": {\n" + 
				"      \"type\": \"keyword\"\n" + 
				"    },\n" + 
				"    \"url\": {\n" + 
				"      \"type\": \"keyword\"\n" + 
				"    },\n" + 
				"    \"creator\": {\n" + 
				"      \"type\": \"keyword\"\n" + 
				"    },\n" + 
				"    \"contains_code\": {\n" + 
				"      \"type\": \"boolean\"\n" + 
				"    },\n" + 
				"    \"severity\": {\n" + 
				"      \"type\": \"keyword\"\n" + 
				"    },\n" + 
				"    \"sentiment\": {\n" + 
				"      \"type\": \"keyword\"\n" + 
				"    },\n" + 
				"    \"emotion\": {\n" + 
				"      \"type\": \"keyword\"\n" + 
				"    },\n" + 
				"    \"request\": {\n" + 
				"      \"type\": \"boolean\"\n" + 
				"    }\n" + 
				"  }\n" + 
				"}\n";
		
		
		
		String index = "example.issue";
		String type = "issue";
		try {
			for (String i : esm.getIndices()) {System.out.println(i);}
			esm.createIndex(index); //creates an Index
			for (String i : esm.getIndices()) {System.out.println(i);}
			esm.addIndexSetting(index,indexSetting );
			esm.addMapping(index, type, map);
			esm.indexDocument(index, type, "ExampleIssue_1234", issue); //UID is generated by the system
			//esm.deleteIndex(index);
			esm.closeAllClients();
			
		} catch (IOException e) {

			e.printStackTrace();
		}


	}

}