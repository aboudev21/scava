/*******************************************************************************
 * Copyright (C) 2017 University of L'Aquila
 * 
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package org.eclipse.scava.business.integration;

import org.eclipse.scava.business.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Juri Di Rocco
 *
 */
public interface UserRepository extends MongoRepository<User, String> {

	public User findOneByUsername(String username);
}