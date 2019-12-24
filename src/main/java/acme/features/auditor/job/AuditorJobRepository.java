/*
 * ConsumerAcmeRequestRepository.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.auditor.job;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuditorJobRepository extends AbstractRepository {

	@Query("select j from Job j where j.id = ?1")
	Job findOneJobById(int id);

	@Query("select a.job from Audit a where a.auditor.id=?1 and a.job.finalMode=1")
	Collection<Job> findManyByAuditorId(int auditorId);

	@Query("select a.job from Audit a where a.auditor.id!=?1 and a.job.finalMode=1")
	Collection<Job> findNotManyByAuditorId(int auditorId);

	@Query("select j from Job j where j.finalMode = 1")
	Collection<Job> findManyPublishedJobs();

	@Query("select u from Auditor u where u.userAccount.id= ?1")
	Auditor findOneAuditorByUserAccountId(int auditorId);
}
