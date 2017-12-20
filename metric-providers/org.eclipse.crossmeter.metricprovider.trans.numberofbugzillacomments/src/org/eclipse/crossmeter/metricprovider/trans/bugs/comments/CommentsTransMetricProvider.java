/*******************************************************************************
 * Copyright (c) 2017 University of Manchester
 * 
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package org.eclipse.crossmeter.metricprovider.trans.bugs.comments;

import java.util.Collections;
import java.util.List;

import org.eclipse.crossmeter.metricprovider.trans.bugs.comments.model.BugTrackerData;
import org.eclipse.crossmeter.metricprovider.trans.bugs.comments.model.BugsCommentsTransMetric;
import org.eclipse.crossmeter.platform.IMetricProvider;
import org.eclipse.crossmeter.platform.ITransientMetricProvider;
import org.eclipse.crossmeter.platform.MetricProviderContext;
import org.eclipse.crossmeter.platform.delta.ProjectDelta;
import org.eclipse.crossmeter.platform.delta.bugtrackingsystem.BugTrackingSystemDelta;
import org.eclipse.crossmeter.platform.delta.bugtrackingsystem.BugTrackingSystemProjectDelta;
import org.eclipse.crossmeter.platform.delta.bugtrackingsystem.PlatformBugTrackingSystemManager;
import org.eclipse.crossmeter.repository.model.BugTrackingSystem;
import org.eclipse.crossmeter.repository.model.Project;

import com.mongodb.DB;

public class CommentsTransMetricProvider implements ITransientMetricProvider<BugsCommentsTransMetric>{

	protected PlatformBugTrackingSystemManager platformBugTrackingSystemManager;

	@Override
	public String getIdentifier() {
		return CommentsTransMetricProvider.class.getCanonicalName();
	}

	@Override
	public String getShortIdentifier() {
		return "bugcomments";
	}

	@Override
	public String getFriendlyName() {
		return "Number of bug comments";
	}

	@Override
	public String getSummaryInformation() {
		return "The number of bug comments over time. Lorum ipsum.";
	}
	
	@Override
	public boolean appliesTo(Project project) {
	    return !project.getBugTrackingSystems().isEmpty();	   
	}

	@Override
	public void setUses(List<IMetricProvider> uses) {
		// DO NOTHING -- we don't use anything
	}

	@Override
	public List<String> getIdentifiersOfUses() {
		return Collections.emptyList();
	}

	@Override
	public void setMetricProviderContext(MetricProviderContext context) {
		this.platformBugTrackingSystemManager = context.getPlatformBugTrackingSystemManager();
	}

	@Override
	public BugsCommentsTransMetric adapt(DB db) {
		return new BugsCommentsTransMetric(db);
	}

	@Override
	public void measure(Project project, ProjectDelta projectDelta, BugsCommentsTransMetric db) {
		BugTrackingSystemProjectDelta delta = projectDelta.getBugTrackingSystemDelta();
		
		for (BugTrackingSystemDelta bugTrackingSystemDelta : delta.getBugTrackingSystemDeltas()) {
			BugTrackingSystem bugTrackingSystem = bugTrackingSystemDelta.getBugTrackingSystem();
			Iterable<BugTrackerData> bugTrackerDataIt = db.getBugTrackerData().
										find(BugTrackerData.BUGTRACKERID.eq(bugTrackingSystem.getOSSMeterId()));
			BugTrackerData bugTrackerData = null;
			for (BugTrackerData btd:  bugTrackerDataIt) {
				bugTrackerData = btd;
			}
			if (bugTrackerData == null) {
				bugTrackerData = new BugTrackerData();
				bugTrackerData.setBugTrackerId(bugTrackingSystem.getOSSMeterId());
				db.getBugTrackerData().add(bugTrackerData);
			} 
			int numberOfComments = bugTrackingSystemDelta.getComments().size();
			bugTrackerData.setNumberOfComments(numberOfComments);
			int cumulativeNumberOfComments = bugTrackerData.getCumulativeNumberOfComments();
			bugTrackerData.setCumulativeNumberOfComments(cumulativeNumberOfComments + numberOfComments);
//			System.out.println("bugTrackingSystemDelta.getComments().size(): " + 
//								bugTrackingSystemDelta.getComments().size());
			db.sync();
		}
	}

}
