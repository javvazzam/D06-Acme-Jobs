/*
 * AuthenticatedUserAccountRepository.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrator.investor;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.investors.Investor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorInvestorRepository extends AbstractRepository {

	@Query("select i from Investor i where i.id = ?1")
	Investor findOneById(int id);

	//@Query("select i from Investor_record i")
	@Query("select i from Investor i ")
	Collection<Investor> findManyAll();

}
