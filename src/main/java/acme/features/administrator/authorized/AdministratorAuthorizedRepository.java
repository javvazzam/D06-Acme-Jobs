/*
 * AuthenticatedEmployerRepository.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrator.authorized;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.roles.Auditor;
import acme.entities.roles.Authorized;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorAuthorizedRepository extends AbstractRepository {

	@Query("select ua from UserAccount ua where ua.id = ?1")
	UserAccount findOneUserAccountById(int id);

	@Query("select e from Authorized e where e.userAccount.id = ?1")
	Auditor findOneAuditorByUserAccountId(int id);

	//	@Query("select e from Auditor e where e.id = ?1")
	//	Auditor findOneAuditorById(int id);

	@Query("select e from Authorized e where e.id = ?1")
	Authorized findOneAuthorizedById(int id);

	@Query("select e from Authorized e")
	Collection<Authorized> findManyAll();
}
