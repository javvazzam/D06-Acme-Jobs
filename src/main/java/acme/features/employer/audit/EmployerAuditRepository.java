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

package acme.features.employer.audit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.audits.Audit;
import acme.entities.jobs.Job;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerAuditRepository extends AbstractRepository {

	@Query("select a from Audit a where a.id = ?1")
	Audit findOneAuditById(int id);

	@Query("select j from Job j where j.id = ?1")
	Job findOneJobById(int id);

	@Query("select a from Audit a where a.auditor.id = ?1")
	Collection<Audit> findManyByAuditorId(int auditorId);

	@Query("select a from Audit a")
	Collection<Audit> findManyAll();

	@Query("select a from Audit a where a.job.id = ?1 and a.finalMode = true")
	Collection<Audit> findManyAuditsReferedToJob(int jobId);

	@Query("select a from Audit a where a.job.id = ?1 and a.finalMode = true")
	Collection<Audit> findManyAuditsReferedToJob2(int jobId);

}
