
package acme.features.employer.application;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.applications.Application;
import acme.entities.customization.Customization;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerApplicationRepository extends AbstractRepository {

	@Query("select a from Application a where a.id = ?1")
	Application findOneApplicationById(int id);

	/*
	 * @Query("select a from Application a where a.job.id = ?1")
	 * Collection<Application> findManyByJobId(int id);
	 *
	 * @Query("select j from Job j where j.employer.id = ?1")
	 * Collection<Job> findManyJobByEmployerId(int d);
	 */

	@Query("select a from Application a where a.job.employer.id = ?1 order by a.reference asc")
	Collection<Application> findManyReferenceByEmployerId(int id);

	@Query("select a from Application a where a.job.employer.id = ?1 order by a.status asc")
	Collection<Application> findManyStatusByEmployerId(int id);

	@Query("select a from Application a where a.job.employer.id = ?1 order by a.moment asc")
	Collection<Application> findManyMomentByEmployerId(int id);

	@Query("select a from Application a where a.job.employer.id = ?1 order by a.reference asc, a.status asc, a.moment asc")
	Collection<Application> findManyByEmployerId(int id);

	@Query("select c from Customization c")
	Customization findCustomization();

}
