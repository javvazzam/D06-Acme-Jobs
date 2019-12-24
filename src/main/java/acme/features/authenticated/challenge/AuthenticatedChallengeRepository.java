
package acme.features.authenticated.challenge;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.challenges.Challenge;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedChallengeRepository extends AbstractRepository {

	@Query("select c from Challenge c where c.id = ?1")
	Challenge findOneById(int id);

	@Query("select c from Challenge c where c.deadline >= current_date()")
	Collection<Challenge> findManyAll();
}
