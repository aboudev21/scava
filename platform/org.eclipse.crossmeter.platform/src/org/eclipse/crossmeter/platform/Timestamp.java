/*******************************************************************************
 * Copyright (c) 2018 University of York
 * 
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package org.eclipse.crossmeter.platform;

public class Timestamp {
	
	public static final java.sql.Timestamp NOW = new java.sql.Timestamp(( new java.util.Date()).getTime());
}