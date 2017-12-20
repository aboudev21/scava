/*******************************************************************************
 * Copyright (c) 2017 University of L'Aquila
 * 
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package org.eclipse.crossmeter.repository.model.redmine;

import com.googlecode.pongo.runtime.*;
import com.googlecode.pongo.runtime.querying.*;


public class RedmineFeature extends Pongo {
	
	
	
	public RedmineFeature() { 
		super();
		NAME.setOwningType("org.eclipse.crossmeter.repository.model.redmine.RedmineFeature");
	}
	
	public static StringQueryProducer NAME = new StringQueryProducer("name"); 
	
	
	public String getName() {
		return parseString(dbObject.get("name")+"", "");
	}
	
	public RedmineFeature setName(String name) {
		dbObject.put("name", name);
		notifyChanged();
		return this;
	}
	
	
	
	
}
