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

package acme.features.employer.job;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.applications.Application;
import acme.entities.customization.Customization;
import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerJobRepository extends AbstractRepository {

	@Query("select j from Job j where j.id = ?1")
	Job findOneJobById(int id);

	@Query("select j from Job j where j.employer.id = ?1")
	Collection<Job> findManyByEmployerId(int employerId);

	@Query("select a from Application a where a.job.id= ?1")
	Collection<Application> findApplicationsByJob(int jobId);

	@Query("select d from Duty d where d.job.id= ?1")
	Collection<Duty> findDutyByJob(int jobId);

	@Query("select e from Employer e where e.userAccount.id= ?1")
	Employer findOneEmployerByUserAccountId(int employerId);

	@Query("select c from Customization c")
	Customization findCustomization();
}
