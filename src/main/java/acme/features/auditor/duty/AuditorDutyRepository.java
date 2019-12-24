/*
 * AuthenticatedAcmeRequestRepository.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.auditor.duty;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuditorDutyRepository extends AbstractRepository {

	@Query("select d from Duty d where d.id = ?1")
	Duty findOneById(int id);

	@Query("select j from Job j where j.id = ?1")
	Job findOneJobById(int id);

	@Query("select d from Duty d where d.job.id = ?1")
	Collection<Duty> findManyAllByJob(int id);

	@Query("select u from Auditor u where u.userAccount.id= ?1")
	Auditor findOneAuditorByUserAccountId(int auditorId);
}
