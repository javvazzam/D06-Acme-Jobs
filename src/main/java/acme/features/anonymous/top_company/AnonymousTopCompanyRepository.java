
package acme.features.anonymous.top_company;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.companies.Company;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousTopCompanyRepository extends AbstractRepository {

	@Query("select a from Company a where a.id = ?1")
	Company findOneById(int id);

	@Query("select a from Company a where a.stars=5")
	Collection<Company> findManyAll();

}
